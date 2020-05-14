package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

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

    public String cekLogin(String username, String pass) {
        String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + pass + "'";
        String level = "blum login";
        try {
            statement = koneksi.createStatement();
            ResultSet rsLogin = statement.executeQuery(query);

            rsLogin.next();
            rsLogin.last(); //mengecek jumlah baris pada hasil query
            if (rsLogin.getRow() == 1) {
                level = rsLogin.getString("level");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return level;
    }

    public String[][] readUser() {
        try {
            int jmlData = 0;//menampung jumlah data

            String[][] data = new String[getBanyakUser()][5];

            String query = "SELECT * FROM user ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("username");
                data[jmlData][3] = resultSet.getString("password");
                data[jmlData][4] = resultSet.getString("level");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public int getBanyakUser() {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from `user`";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public int getBanyakUserCari(String keyword, String kolom) {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM user WHERE " + kolom + " LIKE " + keyword;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                jmlData++;
            }
            return jmlData;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public String[][] searchUser(String keyword, String kolom) {
        try {
            int jmlData = 0;//menampung jumlah data
            keyword = "'%" + keyword + "%'";
            String query = "SELECT * FROM user WHERE " + kolom + " LIKE " + keyword;
            String[][] data = new String[getBanyakUserCari(keyword, kolom)][5];

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("id");
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("username");
                data[jmlData][3] = resultSet.getString("password");
                data[jmlData][4] = resultSet.getString("level");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public ArrayList<String> readUserFilter(String kolom) {
        try {

            ArrayList<String> data = new ArrayList<>();

            String query = "SELECT DISTINCT " + kolom + " FROM user ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String gen = resultSet.getString(kolom);
                data.add(gen);
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }


    public void insertUser(String nama, String username, String password, String level) {
        try {
            String query = "INSERT INTO `user` (`nama`, `username`, `password` , `level` ) " +
                    "VALUES ('" + nama + "','" + username + "','" + password + "','" + level + "'  ) ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, "Maaf, Username Sudah dipakai");
        }
    }

    public void editUser(int id, String nama, String username, String password, String level) {
        try {
            String query = "UPDATE user SET nama = " + "'" + nama + "', username = " + "'" + username + "', password = " +
                    "'" + password + "', level = " + "'" + level + "' " + "WHERE id = " + id;
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil diubah");
            JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, "Maaf, Username Sudah dipakai");
        }
    }

    public String[][] getUserbyId(int id) {
        try {
            String query = "SELECT * FROM user WHERE id = " + "'" + id + "'";
            String[][] data = new String[1][5];
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                data[0][0] = resultSet.getString("id");
                data[0][1] = resultSet.getString("nama");
                data[0][2] = resultSet.getString("username");
                data[0][3] = resultSet.getString("password");
                data[0][4] = resultSet.getString("level");
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }

    }

    public void deleteUser(int id) {
        try {
            String query = "DELETE FROM `user` WHERE `id` = '" + id + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }


}
