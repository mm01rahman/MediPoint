package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame {
    private JFrame currentWindow = null;
    public Reception(){

        setSize(1550, 875);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5,160,1525,670);
        panel.setBackground(new Color(109,164,170));
        panel.setSize(1550, 875);
        add(panel);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(5,5,1525,150);
        panel1.setBackground(new Color(109,164,170));
        add(panel1);

        JButton btn1 = new JButton("Add New Patient");
        btn1.setBounds(30,15,200,30);
        btn1.setBackground(new Color(246,215,118));
        panel1.add(btn1);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new NEW_PATIENT();
            }
        });

        JButton btn2 = new JButton("Room");
        btn2.setBounds(30,58,200,30);
        btn2.setBackground(new Color(246,215,118));
        panel1.add(btn2);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new Room();
            }
        });

        JButton btn3 = new JButton("Department");
        btn3.setBounds(30,100,200,30);
        btn3.setBackground(new Color(246,215,118));
        panel1.add(btn3);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new Department();
            }
        });

        JButton btn4 = new JButton("Update Patient Details");
        btn4.setBounds(270,15,200,30);
        btn4.setBackground(new Color(246,215,118));
        panel1.add(btn4);
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new update_patient_details();
            }
        });

        JButton btn5 = new JButton("Patient Info");
        btn5.setBounds(270,58,200,30);
        btn5.setBackground(new Color(246,215,118));
        panel1.add(btn5);
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new ALL_Patient_Info();
            }
        });

        JButton btn6= new JButton("Patient Discharge");
        btn6.setBounds(270,100,200,30);
        btn6.setBackground(new Color(246,215,118));
        panel1.add(btn6);
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new patient_discharge();
            }
        });

        JButton btn7= new JButton("Logout");
        btn7.setBounds(510,15,200,30);
        btn7.setBackground(new Color(246,215,118));
        panel1.add(btn7);
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                System.exit(0);
            }
        });

        JButton btn8= new JButton("Hospital Ambulance");
        btn8.setBounds(510,58,200,30);
        btn8.setBackground(new Color(246,215,118));
        panel1.add(btn8);
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new Ambulance();
            }
        });

        JButton btn9= new JButton("Search Room");
        btn9.setBounds(510,100,200,30);
        btn9.setBackground(new Color(246,215,118));
        panel1.add(btn9);
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentWindow != null) {
                    currentWindow.dispose();
                }
                currentWindow = new SearchRoom();
            }
        });

        //comment added
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }



    public static void main(String[] args) {
        new Reception();
    }
}