package controller;

import model.BukuModel;
import model.UserModel;
import view.VCari;
import view.VHome;
import view.VLogin;

import javax.swing.*;
import java.util.Objects;

import static utils.ColumnUtils.KOLOM_BUKU;

@SuppressWarnings("ALL")
public class HomeController {
    String[][] buku;
    BukuModel model;
    VHome view;
    String kolom = "";

    public HomeController(VHome view, BukuModel model) {
        if (model.getBanyakBuku() != 0) {
            buku = model.readBuku();
            view.setTitle("BUKU");
            view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            view.aturKolomBuku();

        }


        view.btnCari.addActionListener(actionEvent -> {
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
                buku = model.searchBuku(cari.tfCari.getText(), kolom);
                if (buku == null) {
                    JOptionPane.showMessageDialog(null, "Maaf, Buku yang anda cari tidak ada!");
                } else {
                    view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                    view.aturKolomBuku();
                }
            });
        });

        view.btnFilter.addActionListener(actionEvent -> {
            VCari cari = new VCari("FILTER");

            //mengisi nilai default pada combo box
            cari.radio2.isSelected();
            kolom = "genre";
            for (int i = 0; i < model.readBukuFilter(kolom).size(); i++)
                cari.cbFilter.addItem(model.readBukuFilter(kolom).get(i));

            //mengisi nilai pada combo box
            cari.radio2.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "genre";
                for (int i = 0; i < model.readBukuFilter("genre").size(); i++)
                    cari.cbFilter.addItem(model.readBukuFilter("genre").get(i));
            });
            cari.radio3.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "penulis";
                for (int i = 0; i < model.readBukuFilter("penulis").size(); i++)
                    cari.cbFilter.addItem(model.readBukuFilter("penulis").get(i));
            });
            cari.radio4.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "penerbit";
                for (int i = 0; i < model.readBukuFilter("penerbit").size(); i++)
                    cari.cbFilter.addItem(model.readBukuFilter("penerbit").get(i));
            });
            cari.radio5.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "lokasi";
                for (int i = 0; i < model.readBukuFilter("lokasi").size(); i++)
                    cari.cbFilter.addItem(model.readBukuFilter("lokasi").get(i));
            });
            cari.radio6.addActionListener(actionEvent1 -> {
                cari.cbFilter.removeAllItems();
                kolom = "stok";
                for (int i = 0; i < model.readBukuFilter("stok").size(); i++)
                    cari.cbFilter.addItem(model.readBukuFilter("stok").get(i));
            });


            cari.btnGo.addActionListener(actionEvent1 -> {
                buku = model.searchBuku(Objects.requireNonNull(cari.cbFilter.getSelectedItem()).toString(), kolom);
                if (buku == null) {
                    JOptionPane.showMessageDialog(null, "Maaf, Buku yang anda filter tidak ada!");
                } else {
                    view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                    view.aturKolomBuku();
                }
            });
        });


        view.btnTampil.addActionListener(actionEvent -> {
            buku = model.readBuku();
            view.setTitle("BUKU");
            view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            view.aturKolomBuku();
        });

        view.btnLogin.addActionListener(actionEvent -> {
            VLogin vLogin = new VLogin();
            UserModel userModel = new UserModel();
            LoginController controller = new LoginController(userModel, vLogin);
        });


    }

}
