package org.example;

import org.example.command.AddPatientCommand;
import org.example.core.AppContext;
import org.example.facade.HospitalFacade;
import org.example.memento.PatientFormCaretaker;
import org.example.memento.PatientFormMemento;
import org.example.model.Patient;
import org.example.model.RoomInfo;
import org.example.strategy.ValidationStrategy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NEW_PATIENT extends JFrame implements ActionListener {
    JComboBox comboBox;
    JTextField textFieldNumber, textName, textFieldDisease, textFieldBill, contactField;
    JRadioButton r1, r2;
    JButton b1, b2, b3;
    Choice roomChoice;
    private final HospitalFacade facade = AppContext.getInstance().getHospitalFacade();
    private final PatientFormCaretaker caretaker = new PatientFormCaretaker();

    NEW_PATIENT() {
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 840, 540);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(118, 11, 260, 53);
        labelName.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(labelName);

        JLabel labelID = new JLabel("ID :");
        labelID.setBounds(35, 76, 200, 14);
        labelID.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelID.setForeground(Color.white);
        panel.add(labelID);

        comboBox = new JComboBox(new String[]{"Passport", "Voter Id", "Driving License"});
        comboBox.setBounds(271, 73, 150, 20);
        comboBox.setBackground(new Color(3, 45, 48));
        comboBox.setForeground(Color.white);
        comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(comboBox);

        JLabel labelNumber = new JLabel("Number :");
        labelNumber.setBounds(35, 111, 200, 14);
        labelNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelNumber.setForeground(Color.white);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(271, 111, 150, 20);
        panel.add(textFieldNumber);

        JLabel labelName1 = new JLabel("Name :");
        labelName1.setBounds(35, 151, 200, 14);
        labelName1.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelName1.setForeground(Color.white);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(271, 151, 150, 20);
        panel.add(textName);

        JLabel labelGender = new JLabel("Gender :");
        labelGender.setBounds(35, 191, 200, 14);
        labelGender.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelGender.setForeground(Color.white);
        panel.add(labelGender);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Tahoma", Font.BOLD, 14));
        r1.setForeground(Color.white);
        r1.setBackground(new Color(109, 164, 170));
        r1.setBounds(271, 191, 80, 15);
        panel.add(r1);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Tahoma", Font.BOLD, 14));
        r2.setForeground(Color.white);
        r2.setBackground(new Color(109, 164, 170));
        r2.setBounds(350, 191, 80, 15);
        panel.add(r2);

        JLabel labelDisease = new JLabel("Disease :");
        labelDisease.setBounds(35, 231, 200, 14);
        labelDisease.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelDisease.setForeground(Color.white);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(271, 231, 150, 20);
        panel.add(textFieldDisease);

        JLabel labelRoom = new JLabel("Room :");
        labelRoom.setBounds(35, 274, 200, 14);
        labelRoom.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelRoom.setForeground(Color.white);
        panel.add(labelRoom);

        roomChoice = new Choice();
        roomChoice.setBounds(271, 274, 150, 20);
        panel.add(roomChoice);
        loadAvailableRooms(roomChoice);
        facade.register(patient -> loadAvailableRooms(roomChoice));

        JLabel labelBill = new JLabel("Bill :");
        labelBill.setBounds(35, 316, 200, 14);
        labelBill.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelBill.setForeground(Color.white);
        panel.add(labelBill);

        textFieldBill = new JTextField();
        textFieldBill.setBounds(271, 316, 150, 20);
        panel.add(textFieldBill);

        JLabel labelContact = new JLabel("Contact :");
        labelContact.setBounds(35, 358, 200, 14);
        labelContact.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelContact.setForeground(Color.white);
        panel.add(labelContact);

        contactField = new JTextField();
        contactField.setBounds(271, 358, 150, 20);
        panel.add(contactField);

        b1 = new JButton("ADD");
        b1.setBounds(60, 430, 120, 30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.black);
        b1.addActionListener(this);
        panel.add(b1);

        b2 = new JButton("Clear");
        b2.setBounds(220, 430, 120, 30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(Color.black);
        b2.addActionListener(e -> clearFields());
        panel.add(b2);

        b3 = new JButton("Close");
        b3.setBounds(380, 430, 120, 30);
        b3.setForeground(Color.WHITE);
        b3.setBackground(Color.black);
        b3.addActionListener(e -> closeFields());
        panel.add(b3);

        setUndecorated(true);
        setSize(850, 550);
        setLayout(null);
        setLocation(300, 250);
        setVisible(true);
    }

    String getGender() {
        return r1.isSelected() ? "Male" : (r2.isSelected() ? "Female" : null);
    }

    private Patient createPatientFromForm() {
        double bill;
        try {
            bill = Double.parseDouble(textFieldBill.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Bill should be numeric");
            return null;
        }
        return facade.getFactory().createPatient(
                (String) comboBox.getSelectedItem(),
                textFieldNumber.getText(),
                textName.getText(),
                getGender(),
                textFieldDisease.getText(),
                roomChoice.getSelectedItem(),
                bill,
                contactField.getText()
        );
    }

    private void saveItem() {
        Patient patient = createPatientFromForm();
        if (patient == null) {
            return;
        }
        ValidationStrategy validationStrategy = facade.getValidationStrategy();
        AddPatientCommand command = new AddPatientCommand(facade, patient, validationStrategy, caretaker);
        command.execute();
        caretaker.saveState(new PatientFormMemento(patient));
        clearFields();
    }

    public void actionPerformed(ActionEvent e) {
        saveItem();
    }

    public void clearFields() {
        caretaker.saveState(new PatientFormMemento(facade.getFactory().createPatient(
                (String) comboBox.getSelectedItem(),
                textFieldNumber.getText(),
                textName.getText(),
                getGender(),
                textFieldDisease.getText(),
                roomChoice.getSelectedItem(),
                textFieldBill.getText().isEmpty() ? 0.0 : Double.parseDouble(textFieldBill.getText()),
                contactField.getText()
        )));
        comboBox.setSelectedIndex(0);
        textFieldNumber.setText("");
        textName.setText("");
        r1.setSelected(false);
        r2.setSelected(false);
        textFieldDisease.setText("");
        roomChoice.removeAll();
        loadAvailableRooms(roomChoice);
        textFieldBill.setText("");
        contactField.setText("");
    }

    public void closeFields() {
        clearFields();
        dispose();
    }

    private void loadAvailableRooms(Choice roomChoice) {
        roomChoice.removeAll();
        try {
            List<RoomInfo> rooms = facade.fetchAvailableRooms();
            for (RoomInfo room : rooms) {
                roomChoice.add(room.getRoomNo());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading rooms: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }
}
