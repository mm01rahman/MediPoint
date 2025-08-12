import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class patient_discharge extends JFrame {
    Choice choice;
    JLabel RNo, INTime, OUTTime;
    Integer Bill;

    patient_discharge() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label = new JLabel("CHECK-OUT");
        label.setBounds(100, 20, 200, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.white);
        panel.add(label);

        JLabel label2 = new JLabel("Patient ID");
        label2.setBounds(30, 80, 150, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        label2.setForeground(Color.white);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);
        loadPatientIDs();
        choice.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkPatient();
            }
        });


        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30, 130, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.white);
        panel.add(label3);

        RNo = new JLabel();
        RNo.setBounds(200, 130, 150, 20);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        RNo.setForeground(Color.white);
        panel.add(RNo);

        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30, 180, 150, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.white);
        panel.add(label4);

        INTime = new JLabel();
        INTime.setBounds(200, 180, 250, 20);
        INTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        INTime.setForeground(Color.white);
        panel.add(INTime);

        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30, 230, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.white);
        panel.add(label5);

        OUTTime = new JLabel();
        OUTTime.setBounds(200, 230, 250, 20);
        OUTTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        OUTTime.setForeground(Color.white);
        panel.add(OUTTime);

        JButton discharge = new JButton("Discharge");
        discharge.setBounds(80, 300, 120, 30);
        discharge.setBackground(Color.black);
        discharge.setForeground(Color.white);
        panel.add(discharge);
        discharge.addActionListener(e -> dischargePatient());

//        JButton check = new JButton("Check");
//        check.setBounds(170, 300, 120, 30);
//        check.setBackground(Color.black);
//        check.setForeground(Color.white);
//        panel.add(check);
//        check.addActionListener(e -> checkPatient());

        JButton back = new JButton("Back");
//        back.setBounds(300, 300, 120, 30);
        back.setBounds(220, 300, 120, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        panel.add(back);
        back.addActionListener(e -> setVisible(false));

        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    private void loadPatientIDs() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT id FROM patients");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                choice.add(rs.getString("id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading patient IDs: " + e.getMessage());
        }
    }

    private void checkPatient() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT room, time, bill FROM patients WHERE id = ?");
            ps.setString(1, choice.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                RNo.setText(rs.getString("room"));
                INTime.setText(rs.getString("time"));
                OUTTime.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

                Bill = rs.getInt("bill");  // ✅ correctly update per patient
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found.");
                Bill = null; // ✅ Reset if not found
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching patient: " + e.getMessage());
            Bill = null; // ✅ Reset on error
        }
    }


    private void dischargePatient() {
        try {
            if (Bill == null) {
                JOptionPane.showMessageDialog(this, "Please check patient info first.");
                return;
            }

            if (Bill <= 0) {
                Connection con = DBConnection.getConnection();
                con.setAutoCommit(false);

                String patientId = choice.getSelectedItem();
                String roomNo = RNo.getText();

                PreparedStatement deletePatient = con.prepareStatement("DELETE FROM patients WHERE id = ?");
                deletePatient.setString(1, patientId);
                deletePatient.executeUpdate();

                PreparedStatement updateRoom = con.prepareStatement("UPDATE rooms SET availability = 'Available' WHERE room_no = ?");
                updateRoom.setString(1, roomNo);
                updateRoom.executeUpdate();

                con.commit();
                JOptionPane.showMessageDialog(this, "Patient discharged & room marked as available.");

                RNo.setText("");
                INTime.setText("");
                OUTTime.setText("");
                choice.remove(patientId);
                Bill = null;
            } else {
                JOptionPane.showMessageDialog(this, "Bill not paid. Current due: " + Bill);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new patient_discharge();
    }
}
