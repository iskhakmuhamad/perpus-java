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
            String[][] data = new String[getBanyakBukuCari(keyword, kolom)][7];

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

    public ArrayList<String> readBukuFilter(String kolom) {
        try {

            ArrayList<String> data = new ArrayList<>();

            String query = "SELECT DISTINCT "+ kolom +" FROM buku ";
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

    public void insertBuku(String judul, String genre, String penulis, String penerbit, String lokasi, int stok) {
        try {
            String query = "INSERT INTO `buku` (`judul`, `genre`, `penulis` , `penerbit` , `lokasi` , `stok` ) " +
                    "VALUES ('" + judul + "','" + genre + "','" + penulis + "','" + penerbit + "' ,'" + lokasi + "' ,'" + stok + "') ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void editBuku(int id, String judul, String genre, String penulis, String penerbit, String lokasi, int stok) {
        try {
            String query = "UPDATE buku SET judul = " + "'" + judul + "', genre = " + "'" + genre + "', penulis = " +
                    "'" + penulis + "', penerbit = " + "'" + penerbit + "', lokasi = " + "'" + lokasi
                    + "', stok = " + "'" + stok + "' " + "WHERE id_buku = " + id;
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Buku Berhasil diubah");
            JOptionPane.showMessageDialog(null, "Buku Berhasil diubah");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
        }
    }

    public String[][] getBukubyId(int id) {
        try {
            String query = "SELECT * FROM buku WHERE id_buku = " + "'" + id + "'";
            String[][] data = new String[1][7];
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                data[0][0] = resultSet.getString("id_buku");
                data[0][1] = resultSet.getString("judul");
                data[0][2] = resultSet.getString("genre");
                data[0][3] = resultSet.getString("penulis");
                data[0][4] = resultSet.getString("penerbit");
                data[0][5] = resultSet.getString("lokasi");
                data[0][6] = resultSet.getString("stok");
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }

    }

    public void deleteBuku(int id) {
        try {
            String query = "DELETE FROM `buku` WHERE `id_buku` = '" + id + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }

}
