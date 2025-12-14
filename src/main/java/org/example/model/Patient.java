package org.example.model;

import java.time.LocalDateTime;

public class Patient {
    private final String documentType;
    private final String number;
    private final String name;
    private final String gender;
    private final String disease;
    private final String room;
    private final double bill;
    private final String contact;
    private final LocalDateTime admissionTime;

    public Patient(String documentType, String number, String name, String gender, String disease,
                   String room, double bill, String contact, LocalDateTime admissionTime) {
        this.documentType = documentType;
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.disease = disease;
        this.room = room;
        this.bill = bill;
        this.contact = contact;
        this.admissionTime = admissionTime;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDisease() {
        return disease;
    }

    public String getRoom() {
        return room;
    }

    public double getBill() {
        return bill;
    }

    public String getContact() {
        return contact;
    }

    public LocalDateTime getAdmissionTime() {
        return admissionTime;
    }
}
