package model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

import static utils.DbUtils.*;

public class PinjamModel {

    Connection koneksi;
    Statement statement;//untuk perintah query

    public PinjamModel() {
        try {
            Class.forName(JDBC_DRIVER);
            koneksi = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Koneksi Berhasil");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.out.println("Koneksi Gagal");
        }
    }

    public String[][] readPinjam() {
        try {
            int jmlData = 0;//menampung jumlah data

            String[][] data = new String[getBanyakPinjam()][6];

            String query = "SELECT * FROM pinjam JOIN buku ON pinjam.id_buku = buku.id_buku " +
                    "JOIN anggota ON pinjam.id_Anggota = anggota.id_anggota";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) { //lanjut kedata selanjutnya jmlData bertambah
                data[jmlData][0] = resultSet.getString("pinjam.id_pinjam"); //kolom nama harus sama besar kecilnya dgn database
                data[jmlData][1] = resultSet.getString("buku.judul");
                data[jmlData][2] = resultSet.getString("anggota.nama");
                data[jmlData][3] = resultSet.getString("anggota.alamat");
                data[jmlData][4] = resultSet.getString("pinjam.tgl_pinjam");
                data[jmlData][5] = resultSet.getString("status");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public int getBanyakPinjam() {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "Select * from `pinjam`"; //pengambilan data dalam java dari database
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

    public int getBanyakPinjamCari(String keyword, String kolom) {//menghitung jumlah baris
        int jmlData = 0;
        try {
            statement = koneksi.createStatement();
            String query = "SELECT * FROM pinjam JOIN buku ON pinjam.id_buku = buku.id_buku " +
                    "JOIN anggota ON pinjam.id_Anggota = anggota.id_anggota WHERE "
                    + kolom + " LIKE " + keyword;
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

    public String[][] searchPinjam(String keyword, String kolom) {
        try {
            int jmlData = 0;
            keyword = "'%" + keyword + "%'";

            String query = "SELECT * FROM pinjam JOIN buku ON pinjam.id_buku = buku.id_buku " +
                    "JOIN anggota ON pinjam.id_Anggota = anggota.id_anggota WHERE "
                    + kolom + " LIKE " + keyword;

            String[][] data = new String[getBanyakPinjamCari(keyword, kolom)][6];

            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                data[jmlData][0] = resultSet.getString("pinjam.id_pinjam");
                data[jmlData][1] = resultSet.getString("buku.judul");
                data[jmlData][2] = resultSet.getString("anggota.nama");
                data[jmlData][3] = resultSet.getString("anggota.alamat");
                data[jmlData][4] = resultSet.getString("pinjam.tgl_pinjam");
                data[jmlData][5] = resultSet.getString("status");

                jmlData++; //barisnya berpindah terus
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }

    public ArrayList<String> readPinjamFilter(String kolom) {
        try {

            ArrayList<String> data = new ArrayList<>();

            String query = "SELECT DISTINCT " + kolom + " FROM pinjam JOIN buku ON pinjam.id_buku = buku.id_buku " +
                    "JOIN anggota ON pinjam.id_Anggota = anggota.id_anggota";

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

    public void insertPinjam(int id_anggota, int id_buku, String tgl_pinjam, String status) {
        try {
            String query = "INSERT INTO `pinjam` (`id_anggota`, `id_buku`, `tgl_pinjam` , `status` ) " +
                    "VALUES ('" + id_anggota + "','" + id_buku + "','" + tgl_pinjam + "','" + status + "') ";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil ditambahkan");
            JOptionPane.showMessageDialog(null, "Data Berhasil ditambahkan");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
            JOptionPane.showMessageDialog(null, sql.getMessage());
        }
    }
    public void editPinjam(int id,int id_anggota, int id_buku, String tgl_pinjam, String status) {
        try {
            String query = "UPDATE pinjam SET id_anggota = " + "'" + id_anggota + "', id_buku = " + "'" + id_buku + "', tgl_pinjam = " +
                    "'" + tgl_pinjam + "', status = " + "'" + status + "' " + "WHERE id_pinjam = " + id;
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            System.out.println("Berhasil diubah");
            JOptionPane.showMessageDialog(null, "Data Berhasil diubah");
        } catch (Exception sql) {
            System.out.println(sql.getMessage());
        }
    }

    public String[][] getPinjambyId(int id) {
        try {
            String query = "SELECT * FROM pinjam JOIN buku ON pinjam.id_buku = buku.id_buku " +
                    "JOIN anggota ON pinjam.id_Anggota = anggota.id_anggota WHERE id_pinjam = " + id;
            String[][] data = new String[1][7];
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                data[0][0] = resultSet.getString("pinjam.id_pinjam");
                data[0][1] = resultSet.getString("buku.judul");
                data[0][2] = resultSet.getString("anggota.nama");
                data[0][3] = resultSet.getString("pinjam.tgl_pinjam");
                data[0][4] = resultSet.getString("pinjam.status");
            }
            return data;

        } catch (SQLException e) {
            System.out.println("SQL Error : " + e.getMessage());
            System.out.println();
            return null;
        }
    }
    public void deletePinjam(int id) {
        try {
            String query = "DELETE FROM `pinjam` WHERE `id_pinjam` = '" + id + "'";
            statement = koneksi.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

        } catch (SQLException sql) {
            System.out.println(sql.getMessage());
        }
    }
}
