package view;

import javax.swing.*;
import java.awt.*;

public class VTambahPinjam extends JFrame{
    JLabel lJudul, lNama, lTanggal, lStatus;
    public JTextField tTanggal, tStatus;
    public JComboBox tJudul, tNama;
    public JButton btnTambah;

    public VTambahPinjam(String action) {

        setTitle(action + " Pinjam");
        setSize(new Dimension(400, 400));
        setLayout(null);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 1));
        Font tahoma = new Font("TAHOMA", Font.ITALIC, 14);

        lJudul = new JLabel("Judul Buku ");
        lJudul.setFont(tahoma);
        lNama = new JLabel("Nama Peminjam ");
        lNama.setFont(tahoma);
        lTanggal = new JLabel("Tanggal Pinjam (Tahun-Bulan-Tanggal) (2020-05-21)");
        lTanggal.setFont(tahoma);
        lStatus = new JLabel("Status");
        lStatus.setFont(tahoma);

        tJudul = new JComboBox();
        tNama = new JComboBox();
        tStatus = new JTextField("");
        tTanggal = new JTextField("");

        add(lJudul);
        add(tJudul);
        add(lNama);
        add(tNama);
        add(lTanggal);
        add(tTanggal);
        add(lStatus);
        add(tStatus);

        btnTambah =new JButton(action + " Pinjam");
        add(btnTambah);

        setVisible(true);
    }
}
