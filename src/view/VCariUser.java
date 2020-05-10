package view;

import javax.swing.*;
import java.awt.*;

public class VCariUser extends JFrame {
    JLabel label;
    public JRadioButton radio1 = new JRadioButton("Nama");
    public JRadioButton radio2 = new JRadioButton("Username");
    public JRadioButton radio3 = new JRadioButton("level");
    ButtonGroup grup = new ButtonGroup();
    public JTextField tfCari = new JTextField();
    public JButton btnGo;

    public VCariUser() {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 300));
        frame.setTitle("Cari User");
        btnGo = new JButton("Cari User");


        label = new JLabel( "CARI BERDASARKAN : ");
        label.setFont(new Font("TAHOMA", Font.ITALIC, 15));

        frame.add(label);
        //tampilan untuk fitur cari
        frame.setLayout(new GridLayout(6, 1));
        frame.add(radio1);
        radio1.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio1);
        frame.add(radio2);
        radio2.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio2);
        frame.add(radio3);
        radio3.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio3);

        tfCari.setHorizontalAlignment(JTextField.CENTER);
        tfCari.setSize(300, 20);
        frame.add(tfCari);

        frame.add(btnGo);
    }
}
