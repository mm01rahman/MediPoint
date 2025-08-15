package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchRoom extends JFrame {

    Choice choice;
    JTable table;
    DefaultTableModel model;

    SearchRoom() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 690, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Search For Room");
        heading.setBounds(220, 11, 250, 31);
        heading.setForeground(Color.white);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        panel.add(heading);

        JLabel status = new JLabel("Status:");
        status.setBounds(70, 70, 80, 20);
        status.setForeground(Color.white);
        status.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(status);

        choice = new Choice();
        choice.setBounds(170, 70, 150, 20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Room No", "Availability", "Price", "Bed Type"});
        table = new JTable(model);
        table.setRowHeight(22);
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setBackground(Color.white);

        // Optional: Center all cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 120, 630, 270);
        panel.add(scrollPane);

        JButton search = new JButton("Search");
        search.setBounds(180, 420, 120, 30);
        search.setBackground(Color.black);
        search.setForeground(Color.white);
        panel.add(search);
        search.addActionListener(e -> searchRoom());

        JButton back = new JButton("Back");
        back.setBounds(360, 420, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(e -> setVisible(false));
        panel.add(back);

        setUndecorated(true);
        setSize(700, 500);
        setLayout(null);
        setLocation(450, 250);
        setVisible(true);
    }

    private void searchRoom() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM rooms WHERE availability = ?");
            ps.setString(1, choice.getSelectedItem());
            ResultSet rs = ps.executeQuery();

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("room_no"),
                        rs.getString("availability"),
                        rs.getDouble("price"),
                        rs.getString("type")
                });
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No rooms found with selected status.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}
