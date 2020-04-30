package view;

import javax.swing.*;

public class VLogin extends JFrame {
    //Label
    JLabel lUser, lPass;
    JTextField tfUser;
    JPasswordField tfPass;
    public JButton btnLogin = new JButton("Login");;

    public VLogin() {
        setTitle("Login Perpustakaan");

        //label
        lUser = new JLabel("Username");
        lPass = new JLabel("Password");

        //textfield
        tfUser = new JTextField("");
        tfPass = new JPasswordField("");

        //button
        //btnLogin = new JButton("Login");

        setLayout(null);
        add(lUser);
        add(lPass);
        add(tfUser);
        add(tfPass);
        add(btnLogin);

        lUser.setBounds(50, 25, 120, 20);
        lPass.setBounds(50, 50, 120, 20);
        tfUser.setBounds(120, 25, 180, 20);
        tfPass.setBounds(120, 50, 180, 20);
        btnLogin.setBounds(100, 75, 100, 20);

        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public String getUsername(){ return tfUser.getText();}
    public String getPassword(){ return tfPass.getText();}
}
