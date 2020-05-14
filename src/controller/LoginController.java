package controller;

import model.BukuModel;
import model.UserModel;
import view.VAdmin;
import view.VHome;
import view.VLogin;
import view.VUser;

import javax.swing.*;

public class LoginController {

    UserModel model;
    VLogin view;

    public LoginController(final UserModel model, final VLogin view , VHome vHome) {
        this.view = view;
        this.model = model;
        view.btnLogin.addActionListener(actionEvent -> {
            String username = view.getUsername();
            String pass = view.getPassword();
            if (model.cekLogin(username, pass).equalsIgnoreCase("admin")) {
                vHome.dispose();
                view.dispose();
                VAdmin viewAdmin = new VAdmin();
                BukuModel bukuModel = new BukuModel();
                AdminController controller = new AdminController(viewAdmin, bukuModel);
                JOptionPane.showMessageDialog(null, "Selamat Anda Berhasil login sebagai admin");
            } else if (model.cekLogin(username, pass).equalsIgnoreCase("user")) {
                vHome.dispose();
                view.dispose();
                VUser vUser = new VUser();
                BukuModel bukuModel = new BukuModel();
                UserController controller = new UserController(vUser, bukuModel);
                JOptionPane.showMessageDialog(null, "Selamat Anda Berhasil Login sebagai User");
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Username / Password salah!");
                JOptionPane.showMessageDialog(null, "Silahkan Ulangi Kembali");
                view.dispose();
            }
        });
    }
}
