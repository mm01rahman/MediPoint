import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Room extends JFrame {

    JTable table;
    DefaultTableModel model;

    Room() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Room No", "Availability", "Price", "Bed Type"});

        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.setBackground(new Color(255, 255, 240));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 40, 870, 500);
        panel.add(scroll);

        JButton back = new JButton("BACK");
        back.setBounds(380, 550, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(e -> setVisible(false));
        panel.add(back);

        setUndecorated(true);
        setSize(900, 600);
        setLayout(null);
        setLocation(300, 230);
        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM rooms");

            model.setRowCount(0);
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("room_no"),
                        rs.getString("availability"),
                        rs.getDouble("price"),
                        rs.getString("type")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Room();
    }
}
