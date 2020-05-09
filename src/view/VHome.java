package view;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

import static utils.ColumnUtils.KOLOM_BUKU;

public class VHome extends JFrame {
    public JTable table;
    public JButton btnCari, btnFilter, btnLogin, btnTampil;

    public VHome() {
        JScrollPane scrollPane;
        JLabel lbHome;

        String[][] data = new String[50][7];

        setTitle("BUKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        table = new JTable(data, KOLOM_BUKU);
        scrollPane = new JScrollPane(table);

        lbHome = new JLabel("PERPUSTAKAAN UMUM YOYAKARTA");
        lbHome.setFont(new Font("TAHOMA", Font.ITALIC + Font.BOLD , 30));
        btnCari = new JButton("Cari");
        btnFilter = new JButton("Filter");
        btnTampil = new JButton("Tampilkan Semua");
        btnLogin = new JButton("Login");


        add(scrollPane);
        add(btnCari);
        add(btnFilter);
        add(btnLogin);
        add(btnTampil);
        add(lbHome);

        lbHome.setBounds(50,10,760,30);
        scrollPane.setBounds(20, 50, 760, 320);
        btnCari.setBounds(790, 50, 140, 50);
        btnFilter.setBounds(790, 105, 140, 50);
        btnTampil.setBounds(790, 160, 140, 55);
        btnLogin.setBounds(790, 220, 140, 50);
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

}
