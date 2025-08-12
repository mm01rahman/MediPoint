import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Ambulance extends JFrame {
    JTable table;
    DefaultTableModel model;

    Ambulance() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel heading = new JLabel("Ambulance Details");
        heading.setBounds(330, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setForeground(Color.WHITE);
        panel.add(heading);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Vehicle No", "Driver Name", "Contact", "Status"});

        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
        table.setBackground(new Color(255, 255, 240));

        // Center align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 60, 870, 430);
        panel.add(scrollPane);

        JButton button = new JButton("BACK");
        button.setBounds(385, 510, 120, 30);
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        panel.add(button);

        button.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 200);
        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ambulances");

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("vehicle_no"),
                        rs.getString("driver_name"),
                        rs.getString("contact"),
                        rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Ambulance();
    }
}
