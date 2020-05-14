package view;

import javax.swing.*;
import java.awt.*;

public class VCariUser extends JFrame {
    JLabel label;
    public JRadioButton radio1 = new JRadioButton("Nama");
    public JRadioButton radio2 = new JRadioButton("Username");
    public JRadioButton radio3 = new JRadioButton("level");
    ButtonGroup grup = new ButtonGroup();
    public JComboBox cbFilter;
    public JTextField tfCari = new JTextField();
    public JButton btnGo;

    public VCariUser(String action) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        frame.setTitle(action + " User");
        btnGo = new JButton(action + " User");


        label = new JLabel(action + " BERDASARKAN : ");
        label.setFont(new Font("TAHOMA", Font.ITALIC, 15));

        frame.add(label);
        //tampilan untuk fitur cari
        if (action.equalsIgnoreCase("cari")) {
            frame.setSize(new Dimension(400, 300));
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
        }else {
            frame.setSize(new Dimension(400, 200));
            frame.setLayout(new GridLayout(3, 1));
            radio3.setHorizontalAlignment(JRadioButton.LEFT);
            grup.add(radio3);
        }
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
