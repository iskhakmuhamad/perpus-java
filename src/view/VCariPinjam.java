package view;

import javax.swing.*;
import java.awt.*;

public class VCariPinjam extends JFrame {

    JLabel label;
    public JRadioButton radio1 = new JRadioButton("Judul Buku");
    public JRadioButton radio2 = new JRadioButton("Nama Peminjam");
    public JRadioButton radio3 = new JRadioButton("Alamat Peminjam");
    public JRadioButton radio4 = new JRadioButton("Tanggal Pinjam");
    public JRadioButton radio5 = new JRadioButton("Status");
    public JComboBox cbFilter;
    ButtonGroup grup = new ButtonGroup();
    public JTextField tfCari = new JTextField();
    public JButton btnGo;

    public VCariPinjam(String action) {
        JFrame frame = new JFrame(action);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 300));
        btnGo = new JButton(action);


        label = new JLabel(action + " BERDASARKAN : ");
        label.setFont(new Font("TAHOMA", Font.ITALIC, 15));

        frame.add(label);

        frame.setLayout(new GridLayout(8, 1));
        frame.add(radio1);
        radio1.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio1);
        frame.add(radio2);
        radio2.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio2);
        frame.add(radio3);
        radio3.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio3);
        frame.add(radio4);
        radio4.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio4);
        frame.add(radio5);
        radio5.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio5);

        if (action.equalsIgnoreCase("cari")) {
            //tampilan untuk fitur cari
            tfCari.setHorizontalAlignment(JTextField.CENTER);
            tfCari.setSize(300, 20);
            frame.add(tfCari);
        } else {
            //tampilan untuk fitur filter
            cbFilter = new JComboBox();
            frame.add(cbFilter);
        }

        frame.add(btnGo);
    }
}
