package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Department extends JFrame {
    JTable table;
    DefaultTableModel model;

    Department() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Department ID", "Name", "Head", "Contact"});

        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(24);
        table.setBackground(new Color(255, 255, 240));

        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 80, 670, 350);
        panel.add(scrollPane);

        JLabel labelTitle = new JLabel("Department Directory");
        labelTitle.setBounds(220, 20, 300, 30);
        labelTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelTitle.setForeground(Color.white);
        panel.add(labelTitle);

        JButton refreshBtn = new JButton("REFRESH");
        refreshBtn.setBounds(180, 440, 120, 30);
        refreshBtn.setBackground(Color.BLACK);
        refreshBtn.setForeground(Color.WHITE);
        panel.add(refreshBtn);
        refreshBtn.addActionListener(e -> loadData());

        JButton backBtn = new JButton("BACK");
        backBtn.setBounds(320, 440, 120, 30);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);
        backBtn.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(350, 250);
        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM departments");

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("head"),
                        rs.getString("contact")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading departments: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Department();
    }
}
