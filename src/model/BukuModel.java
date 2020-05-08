package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

import static utils.DbUtils.*;

public class BukuModel {

    Connection koneksi;
    Statement statement;//untuk perintah query

    public BukuModel() {
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }

    public String[][] readBuku() {
        try {
            int jmlData = 0;//menampung jumlah data

            String[][] data = new String[getBanyakBuku()][7];

            String query = "Select * from buku "; //pengambilan dara dalam java dari database
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("id_buku"); //kolom nama harus sama besar kecilnya dgn database
                data[jmlData][1] = resultSet.getString("judul");
                data[jmlData][2] = resultSet.getString("genre");
                data[jmlData][3] = resultSet.getString("penulis");
                data[jmlData][4] = resultSet.getString("penerbit");
                data[jmlData][5] = resultSet.getString("lokasi");
                data[jmlData][6] = resultSet.getString("stok");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public int getBanyakBuku() {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from `buku`"; //pengambilan data dalam java dari database
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                jmlData++;
            }
            return jmlData;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("SQL Error");
            return 0;
        }
    }

    public int getBanyakBukuCari(String keyword, String kolom) {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM buku WHERE " + kolom + " LIKE " + keyword;
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

    public String[][] searchBuku(String keyword, String kolom) {
        try {
            int jmlData = 0;//menampung jumlah data
            keyword = "'%" + keyword + "%'";
            String query = "SELECT * FROM buku WHERE " + kolom + " LIKE " + keyword; //pengambilan dara dalam java dari database
            String[][] data = new String[getBanyakBukuCari(keyword,kolom)][7];

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("id_buku"); //kolom nama harus sama besar kecilnya dgn database
                data[jmlData][1] = resultSet.getString("judul");
                data[jmlData][2] = resultSet.getString("genre");
                data[jmlData][3] = resultSet.getString("penulis");
                data[jmlData][4] = resultSet.getString("penerbit");
                data[jmlData][5] = resultSet.getString("lokasi");
                data[jmlData][6] = resultSet.getString("stok");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public ArrayList<String> readBukuGenre() {
        try {

           ArrayList<String> data = new ArrayList<>();

            String query = "SELECT DISTINCT genre FROM buku "; //pengambilan dara dalam java dari database
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String gen = resultSet.getString("genre");
                data.add(gen);
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

}
