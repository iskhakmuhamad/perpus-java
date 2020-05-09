package controller;

import model.BukuModel;
import view.VAdmin;
import view.VCari;
import view.VHome;
import view.VTambahBuku;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static utils.ColumnUtils.KOLOM_BUKU;

public class AdminController {
    VAdmin vAdmin;
    BukuModel bukuModel;
    String[][] buku;
    String kolom;

    public AdminController(VAdmin vAdmin, BukuModel bukuModel) {
        this.vAdmin = vAdmin;
        this.bukuModel = bukuModel;
        halamanBuku();
    }

    public void halamanBuku() {

        vAdmin.btnBuku.setBackground(Color.LIGHT_GRAY);
        if (bukuModel.getBanyakBuku() != 0) {
            buku = bukuModel.readBuku();
            vAdmin.setTitle("BUKU");
            vAdmin.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            vAdmin.aturKolomBuku();
        }

        vAdmin.btnFilter.addActionListener(actionEvent -> {
            VCari cari = new VCari("FILTER");
            cari.radio2.isSelected();
            kolom = "genre";
            isiFilter(cari, kolom);

            cari.radio2.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "genre";
                isiFilter(cari, kolom);
            });
            cari.radio3.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "penulis";
                isiFilter(cari, kolom);
            });
            cari.radio4.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "pengarang";
                isiFilter(cari, kolom);
            });
            cari.radio5.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "lokasi";
                isiFilter(cari, kolom);
            });
            cari.radio6.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "stok";
                isiFilter(cari, kolom);
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
            VCari cari = new VCari("CARI");
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

    private void isiFilter(VCari vCari, String kolom) {
        for (int i = 0; i < bukuModel.readBukuFilter(kolom).size(); i++)
            vCari.cbFilter.addItem(bukuModel.readBukuFilter(kolom).get(i));
    }

    private void allPageButton() {
        vAdmin.btnLogout.addActionListener(actionEvent -> {
            vAdmin.dispose();
            VHome vHome = new VHome();
            BukuModel bukuModel = new BukuModel();
            HomeController homeController = new HomeController(vHome, bukuModel);
            JOptionPane.showMessageDialog(null, "Terimakasih, anda berhasil logout!");
        });
    }
}
