package view;


import javax.swing.*;
import java.awt.*;


@SuppressWarnings("ALL")
public class VCariBuku extends JFrame {

    JLabel label;
    public JRadioButton radio1 = new JRadioButton("Judul");
    public JRadioButton radio2 = new JRadioButton("Genre");
    public JRadioButton radio3 = new JRadioButton("Penulis");
    public JRadioButton radio4 = new JRadioButton("Penerbit");
    public JRadioButton radio5 = new JRadioButton("Lokasi");
    public JRadioButton radio6 = new JRadioButton("Stok");
    public JComboBox cbFilter;
    ButtonGroup grup = new ButtonGroup();
    public JTextField tfCari = new JTextField();
    public JButton btnGo;

    public VCariBuku(String action) {
        JFrame frame = new JFrame(action);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(400, 300));
        btnGo = new JButton(action);


        label = new JLabel(action + " BERDASARKAN : ");
        label.setFont(new Font("TAHOMA", Font.ITALIC, 15));

        frame.add(label);
        if (action.equalsIgnoreCase("cari")) {
            //tampilan untuk fitur cari
            frame.setLayout(new GridLayout(9, 1));
            frame.add(radio1);
            radio1.setHorizontalAlignment(JRadioButton.LEFT);
            grup.add(radio1);
        } else {
            //tampilan untuk fitur filter
            frame.setLayout(new GridLayout(8, 1));
        }
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
        frame.add(radio6);
        radio6.setHorizontalAlignment(JRadioButton.LEFT);
        grup.add(radio6);

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
