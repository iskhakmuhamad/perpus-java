package controller;

import model.AnggotaModel;
import model.BukuModel;
import model.PinjamModel;
import model.UserModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static utils.ColumnUtils.*;

public class AdminController {
    VAdmin vAdmin;
    BukuModel bukuModel;
    String[][] buku, anggota, user, pinjam;
    String kolom;

    public AdminController(VAdmin vAdmin, BukuModel bukuModel) {
        this.vAdmin = vAdmin;
        this.bukuModel = bukuModel;
        halamanBuku();
    }

    public void halamanBuku() {

        VAdmin.halaman = "buku";
        vAdmin.dispose();
        this.vAdmin = new VAdmin();
        vAdmin.btnBuku.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));

        if (bukuModel.getBanyakBuku() != 0) {
            buku = bukuModel.readBuku();
            vAdmin.setTitle("BUKU");
            vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            vAdmin.aturKolomBuku();
        }

        vAdmin.btnFilter.addActionListener(actionEvent -> {
            VCariBuku cari = new VCariBuku("FILTER");
            cari.radio2.isSelected();
            kolom = "genre";
            isiFilterBuku(cari, kolom);

            cari.radio2.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "genre";
                isiFilterBuku(cari, kolom);
            });
            cari.radio3.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "penulis";
                isiFilterBuku(cari, kolom);
            });
            cari.radio4.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "penerbit";
                isiFilterBuku(cari, kolom);
            });
            cari.radio5.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "lokasi";
                isiFilterBuku(cari, kolom);
            });
            cari.radio6.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "stok";
                isiFilterBuku(cari, kolom);
            });

            cari.btnGo.addActionListener(actionEvent1 -> {
                buku = bukuModel.searchBuku(Objects.requireNonNull(cari.cbFilter.getSelectedItem()).toString(), kolom);
                if (buku == null) {
                    JOptionPane.showMessageDialog(null, "Maaf, Buku yang anda filter tidak ada!");
                } else {
                    vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                    vAdmin.aturKolomBuku();
                }
            });
        });

        vAdmin.btnCari.addActionListener(actionEvent -> {
            VCariBuku cari = new VCariBuku("CARI");
            cari.btnGo.addActionListener(actionEvent1 -> {
                if (cari.radio6.isSelected()) {
                    kolom = "stok";
                } else if (cari.radio2.isSelected()) {
                    kolom = "genre";
                } else if (cari.radio3.isSelected()) {
                    kolom = "penulis";
                } else if (cari.radio4.isSelected()) {
                    kolom = "penerbit";
                } else if (cari.radio5.isSelected()) {
                    kolom = "lokasi";
                } else {
                    kolom = "judul";
                }
                if (cari.tfCari.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Input Buku yang Dicari");
                } else {
                    buku = bukuModel.searchBuku(cari.tfCari.getText(), kolom);
                    if (buku == null) {
                        JOptionPane.showMessageDialog(null, "Maaf, Buku yang anda cari tidak ada!");
                    } else {
                        vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                        vAdmin.aturKolomBuku();
                    }
                }
            });
        });

        vAdmin.btnTampil.addActionListener(actionEvent -> {
            buku = bukuModel.readBuku();
            vAdmin.setTitle("BUKU");
            vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            vAdmin.aturKolomBuku();
        });

        vAdmin.btnTambah.addActionListener(actionEvent -> {
            VTambahBuku vTambahBuku = new VTambahBuku("TAMBAH");
            vTambahBuku.btnTambah.addActionListener(actionEvent1 -> {
                String judul = vTambahBuku.tfJudul.getText();
                String genre = vTambahBuku.tfGenre.getText();
                String penulis = vTambahBuku.tfPenulis.getText();
                String penerbit = vTambahBuku.tfPenerbit.getText();
                String lokasi = vTambahBuku.tfLokasi.getText();
                String stok = vTambahBuku.tfStok.getText();
                int jumlahBuku;
                try {
                    jumlahBuku = Integer.parseInt(stok);
                    bukuModel.insertBuku(judul, genre, penulis, penerbit, lokasi, jumlahBuku);
                    vTambahBuku.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Gagal tambah buku jumlah buku harus berupa angka");
                }
                buku = bukuModel.readBuku();
                vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                vAdmin.aturKolomBuku();
            });
        });

        vAdmin.btnHapus.addActionListener(actionEvent -> {
            String input = JOptionPane.showInputDialog("Input Id Buku yang akan dihapus");
            try {
                int hapus = Integer.parseInt(input);
                bukuModel.deleteBuku(hapus);
                buku = bukuModel.readBuku();
                vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                vAdmin.aturKolomBuku();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Gagal hapus buku id buku harus berupa angka");
            }
        });

        vAdmin.btnEdit.addActionListener(actionEvent -> {
            String[][] dataEdit;
            String input = JOptionPane.showInputDialog("Input Id Buku yang akan diubah");
            try {
                int edit = Integer.parseInt(input);
                dataEdit = bukuModel.getBukubyId(edit);
                if (dataEdit[0][0] == null) {
                    JOptionPane.showMessageDialog(null, "id buku tidak ditemukan");
                } else {
                    dataEdit = bukuModel.getBukubyId(edit);
                    VTambahBuku vEditBuku = new VTambahBuku("UBAH");
                    vEditBuku.tfJudul.setText(dataEdit[0][1]);
                    vEditBuku.tfGenre.setText(dataEdit[0][2]);
                    vEditBuku.tfPenulis.setText(dataEdit[0][3]);
                    vEditBuku.tfPenerbit.setText(dataEdit[0][4]);
                    vEditBuku.tfLokasi.setText(dataEdit[0][5]);
                    vEditBuku.tfStok.setText(dataEdit[0][6]);
                    vEditBuku.btnTambah.addActionListener(actionEvent1 -> {
                        String judul = vEditBuku.tfJudul.getText();
                        String genre = vEditBuku.tfGenre.getText();
                        String penulis = vEditBuku.tfPenulis.getText();
                        String penerbit = vEditBuku.tfPenerbit.getText();
                        String lokasi = vEditBuku.tfLokasi.getText();
                        String stok = vEditBuku.tfStok.getText();
                        int jum = Integer.parseInt(stok);
                        bukuModel.editBuku(edit, judul,genre,penulis,penerbit,lokasi,jum);

                        vEditBuku.dispose();
                        buku = bukuModel.readBuku();
                        vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                        vAdmin.aturKolomBuku();
                    });
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "id buku harus berupa angka");
            }
        });

        allPageButton();

    }

    private void isiFilterBuku(VCariBuku vCariBuku, String kolom) {
        for (int i = 0; i < bukuModel.readBukuFilter(kolom).size(); i++)
            vCariBuku.cbFilter.addItem(bukuModel.readBukuFilter(kolom).get(i));
    }

    private void allPageButton() {
        vAdmin.btnLogout.addActionListener(actionEvent -> {
            vAdmin.dispose();
            VHome vHome = new VHome();
            BukuModel bukuModel = new BukuModel();
            HomeController homeController = new HomeController(vHome, bukuModel);
            JOptionPane.showMessageDialog(null, "Terimakasih, anda berhasil logout!");
        });
        vAdmin.btnUser.addActionListener(actionEvent -> {
            halamanUser();
        });
        vAdmin.btnAnggota.addActionListener(actionEvent -> {
            halamanAnggota();
        });
        vAdmin.btnBuku.addActionListener(actionEvent -> {
            halamanBuku();
        });
        vAdmin.btnPinjam.addActionListener(actionEvent -> {
            halamanPinjam();
        });
    }

    private void halamanUser() {

        VAdmin.halaman = "user";
        vAdmin.dispose();
        this.vAdmin = new VAdmin();
        vAdmin.btnUser.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        UserModel userModel = new UserModel();
        if (userModel.getBanyakUser() != 0) {
            user = userModel.readUser();

            vAdmin.setTitle("User");
            vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
            vAdmin.aturKolomUser();
        }

        vAdmin.btnCari.addActionListener(actionEvent -> {
            VCariUser cari = new VCariUser();
            cari.btnGo.addActionListener(actionEvent1 -> {
                if (cari.radio1.isSelected()) {
                    kolom = "nama";
                } else if (cari.radio2.isSelected()) {
                    kolom = "username";
                } else if (cari.radio3.isSelected()) {
                    kolom = "level";
                } else {
                    kolom = "nama";
                }
                if (cari.tfCari.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Input User yang Dicari");
                } else {
                    user = userModel.searchUser(cari.tfCari.getText(), kolom);
                    if (user == null) {
                        JOptionPane.showMessageDialog(null, "Maaf, User yang anda cari tidak ada!");
                    } else {
                        vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
                        vAdmin.aturKolomUser();
                    }
                }
            });
        });

        vAdmin.btnTampil.addActionListener(actionEvent -> {
            user = userModel.readUser();
            vAdmin.setTitle("User");
            vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
            vAdmin.aturKolomUser();
        });

        vAdmin.btnTambah.addActionListener(actionEvent -> {
            VTambahUser tambahUser = new VTambahUser("Tambah");
            tambahUser.btnTambah.addActionListener(actionEvent1 -> {
                String nama = tambahUser.tNama.getText();
                String username = tambahUser.tUsername.getText();
                String password = tambahUser.tPassword.getText();
                try {
                    userModel.insertUser(nama, username, password, "Admin");
                    tambahUser.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                user = userModel.readUser();
                vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
                vAdmin.aturKolomUser();
            });
        });

        vAdmin.btnHapus.addActionListener(actionEvent -> {
            String input = JOptionPane.showInputDialog("Input Id User yang akan dihapus");
            try {
                int hapus = Integer.parseInt(input);
                userModel.deleteUser(hapus);
                user = userModel.readUser();
                vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
                vAdmin.aturKolomUser();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Gagal hapus User id buku harus berupa angka");
            }
        });

        vAdmin.btnEdit.addActionListener(actionEvent -> {
            String[][] dataEdit;
            String input = JOptionPane.showInputDialog("Input Id User yang akan diubah");
            try {
                int edit = Integer.parseInt(input);
                dataEdit = userModel.getUserbyId(edit);
                if (dataEdit[0][0] == null) {
                    JOptionPane.showMessageDialog(null, "id User tidak ditemukan");
                } else {
                    dataEdit = userModel.getUserbyId(edit);
                    VTambahUser vEditUser = new VTambahUser("Ubah");
                    vEditUser.tNama.setText(dataEdit[0][1]);
                    vEditUser.tUsername.setText(dataEdit[0][2]);
                    vEditUser.tPassword.setText(dataEdit[0][3]);
                    vEditUser.btnTambah.addActionListener(actionEvent1 -> {
                        String nama = vEditUser.tNama.getText();
                        String username = vEditUser.tUsername.getText();
                        String password = vEditUser.tPassword.getText();
                        userModel.editUser(edit,nama,username,password,"Admin");

                        vEditUser.dispose();
                        user = userModel.readUser();
                        vAdmin.table.setModel(new JTable(user, KOLOM_USER).getModel());
                        vAdmin.aturKolomUser();
                    });
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "id User harus berupa angka");
            }
        });

        allPageButton();
    }


    private void halamanAnggota() {

        VAdmin.halaman = "anggota";
        vAdmin.dispose();
        this.vAdmin = new VAdmin();
        vAdmin.btnAnggota.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        AnggotaModel anggotaModel = new AnggotaModel();

        if (anggotaModel.getBanyakAnggota() != 0) {
            anggota = anggotaModel.readAnggota();
            vAdmin.setTitle("ANGGOTA");
            vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
            vAdmin.aturKolomAnggota();
        }

        vAdmin.btnFilter.addActionListener(actionEvent -> {
            VCariAnggota cari = new VCariAnggota("FILTER");
            cari.radio2.isSelected();
            kolom = "gender";
            isiFilterAnggota(anggotaModel, cari, kolom);

            cari.radio2.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "gender";
                isiFilterAnggota(anggotaModel, cari, kolom);
            });
            cari.radio3.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "alamat";
                isiFilterAnggota(anggotaModel, cari, kolom);
            });
            cari.radio4.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "umur";
                isiFilterAnggota(anggotaModel, cari, kolom);
            });
            cari.radio5.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "pendidikan";
                isiFilterAnggota(anggotaModel, cari, kolom);
            });

            cari.btnGo.addActionListener(actionEvent1 -> {
                anggota = anggotaModel.searchAnggota(Objects.requireNonNull(cari.cbFilter.getSelectedItem()).toString(), kolom);
                if (anggota == null) {
                    JOptionPane.showMessageDialog(null, "Maaf, Anggota yang anda filter tidak ada!");
                } else {
                    vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
                    vAdmin.aturKolomAnggota();
                }
            });
        });

        vAdmin.btnCari.addActionListener(actionEvent -> {
            VCariAnggota cari = new VCariAnggota("CARI");
            cari.btnGo.addActionListener(actionEvent1 -> {
                if (cari.radio1.isSelected()) {
                    kolom = "nama";
                } else if (cari.radio3.isSelected()) {
                    kolom = "alamat";
                } else if (cari.radio4.isSelected()) {
                    kolom = "umur";
                } else if (cari.radio5.isSelected()) {
                    kolom = "pendidikan";
                } else {
                    kolom = "nama";
                }
                if (cari.tfCari.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Input Anggota yang Dicari");
                } else {
                    anggota = anggotaModel.searchAnggota(cari.tfCari.getText(), kolom);
                    if (anggota == null) {
                        JOptionPane.showMessageDialog(null, "Maaf, Anggota yang anda cari tidak ada!");
                    } else {
                        vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
                        vAdmin.aturKolomAnggota();
                    }
                }
            });
        });

        vAdmin.btnTampil.addActionListener(actionEvent -> {
            anggota = anggotaModel.readAnggota();
            vAdmin.setTitle("Anggota");
            vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
            vAdmin.aturKolomAnggota();
        });

        vAdmin.btnTambah.addActionListener(actionEvent -> {
            VTambahAnggota vTambahAnggota = new VTambahAnggota("Tambah");
            vTambahAnggota.btnTambah.addActionListener(actionEvent1 -> {
                String nama = vTambahAnggota.fnama.getText();
                String gender;
                if (vTambahAnggota.fl.isSelected())
                    gender = "Laki - Laki";
                else
                    gender = "Perempuan";
                String alamat = vTambahAnggota.falamat.getText();
                String umur = vTambahAnggota.fumur.getText();
                String pendidikan = vTambahAnggota.fpendidikan.getText();
                int umuri;
                try {
                    umuri = Integer.parseInt(umur);
                    anggotaModel.insertAnggota(nama, gender, alamat, umuri, pendidikan);
                    vTambahAnggota.dispose();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    JOptionPane.showMessageDialog(null, "Gagal tambah anggota jumlah buku harus berupa angka");
                }
                anggota = anggotaModel.readAnggota();
                vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
                vAdmin.aturKolomAnggota();
            });
        });

        vAdmin.btnHapus.addActionListener(actionEvent -> {
            String input = JOptionPane.showInputDialog("Input Id Anggota yang akan dihapus");
            try {
                int hapus = Integer.parseInt(input);
                anggotaModel.deleteAnggota(hapus);
                anggota = anggotaModel.readAnggota();
                vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
                vAdmin.aturKolomAnggota();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Gagal hapus Anggota id buku harus berupa angka");
            }
        });

        vAdmin.btnEdit.addActionListener(actionEvent -> {
            String[][] dataEdit;
            String input = JOptionPane.showInputDialog("Input Id Anggota yang akan diubah");
            try {
                int edit = Integer.parseInt(input);
                dataEdit = anggotaModel.getAnggotabyId(edit);
                if (dataEdit[0][0] == null) {
                    JOptionPane.showMessageDialog(null, "id Anggota tidak ditemukan");
                } else {
                    dataEdit = anggotaModel.getAnggotabyId(edit);
                    VTambahAnggota vEditAnggota = new VTambahAnggota("Ubah");
                    vEditAnggota.fnama.setText(dataEdit[0][1]);
                    if (dataEdit[0][2].equalsIgnoreCase("Laki - Laki"))
                        vEditAnggota.fl.isSelected();
                    else vEditAnggota.fp.isSelected();
                    vEditAnggota.falamat.setText(dataEdit[0][3]);
                    vEditAnggota.fumur.setText(dataEdit[0][4]);
                    vEditAnggota.fpendidikan.setText(dataEdit[0][5]);
                    vEditAnggota.btnTambah.addActionListener(actionEvent1 -> {
                        String nama = vEditAnggota.fnama.getText();
                        String gender = vEditAnggota.buttonGroup.getSelection().getActionCommand();
                        String alamat = vEditAnggota.falamat.getText();
                        String umur = vEditAnggota.fumur.getText();
                        String pendidikan = vEditAnggota.fpendidikan.getText();
                        int um = Integer.parseInt(umur);
                        anggotaModel.editAnggota(edit, nama, gender, alamat, um, pendidikan);

                        vEditAnggota.dispose();
                        anggota = anggotaModel.readAnggota();
                        vAdmin.table.setModel(new JTable(anggota, KOLOM_ANGGOTA).getModel());
                        vAdmin.aturKolomAnggota();
                    });
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "id anggota harus berupa angka");
            }
        });

        allPageButton();
    }


    private void halamanPinjam() {
        VAdmin.halaman = "pinjam";
        vAdmin.dispose();
        this.vAdmin = new VAdmin();
        vAdmin.btnPinjam.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        PinjamModel pinjamModel = new PinjamModel();

        if (pinjamModel.getBanyakPinjam() != 0) {
            pinjam = pinjamModel.readPinjam();
            vAdmin.setTitle("PEMINJAMAN");
            vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
            vAdmin.aturKolomPinjam();
        }

        vAdmin.btnFilter.addActionListener(actionEvent -> {
            VCariPinjam cari = new VCariPinjam("FILTER");
            cari.radio1.isSelected();
            kolom = "buku.judul";
            isiFilterPinjam(pinjamModel, cari, kolom);

            cari.radio1.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "buku.judul";
                isiFilterPinjam(pinjamModel, cari, kolom);
            });
            cari.radio2.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "anggota.nama";
                isiFilterPinjam(pinjamModel, cari, kolom);
            });
            cari.radio3.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "anggota.alamat";
                isiFilterPinjam(pinjamModel, cari, kolom);
            });
            cari.radio4.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "pinjam.tgl_pinjam";
                isiFilterPinjam(pinjamModel, cari, kolom);
            });
            cari.radio5.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "pinjam.status";
                isiFilterPinjam(pinjamModel, cari, kolom);
            });

            cari.btnGo.addActionListener(actionEvent1 -> {
                pinjam = pinjamModel.searchPinjam(Objects.requireNonNull(cari.cbFilter.getSelectedItem()).toString(), kolom);
                if (pinjam == null) {
                    JOptionPane.showMessageDialog(null, "Maaf, Data Pinjam yang anda filter tidak ada!");
                } else {
                    vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                    vAdmin.aturKolomPinjam();
                }
            });
        });

        vAdmin.btnCari.addActionListener(actionEvent -> {
            VCariPinjam cari = new VCariPinjam("CARI");
            cari.btnGo.addActionListener(actionEvent1 -> {
                if (cari.radio1.isSelected()) {
                    kolom = "buku.judul";
                } else if (cari.radio2.isSelected()) {
                    kolom = "anggota.nama";
                } else if (cari.radio3.isSelected()) {
                    kolom = "anggota.alamat";
                } else if (cari.radio4.isSelected()) {
                    kolom = "pinjam.tgl_pinjam";
                } else if (cari.radio5.isSelected()) {
                    kolom = "pinjam.status";
                } else {
                    kolom = "buku.judul";
                }
                if (cari.tfCari.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Input Data Pinjam yang Dicari");
                } else {
                    pinjam = pinjamModel.searchPinjam(cari.tfCari.getText(), kolom);
                    if (pinjam == null) {
                        JOptionPane.showMessageDialog(null, "Maaf, Data Pinjam yang anda cari tidak ada!");
                    } else {
                        vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                        vAdmin.aturKolomPinjam();
                    }
                }
            });
        });

        vAdmin.btnTampil.addActionListener(actionEvent -> {
            pinjam = pinjamModel.readPinjam();
            vAdmin.setTitle("PEMINJAMAN");
            vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
            vAdmin.aturKolomPinjam();
        });

        vAdmin.btnHapus.addActionListener(actionEvent -> {
            String input = JOptionPane.showInputDialog("Input Id data pinjam yang akan dihapus");
            try {
                int hapus = Integer.parseInt(input);
                pinjamModel.deletePinjam(hapus);
                pinjam = pinjamModel.readPinjam();
                vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                vAdmin.aturKolomPinjam();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "Gagal hapus data pinjam id buku harus berupa angka");
            }
        });

        vAdmin.btnTambah.addActionListener(actionEvent -> {
            VTambahPinjam vTambahPinjam = new VTambahPinjam("Tambah");

            BukuModel bukuModel = new BukuModel();
            AnggotaModel anggotaModel = new AnggotaModel();
            buku = bukuModel.readBuku();
            anggota = anggotaModel.readAnggota();
            for (String[] strings : buku) {
                vTambahPinjam.tJudul.addItem(strings[1]);
            }
            for (String[] strings : anggota) {
                vTambahPinjam.tNama.addItem(strings[1]);
            }
            vTambahPinjam.btnTambah.addActionListener(actionEvent1 -> {
                String nama = Objects.requireNonNull(vTambahPinjam.tNama.getSelectedItem()).toString();
                String judul = Objects.requireNonNull(vTambahPinjam.tJudul.getSelectedItem()).toString();
                int id_anggota = 0, id_buku = 0;
                for (String[] strings : buku) {
                    if (strings[1].equals(judul))
                        id_buku = Integer.parseInt(strings[0]);
                }
                for (String[] strings : anggota) {
                    if (strings[1].equals(nama))
                        id_anggota = Integer.parseInt(strings[0]);
                }
                String tgl = vTambahPinjam.tTanggal.getText();
                String status = vTambahPinjam.tStatus.getText();

                pinjamModel.insertPinjam(id_anggota, id_buku, tgl, status);

                pinjam = pinjamModel.readPinjam();
                vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                vAdmin.aturKolomPinjam();
            });
        });
        vAdmin.btnEdit.addActionListener(actionEvent -> {
            String[][] dataEdit;
            String input = JOptionPane.showInputDialog("Input Id Pinjam yang akan diubah");
            try {
                int edit = Integer.parseInt(input);
                dataEdit = pinjamModel.getPinjambyId(edit);
                if (dataEdit[0][0] == null) {
                    JOptionPane.showMessageDialog(null, "id Pinjam tidak ditemukan");
                } else {
                    dataEdit = pinjamModel.getPinjambyId(edit);
                    VTambahPinjam vEdit = new VTambahPinjam("Ubah");
                    BukuModel bukuModel = new BukuModel();
                    AnggotaModel anggotaModel = new AnggotaModel();
                    buku = bukuModel.readBuku();
                    anggota = anggotaModel.readAnggota();
                    for (String[] strings : buku) {
                        vEdit.tJudul.addItem(strings[1]);
                    }
                    for (String[] strings : anggota) {
                        vEdit.tNama.addItem(strings[1]);
                    }
                    vEdit.tTanggal.setText(dataEdit[0][3]);
                    vEdit.tStatus.setText(dataEdit[0][4]);
                    vEdit.btnTambah.addActionListener(actionEvent1 -> {
                        String nama = Objects.requireNonNull(vEdit.tNama.getSelectedItem()).toString();
                        String judul = Objects.requireNonNull(vEdit.tJudul.getSelectedItem()).toString();
                        int id_anggota = 0, id_buku = 0;
                        for (String[] strings : buku) {
                            if (strings[1].equals(judul))
                                id_buku = Integer.parseInt(strings[0]);
                        }
                        for (String[] strings : anggota) {
                            if (strings[1].equals(nama))
                                id_anggota = Integer.parseInt(strings[0]);
                        }
                        String tgl = vEdit.tTanggal.getText();
                        String status = vEdit.tStatus.getText();

                        pinjamModel.editPinjam(edit,id_anggota, id_buku, tgl, status);

                        pinjam = pinjamModel.readPinjam();
                        vAdmin.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                        vAdmin.aturKolomPinjam();
                    });
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                JOptionPane.showMessageDialog(null, "id Pinjam harus berupa angka");
            }
        });

        allPageButton();
    }

    private void isiFilterAnggota(AnggotaModel anggotaModel, VCariAnggota vCariAnggota, String kolom) {
        for (int i = 0; i < anggotaModel.readAnggotaFilter(kolom).size(); i++)
            vCariAnggota.cbFilter.addItem(anggotaModel.readAnggotaFilter(kolom).get(i));
    }

    private void isiFilterPinjam(PinjamModel pinjamModel, VCariPinjam vCariPinjam, String kolom) {
        for (int i = 0; i < pinjamModel.readPinjamFilter(kolom).size(); i++)
            vCariPinjam.cbFilter.addItem(pinjamModel.readPinjamFilter(kolom).get(i));
    }

}
