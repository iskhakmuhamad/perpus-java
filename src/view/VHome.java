package view;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class VHome extends JFrame {

    public VHome() {
        JScrollPane scrollPane;
        JTable table;
        JTextField tfCari;
        JLabel lbCari;
        JButton btnCari, btnFilter, btnLogin;

        String[][] data = new String[50][7];
        String[] kolom = {"No", "Judul Buku", "Genre buku", "Penulis", "Pengarang", "Lokasi", "Stok"};

        setTitle("BUKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        table = new JTable(data, kolom);
        TableColumn column;
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        column = table.getColumnModel().getColumn(0);
        column.setPreferredWidth(50);
        column = table.getColumnModel().getColumn(1);
        column.setPreferredWidth(200);
        column = table.getColumnModel().getColumn(2);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(3);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(4);
        column.setPreferredWidth(110);
        column = table.getColumnModel().getColumn(5);
        column.setPreferredWidth(100);
        column = table.getColumnModel().getColumn(6);
        column.setPreferredWidth(70);
        scrollPane = new JScrollPane(table);

        tfCari = new JTextField("");
        lbCari = new JLabel("Cari Berdasarkan Judul");
        lbCari.setFont( new Font( "Dialog" , Font.ITALIC, 12 ));
        btnCari = new JButton("Cari");
        btnFilter = new JButton("Filter");
        btnLogin = new JButton("Login");


        add(scrollPane);
        add(btnCari);
        add(btnFilter);
        add(btnLogin);
        add(tfCari);
        add(lbCari);

        scrollPane.setBounds(20, 20, 760, 320);
        lbCari.setBounds(790,20,150,20);
        tfCari.setBounds(790, 40, 150, 25);
        btnCari.setBounds(790, 70, 120, 30);
        btnFilter.setBounds(790, 120, 120, 30);
        btnLogin.setBounds(790, 160, 120, 30);
    }
}
