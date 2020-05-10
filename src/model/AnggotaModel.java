package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

import static utils.DbUtils.*;

public class AnggotaModel {
    Connection koneksi;
    Statement statement;

    public AnggotaModel() {
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }

    public String[][] readAnggota() {
        try {
            int jmlData = 0;//menampung jumlah data

            String[][] data = new String[getBanyakAnggota()][6];

            String query = "SELECT * FROM anggota ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = resultSet.getString("id_anggota");
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("gender");
                data[jmlData][3] = resultSet.getString("alamat");
                data[jmlData][4] = resultSet.getString("umur");
                data[jmlData][5] = resultSet.getString("pendidikan");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public int getBanyakAnggota() {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from `anggota`"; //pengambilan data dalam java dari database
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

    public int getBanyakAnggotaCari(String keyword, String kolom) {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM anggota WHERE " + kolom + " LIKE " + keyword;
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

    public String[][] searchAnggota(String keyword, String kolom) {
        try {
            int jmlData = 0;//menampung jumlah data
            keyword = "'%" + keyword + "%'";
            String query = "SELECT * FROM anggota WHERE " + kolom + " LIKE " + keyword;
            String[][] data = new String[getBanyakAnggotaCari(keyword, kolom)][6];

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("id_anggota");
                data[jmlData][1] = resultSet.getString("nama");
                data[jmlData][2] = resultSet.getString("gender");
                data[jmlData][3] = resultSet.getString("alamat");
                data[jmlData][4] = resultSet.getString("umur");
                data[jmlData][5] = resultSet.getString("pendidikan");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public ArrayList<String> readAnggotaFilter(String kolom) {
        try {

            ArrayList<String> data = new ArrayList<>();

            String query = "SELECT DISTINCT " + kolom + " FROM anggota ";
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

    public void insertAnggota(String nama, String gender, String alamat, int umur, String pendidikan) {
        try {
            String query = "INSERT INTO `anggota` (`nama`, `gender`, `alamat` , `umur` , `pendidikan` ) " +
                    "VALUES ('" + nama + "','" + gender + "','" + alamat + "','" + umur + "' ,'" + pendidikan + "' ) ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }

    public void editAnggota(int id, String nama, String gender, String alamat, int umur, String pendidikan) {
        try {
            String query = "UPDATE anggota SET nama = " + "'" + nama + "', gender = " + "'" + gender + "', alamat = " +
                    "'" + alamat + "', umur = " + "'" + umur + "', pendidikan = " + "'" + pendidikan
                    + "' "  + "WHERE id_anggota = " + id;
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil diubah");
            JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
        }
    }

    public String[][] getAnggotabyId(int id) {
        try {
            String query = "SELECT * FROM anggota WHERE id_anggota = " + "'" + id + "'";
            String[][] data = new String[1][6];
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                data[0][0] = resultSet.getString("id_anggota");
                data[0][1] = resultSet.getString("nama");
                data[0][2] = resultSet.getString("gender");
                data[0][3] = resultSet.getString("alamat");
                data[0][4] = resultSet.getString("umur");
                data[0][5] = resultSet.getString("pendidikan");
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }

    }

    public void deleteAnggota(int id) {
        try {
            String query = "DELETE FROM `anggota` WHERE `id_anggota` = '" + id + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
}
