package view;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

import static utils.ColumnUtils.KOLOM_BUKU;

public class VHome extends JFrame {
    public JTable table;
    public JButton btnCari, btnFilter, btnLogin, btnTampil;
    public JTextField tfCari;
    public JComboBox cbFilter;

    public VHome() {
        JScrollPane scrollPane;
        JLabel lbCari,lbfilter;

        String[][] data = new String[50][7];

        setTitle("BUKU");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1000, 400);
        setLayout(null);
        setLocationRelativeTo(null);

        table = new JTable(data, KOLOM_BUKU);
        scrollPane = new JScrollPane(table);

        tfCari = new JTextField("");
        lbCari = new JLabel("Cari Berdasarkan Judul");
        lbfilter = new JLabel("Pilih Genre");
        lbCari.setFont( new Font( "TAHOMA" , Font.ITALIC, 12 ));
        lbfilter.setFont(new Font( "TAHOMA" , Font.ITALIC, 12 ));
        cbFilter = new JComboBox();
        btnCari = new JButton("Cari");
        btnFilter = new JButton("Filter");
        btnTampil = new JButton("Tampilkan Semua");
        btnLogin = new JButton("Login");


        add(scrollPane);
        add(btnCari);
        add(btnFilter);
        add(btnLogin);
        add(tfCari);
        add(lbCari);
        add(lbfilter);
        add(cbFilter);
        add(btnTampil);

        scrollPane.setBounds(20, 20, 760, 320);
        lbCari.setBounds(790, 20, 150, 20);
        tfCari.setBounds(790, 40, 150, 25);
        btnCari.setBounds(790, 70, 140, 30);
        lbfilter.setBounds(790,110,150,25);
        cbFilter.setBounds(790,140,150,25);
        btnFilter.setBounds(790, 170, 140, 30);
        btnTampil.setBounds(790,210,140,30);
        btnLogin.setBounds(790, 250, 140, 30);
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
