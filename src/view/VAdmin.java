package view;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

import static utils.ColumnUtils.*;

public class VAdmin extends JFrame {
    public JTable table;
    public static String halaman = "BUKU";
    public JButton btnCari, btnFilter, btnLogout, btnTampil;
    public JButton btnTambah, btnEdit, btnHapus;
    public JButton btnBuku, btnPinjam, btnUser, btnAnggota;

    public VAdmin() {
        JScrollPane scrollPane;
        JLabel lbHome;

        setTitle("BUKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1250, 600);
        setLayout(null);
        setLocationRelativeTo(null);

        if (halaman.equalsIgnoreCase("anggota")) {
            String[][] data = new String[50][6];
            table = new JTable(data, KOLOM_ANGGOTA);
        }else if (halaman.equalsIgnoreCase("pinjam")){
            String[][] data = new String[50][6];
            table = new JTable(data, KOLOM_PINJAM);
        }else if (halaman.equalsIgnoreCase("user")){
            String[][] data = new String[50][5];
            table = new JTable(data, KOLOM_USER);
        }else{
            String[][] data = new String[50][7];
            table = new JTable(data, KOLOM_BUKU);
        }
        scrollPane = new JScrollPane(table);

        lbHome = new JLabel("PERPUSTAKAAN UMUM YOYAKARTA");
        lbHome.setFont(new Font("TAHOMA", Font.ITALIC + Font.BOLD, 30));

        btnBuku = new JButton("BUKU");
        btnPinjam = new JButton("PEMINJAMAN");
        btnUser = new JButton("USER");
        btnAnggota = new JButton("ANGGOTA");

        btnCari = new JButton("Cari");
        btnFilter = new JButton("Filter");
        btnTampil = new JButton("Tampilkan Semua");
        btnLogout = new JButton("Logout");

        btnTambah = new JButton("Tambah");
        btnEdit = new JButton("Ubah");
        btnHapus = new JButton("Hapus");

        add(lbHome);
        add(scrollPane);

        add(btnBuku);
        add(btnPinjam);
        add(btnUser);
        add(btnAnggota);

        add(btnCari);
        add(btnFilter);
        add(btnLogout);
        add(btnTampil);

        add(btnTambah);
        add(btnEdit);
        add(btnHapus);


        lbHome.setBounds(50, 10, 760, 30);
        scrollPane.setBounds(20, 50, 760, 320);

        btnBuku.setBounds(790, 50, 120, 50);
        btnPinjam.setBounds(790, 105, 120, 50);
        btnUser.setBounds(790, 160, 120, 50);
        btnAnggota.setBounds(790, 215, 120, 50);

        btnCari.setBounds(920, 50, 140, 50);
        btnFilter.setBounds(920, 105, 140, 50);
        btnTampil.setBounds(920, 160, 140, 55);
        btnLogout.setBounds(920, 220, 140, 50);

        btnTambah.setBounds(1070, 50, 140, 50);
        btnEdit.setBounds(1070, 105, 140, 50);
        btnHapus.setBounds(1070, 160, 140, 55);

    }

    public void aturKolomBuku() {
        TableColumn column;
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(200);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(6);
        column.setPreferredWidth(70);
    }
    public void aturKolomUser() {
        TableColumn column;
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(80);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(220);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(160);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(160);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(150);
    }
    public void aturKolomAnggota() {
        TableColumn column;
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(60);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(170);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(260);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(60);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(110);
    }
    public void aturKolomPinjam() {
        TableColumn column;
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(40);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(140);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(140);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(220);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(120);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(120);
    }
}
