package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {

    JTextField textField;
    JPasswordField jPasswordField;
    JButton b1, b2;

    public Login() {
        JLabel namelabel = new JLabel("Username");
        namelabel.setBounds(40, 20, 100, 30);
        namelabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(namelabel);

        JLabel password = new JLabel("Password");
        password.setBounds(40, 70, 100, 30);
        password.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(password);

        textField = new JTextField();
        textField.setBounds(150, 20, 150, 30);
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setBackground(new Color(255, 179, 0));
        add(textField);

        jPasswordField = new JPasswordField();
        jPasswordField.setBounds(150, 70, 150, 30);
        jPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jPasswordField.setBackground(new Color(255, 179, 0));
        add(jPasswordField);

        b1 = new JButton("Login");
        b1.setBounds(40, 140, 120, 30);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.white);
        b1.addActionListener(e -> validateLogin());
        add(b1);

        b2 = new JButton("Cancel");
        b2.setBounds(180, 140, 120, 30);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.white);
        b2.addActionListener(this);
        add(b2);

        getContentPane().setBackground(new Color(109, 164, 170));
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    private void validateLogin() {
        String username = textField.getText();
        char[] passwordChars = jPasswordField.getPassword();
        String password = new String(passwordChars);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful");
                    dispose();
                    SwingUtilities.invokeLater(() -> new Reception());
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database error occurred.");
            ex.printStackTrace();
        } finally {
            Arrays.fill(passwordChars, '\0');
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        new Login();
    }
}
