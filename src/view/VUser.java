package view;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

import static utils.ColumnUtils.KOLOM_BUKU;
import static utils.ColumnUtils.KOLOM_PINJAM;

public class VUser extends JFrame {
    public static String halaman = "BUKU";
    public JTable table;
    public JButton btnCari, btnFilter, btnLogout, btnTampil, btnPinjam, btnBuku;
    JScrollPane scrollPane;
    JLabel lbUser;

    String[][] data = new String[50][7];

    public VUser() {
        setTitle("BUKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        if (halaman.equalsIgnoreCase("pinjam")) {
            String[][] data = new String[50][6];
            table = new JTable(data, KOLOM_PINJAM);
        } else {
            String[][] data = new String[50][7];
            table = new JTable(data, KOLOM_BUKU);
        }

        table = new JTable(data, KOLOM_BUKU);
        scrollPane = new JScrollPane(table);

        lbUser = new JLabel("PERPUSTAKAAN UMUM YOYAKARTA");
        lbUser.setFont(new Font("TAHOMA", Font.ITALIC + Font.BOLD, 30));
        btnCari = new JButton("Cari");
        btnFilter = new JButton("Filter");
        btnTampil = new JButton("Tampilkan Semua");
        btnLogout = new JButton("Logout");
        btnPinjam = new JButton("Peminjaman");
        btnBuku = new JButton("BUKU");


        add(scrollPane);
        add(btnCari);
        add(btnFilter);
        add(btnLogout);
        add(btnTampil);
        add(lbUser);
        add(btnPinjam);
        add(btnBuku);

        lbUser.setBounds(50, 10, 760, 30);
        scrollPane.setBounds(20, 50, 760, 320);
        btnCari.setBounds(790, 50, 140, 50);
        btnFilter.setBounds(790, 105, 140, 50);
        btnTampil.setBounds(790, 160, 140, 55);
        btnBuku.setBounds(790, 220, 140, 50);
        btnPinjam.setBounds(790, 280, 140, 50);
        btnLogout.setBounds(790, 340, 140, 50);
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
