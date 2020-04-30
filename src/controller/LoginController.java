package controller;

import javafx.css.CssParser;
import model.UserModel;
import view.VLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    UserModel model;
    VLogin view;

    public LoginController(final UserModel model, final VLogin view) {
        this.view = view;
        this.model = model;
        view.btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String username = view.getUsername();
                String pass = view.getPassword();
                if (model.cekLogin(username, pass)){
                    view.dispose();
                    PerpusController perpusController = new PerpusController();
                }else{
                    JOptionPane.showMessageDialog(null, "Silahkan Ulangi Kembali");
                }
            }
        });
    }
}
