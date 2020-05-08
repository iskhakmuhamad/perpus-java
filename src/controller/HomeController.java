package controller;

import model.BukuModel;
import model.UserModel;
import view.VHome;
import view.VLogin;

import javax.swing.*;


import java.util.Arrays;

import static utils.ColumnUtils.KOLOM_BUKU;

public class HomeController {
    String cari;
    String[][] buku;
    BukuModel model;
    VHome view;

    public HomeController(VHome view, BukuModel model) {
        if (model.getBanyakBuku() != 0) {
            buku = model.readBuku();
            view.setTitle("BUKU");
            view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
            view.aturKolomBuku();

            for (int i = 0; i < model.readBukuGenre().size(); i++) {
                //mengambil genre dari database
                view.cbFilter.addItem(model.readBukuGenre().get(i));
            }
        }

        view.btnCari.addActionListener(actionEvent -> {
            buku = model.searchBuku(view.tfCari.getText(), "judul");
            if (buku == null) {
                JOptionPane.showMessageDialog(null, "Maaf, Buku yang anda cari tidak ada!");
            } else {
                view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                view.aturKolomBuku();
            }
        });

        view.btnFilter.addActionListener(actionEvent -> {
            if (view.cbFilter.getSelectedItem() != null){
                String item = view.cbFilter.getSelectedItem().toString();
                buku = model.searchBuku(item,"genre");
                view.table.setModel(new JTable(buku, KOLOM_BUKU).getModel());
                view.aturKolomBuku();
            }else {
                JOptionPane.showMessageDialog(null, "Silahkan Pilih Genre Buku");
            }
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
            LoginController controller = new LoginController(userModel,vLogin);
        });


    }


}
