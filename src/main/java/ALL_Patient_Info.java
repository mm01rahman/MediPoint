import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ALL_Patient_Info extends JFrame {

    JTable table;
    DefaultTableModel model;

    ALL_Patient_Info() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{
                "ID", "Document", "Number", "Name", "Gender",
                "Disease", "Room", "Entry Time", "Bill", "Contact"
        });


        table = new JTable(model);
        table.setBackground(new Color(255, 255, 240));
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table.setRowHeight(30);


//        JTableHeader header = table.getTableHeader();
//        header.setFont(new Font("Tahoma", Font.BOLD, 14));
//        header.setBackground(new Color(60, 120, 130));
//        header.setForeground(Color.WHITE);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 40, 970, 450);
        panel.add(scroll);


        JButton refresh = new JButton("REFRESH");
        refresh.setBounds(340, 510, 120, 30);
        refresh.setBackground(Color.black);
        refresh.setForeground(Color.white);
        refresh.addActionListener(e -> loadData());
        panel.add(refresh);

        JButton back = new JButton("BACK");
        back.setBounds(480, 510, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(e -> setVisible(false));
        panel.add(back);

        setUndecorated(true);
        setSize(1000, 600);
        setLayout(null);
        setLocation(300, 200);
        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            Connection con = DBConnection.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM patients");

            model.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("document"),
                        rs.getString("number"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("disease"),
                        rs.getString("room"),
                        rs.getTimestamp("time"),
                        rs.getDouble("bill"),
                        rs.getString("contact")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Load error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new ALL_Patient_Info();
    }
}
