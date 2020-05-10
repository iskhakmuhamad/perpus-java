package view;

import javax.swing.*;
import java.awt.*;

public class VTambahBuku extends JFrame {
    JLabel lJudul, lGenre, lPenulis, lPenerbit, lLokasi, lStok,l;
    public JTextField tfJudul, tfGenre, tfPenulis, tfPenerbit, tfLokasi, tfStok;
    public JButton btnTambah;

    public VTambahBuku(String action) {

        setTitle(action + " Buku");
        setSize(new Dimension(400, 300));
        setLayout(null);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));
        Font tahoma = new Font("TAHOMA", Font.ITALIC, 14);

        lJudul = new JLabel("Judul Buku ");
        lJudul.setFont(tahoma);
        lGenre = new JLabel("Genre Buku ");
        lGenre.setFont(tahoma);
        lPenulis = new JLabel("Penulis Buku ");
        lPenulis.setFont(tahoma);
        lPenerbit = new JLabel("Penerbit Buku ");
        lPenerbit.setFont(tahoma);
        lLokasi = new JLabel("Lokasi Buku ");
        lLokasi.setFont(tahoma);
        lStok = new JLabel("Stok Buku ");
        lStok.setFont(tahoma);

        tfJudul = new JTextField("");
        tfGenre = new JTextField("");
        tfPenulis = new JTextField("");
        tfPenerbit = new JTextField("");
        tfLokasi = new JTextField("");
        tfStok = new JTextField("");

        add(lJudul);
        add(tfJudul);
        add(lGenre);
        add(tfGenre);
        add(lPenulis);
        add(tfPenulis);
        add(lPenerbit);
        add(tfPenerbit);
        add(lLokasi);
        add(tfLokasi);
        add(lStok);
        add(tfStok);

        btnTambah =new JButton(action + " BUKU");
        l = new JLabel();
        add(l);
        add(btnTambah);

        setVisible(true);
    }
}
