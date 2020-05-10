package view;

import javax.swing.*;
import java.awt.*;

public class VTambahUser extends JFrame {
    JLabel lNama, lUsername, lPaswword, l;
    public JTextField tNama, tUsername, tPassword;
    public JButton btnTambah;

    public VTambahUser(String action) {

        setTitle(action + " Buku");
        setSize(new Dimension(400, 200));
        setLayout(null);
        setLocationRelativeTo(null);
        if (action.equalsIgnoreCase("cari")) {
            setLayout(new GridLayout(7, 2));
        } else {
            setLayout(new GridLayout(4, 2));
        }
        Font tahoma = new Font("TAHOMA", Font.ITALIC, 14);

        lNama = new JLabel("Nama Lengkap ");
        lNama.setFont(tahoma);
        lUsername = new JLabel("Username ");
        lUsername.setFont(tahoma);
        lPaswword = new JLabel("Password ");
        lPaswword.setFont(tahoma);

        tNama = new JTextField("");
        tUsername = new JTextField("");
        tPassword = new JTextField("");

        add(lNama);
        add(tNama);
        add(lUsername);
        add(tUsername);
        add(lPaswword);
        add(tPassword);


        btnTambah = new JButton(action + " User");
        l = new JLabel();
        add(l);
        add(btnTambah);

        setVisible(true);
    }
}
