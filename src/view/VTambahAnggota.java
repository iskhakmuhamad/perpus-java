package view;

import javax.swing.*;

public class VTambahAnggota extends JFrame {
    JLabel lnama, lumur, lkelamin, lpendidikn, lalamat;
    public JTextField fnama, fpendidikan, fumur;
    public JTextArea falamat;
    public JRadioButton fl, fp;
    public ButtonGroup buttonGroup;
    JScrollPane scrollPane;
    public JButton btnTambah;

    public VTambahAnggota(String action) {
        lnama = new JLabel("Nama");
        lumur = new JLabel("Umur");
        lkelamin = new JLabel("Jenis Kelamin");
        lpendidikn = new JLabel("Pendidikan Terakhir");
        lalamat = new JLabel("Alamat");

        fnama = new JTextField(120);
        fpendidikan = new JTextField(120);
        fumur = new JTextField(120);
        falamat = new JTextArea(3, 10);
        scrollPane = new JScrollPane(falamat);

        buttonGroup = new ButtonGroup();
        fl = new JRadioButton("Laki - Laki");
        fl.setActionCommand("Laki - Laki");
        fp = new JRadioButton("Perempuan");
        fp.setActionCommand("Perempuan");
        buttonGroup.add(fl);
        buttonGroup.add(fp);

        btnTambah = new JButton(action + " Data Anggota");


        setTitle(action + " Data Anggota");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(380, 360);
        setLayout(null);
        setLocationRelativeTo(null);

        add(lpendidikn);
        add(fpendidikan);
        add(lnama);
        add(fnama);
        add(lkelamin);
        add(fl);
        add(fp);
        add(lumur);
        add(fumur);
        add(lalamat);
        add(scrollPane);
        add(btnTambah);

        lnama.setBounds(20, 20, 120, 20);
        fnama.setBounds(140, 20, 200, 20);
        lumur.setBounds(20, 50, 120, 20);
        fumur.setBounds(140, 50, 200, 20);
        lkelamin.setBounds(20, 80, 120, 20);
        fl.setBounds(140, 80, 200, 20);
        fp.setBounds(140, 110, 200, 20);
        lpendidikn.setBounds(20, 140, 200, 20);
        fpendidikan.setBounds(140, 140, 200, 20);
        lalamat.setBounds(20, 170, 200, 20);
        scrollPane.setBounds(140, 170, 200, 80);
        btnTambah.setBounds(40, 270, 120, 30);
    }
}
