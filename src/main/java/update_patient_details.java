import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class update_patient_details extends JFrame {
    JTextField textFieldAmount, textFieldName, textFieldDisease, textFieldContact;
    JRadioButton r1, r2;
    Choice choice, roomChoice;
    String previousRoom = null;

    update_patient_details() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 950, 500);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 300, 25);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.white);
        panel.add(label1);

        JLabel label2 = new JLabel("Patient ID :");
        label2.setBounds(25, 48, 100, 14);
        label2.setForeground(Color.white);
        panel.add(label2);

        choice = new Choice();
        choice.setBounds(248, 45, 140, 25);
        panel.add(choice);
        populatePatientIDs();

        JLabel labelName = new JLabel("Name :");
        labelName.setBounds(25, 88, 100, 14);
        labelName.setForeground(Color.white);
        panel.add(labelName);

        textFieldName = new JTextField();
        textFieldName.setBounds(248, 88, 140, 20);
        panel.add(textFieldName);

        JLabel labelGender = new JLabel("Gender :");
        labelGender.setBounds(25, 128, 100, 14);
        labelGender.setForeground(Color.white);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setBounds(248, 128, 70, 20);
        r1.setBackground(new Color(90, 156, 163));
        r1.setForeground(Color.white);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setBounds(320, 128, 80, 20);
        r2.setBackground(new Color(90, 156, 163));
        r2.setForeground(Color.white);
        panel.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JLabel labelDisease = new JLabel("Disease :");
        labelDisease.setBounds(25, 168, 100, 14);
        labelDisease.setForeground(Color.white);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(248, 168, 140, 20);
        panel.add(textFieldDisease);

        JLabel labelRoom = new JLabel("Room Number :");
        labelRoom.setBounds(25, 208, 120, 14);
        labelRoom.setForeground(Color.white);
        panel.add(labelRoom);

        roomChoice = new Choice();
        roomChoice.setBounds(248, 208, 140, 20);
        panel.add(roomChoice);

        JLabel labelBill = new JLabel("Bill :");
        labelBill.setBounds(25, 248, 100, 14);
        labelBill.setForeground(Color.white);
        panel.add(labelBill);

        textFieldAmount = new JTextField();
        textFieldAmount.setBounds(248, 248, 140, 20);
        panel.add(textFieldAmount);

        JLabel labelContact = new JLabel("Contact :");
        labelContact.setBounds(25, 288, 100, 14);
        labelContact.setForeground(Color.white);
        panel.add(labelContact);

        textFieldContact = new JTextField();
        textFieldContact.setBounds(248, 288, 140, 20);
        panel.add(textFieldContact);

        JButton update = new JButton("UPDATE");
        update.setBounds(56, 378, 89, 30);
        update.setBackground(Color.black);
        update.setForeground(Color.white);
        update.addActionListener(e -> updatePatient());
        panel.add(update);

        JButton back = new JButton("BACK");
        back.setBounds(168, 378, 89, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(e -> setVisible(false));
        panel.add(back);

        JButton check = new JButton("CHECK");
        check.setBounds(281, 378, 89, 30);
        check.setBackground(Color.black);
        check.setForeground(Color.white);
        check.addActionListener(e -> checkPatient());
        panel.add(check);

        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    private void populatePatientIDs() {
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

    private void loadAvailableRooms() {
        roomChoice.removeAll();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT room_no FROM rooms WHERE availability = 'Available'");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roomChoice.add(rs.getString("room_no"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    private void checkPatient() {
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT name, gender, disease, room, bill, contact FROM patients WHERE id = ?");
            ps.setString(1, choice.getSelectedItem());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                textFieldName.setText(rs.getString("name"));
                textFieldDisease.setText(rs.getString("disease"));
                textFieldAmount.setText(rs.getString("bill"));
                textFieldContact.setText(rs.getString("contact"));

                String gender = rs.getString("gender");
                if ("Male".equalsIgnoreCase(gender)) r1.setSelected(true);
                else if ("Female".equalsIgnoreCase(gender)) r2.setSelected(true);

                previousRoom = rs.getString("room");
                loadAvailableRooms();
                roomChoice.add(previousRoom);  // Add current room to choice as well
                roomChoice.select(previousRoom);
            } else {
                JOptionPane.showMessageDialog(this, "Patient not found.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updatePatient() {
        try {
            String newRoom = roomChoice.getSelectedItem();

            Connection con = DBConnection.getConnection();
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("UPDATE patients SET name = ?, gender = ?, disease = ?, room = ?, bill = ?, contact = ? WHERE id = ?");
            ps.setString(1, textFieldName.getText());
            ps.setString(2, r1.isSelected() ? "Male" : "Female");
            ps.setString(3, textFieldDisease.getText());
            ps.setString(4, newRoom);
            ps.setDouble(5, Double.parseDouble(textFieldAmount.getText()));
            ps.setString(6, textFieldContact.getText());
            ps.setString(7, choice.getSelectedItem());
            ps.executeUpdate();

            if (!newRoom.equals(previousRoom)) {
                PreparedStatement markOldAvailable = con.prepareStatement("UPDATE rooms SET availability = 'Available' WHERE room_no = ?");
                markOldAvailable.setString(1, previousRoom);
                markOldAvailable.executeUpdate();

                PreparedStatement markNewOccupied = con.prepareStatement("UPDATE rooms SET availability = 'Occupied' WHERE room_no = ?");
                markNewOccupied.setString(1, newRoom);
                markNewOccupied.executeUpdate();
            }

            con.commit();
            JOptionPane.showMessageDialog(this, "Patient details updated.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new update_patient_details();
    }
}
