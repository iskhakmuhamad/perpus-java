package view;

import javax.swing.*;
import java.awt.*;

public class VTambahUser extends JFrame {
    JLabel lNama, lUsername, lPaswword, lLevel, l;
    public JTextField tNama, tUsername, tPassword;
    public JComboBox cbLevel;
    public JButton btnTambah;

    public VTambahUser(String action) {

        setTitle(action + " Buku");
        setSize(new Dimension(400, 200));
        setLayout(null);
        setLocationRelativeTo(null);
        if (action.equalsIgnoreCase("cari")) {
            setLayout(new GridLayout(8, 2));
        } else {
            setLayout(new GridLayout(5, 2));
        }
        Font tahoma = new Font("TAHOMA", Font.ITALIC, 14);

        lNama = new JLabel("Nama Lengkap ");
        lNama.setFont(tahoma);
        lUsername = new JLabel("Username ");
        lUsername.setFont(tahoma);
        lPaswword = new JLabel("Password ");
        lPaswword.setFont(tahoma);
        lLevel = new JLabel("Level");
        lLevel.setFont(tahoma);

        tNama = new JTextField("");
        tUsername = new JTextField("");
        tPassword = new JTextField("");
        cbLevel = new JComboBox();

        cbLevel.addItem("admin");
        cbLevel.addItem("user");

        add(lNama);
        add(tNama);
        add(lUsername);
        add(tUsername);
        add(lPaswword);
        add(tPassword);
        add(lLevel);
        add(cbLevel);


        btnTambah = new JButton(action + " User");
        l = new JLabel();
        add(l);
        add(btnTambah);

        setVisible(true);
    }
}
