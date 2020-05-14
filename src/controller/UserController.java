package controller;

import model.BukuModel;
import model.PinjamModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static utils.ColumnUtils.KOLOM_BUKU;
import static utils.ColumnUtils.KOLOM_PINJAM;

@SuppressWarnings("ALL")
public class UserController {
    String[][] buku,pinjam;
    VUser view;
    BukuModel bukuModel;
    String kolom = "";

    public UserController(VUser view, BukuModel model) {
        this.view = view;
        this.bukuModel = model;
        halamanBuku();
    }

    private void allPageButton() {
        //Navigasi Halaman
        view.btnLogout.addActionListener(actionEvent -> {
            view.dispose();
            VHome vHome = new VHome();
            BukuModel bukuModel = new BukuModel();
            HomeController homeController = new HomeController(vHome, bukuModel);
            JOptionPane.showMessageDialog(null, "Terimakasih, anda berhasil logout!");
        });
        view.btnBuku.addActionListener(actionEvent -> {
            halamanBuku();
        });
        view.btnPinjam.addActionListener(actionEvent -> {
            halamanPinjam();
        });
    }

    private void halamanPinjam() {
        VUser.halaman = "pinjam";
        view.dispose();
        this.view = new VUser();
        view.btnPinjam.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));
        PinjamModel pinjamModel = new PinjamModel();

        if (pinjamModel.getBanyakPinjam() != 0) {
            pinjam = pinjamModel.readPinjam();
            view.setTitle("PEMINJAMAN");
            view.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
            view.aturKolomPinjam();
        }

        view.btnFilter.addActionListener(actionEvent -> {
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
                    view.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                    view.aturKolomPinjam();
                }
            });
        });

        view.btnCari.addActionListener(actionEvent -> {
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
                        view.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
                        view.aturKolomPinjam();
                    }
                }
            });
        });

        view.btnTampil.addActionListener(actionEvent -> {
            pinjam = pinjamModel.readPinjam();
            view.setTitle("PEMINJAMAN");
            view.table.setModel(new JTable(pinjam, KOLOM_PINJAM).getModel());
            view.aturKolomPinjam();
        });

        allPageButton();
    }

    public void halamanBuku() {

        VAdmin.halaman = "buku";
        view.dispose();
        this.view = new VUser();
        view.btnBuku.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2, true));

        if (bukuModel.getBanyakBuku() != 0) {
            buku = bukuModel.readBuku();
            view.setTitle("BUKU");
            view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            view.aturKolomBuku();
        }

        view.btnFilter.addActionListener(actionEvent -> {
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
                    view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                    view.aturKolomBuku();
                }
            });
        });

        view.btnCari.addActionListener(actionEvent -> {
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
                        view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                        view.aturKolomBuku();
                    }
                }
            });
        });

        view.btnTampil.addActionListener(actionEvent -> {
            buku = bukuModel.readBuku();
            view.setTitle("BUKU");
            view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            view.aturKolomBuku();
        });

        allPageButton();
    }


    private void isiFilterBuku(VCariBuku vCariBuku, String kolom) {
        for (int i = 0; i < bukuModel.readBukuFilter(kolom).size(); i++)
            vCariBuku.cbFilter.addItem(bukuModel.readBukuFilter(kolom).get(i));
    }

    private void isiFilterPinjam(PinjamModel pinjamModel, VCariPinjam vCariPinjam, String kolom) {
        for (int i = 0; i < pinjamModel.readPinjamFilter(kolom).size(); i++)
            vCariPinjam.cbFilter.addItem(pinjamModel.readPinjamFilter(kolom).get(i));
    }
}