package controller;

import model.BukuModel;
import model.UserModel;
import view.VAdmin;
import view.VLogin;

import javax.swing.*;

public class LoginController {

    UserModel model;
    VLogin view;

    public LoginController(final UserModel model, final VLogin view) {
        this.view = view;
        this.model = model;
        view.btnLogin.addActionListener(actionEvent -> {
            String username = view.getUsername();
            String pass = view.getPassword();
            if (model.cekLogin(username, pass)) {
                view.dispose();
                VAdmin viewAdmin = new VAdmin();
                BukuModel bukuModel = new BukuModel();
                AdminController controller = new AdminController(viewAdmin, bukuModel);
                JOptionPane.showMessageDialog(null, "Selamat Anda Berhasil ogin sebgai admin");
            } else {
                JOptionPane.showMessageDialog(null, "Silahkan Ulangi Kembali");
                view.dispose();
            }
        });
    }
}
