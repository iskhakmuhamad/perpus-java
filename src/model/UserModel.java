package model;

import javax.swing.*;
import java.sql.*;

import static utils.DbUtils.*;

public class UserModel {

    Connection koneksi;
    Statement statement;//untuk perintah query

    public UserModel() {
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }

    public boolean cekLogin(String username, String pass) {
        String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + pass + "'";
        boolean login = false;
        try {
            statement = koneksi.createStatement();
            ResultSet rsLogin = statement.executeQuery(query);

            rsLogin.next();
            rsLogin.last(); //mengecek jumlah baris pada hasil query
            if (rsLogin.getRow() == 1) {
                JOptionPane.showMessageDialog(null, "Login Berhasil !");
                login = true;
            } else {
                JOptionPane.showMessageDialog(null, "Maaf, Username / Password salah!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return login;
    }


}
