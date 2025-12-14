package org.example.strategy;

import org.example.model.Patient;

public class BasicPatientValidation implements ValidationStrategy {
    private String message = "";

    @Override
    public boolean isValid(Patient patient) {
        if (patient.getNumber() == null || patient.getNumber().isEmpty()) {
            message = "Patient number is required";
            return false;
        }
        if (patient.getName() == null || patient.getName().isEmpty()) {
            message = "Patient name is required";
            return false;
        }
        if (patient.getGender() == null || patient.getGender().isEmpty()) {
            message = "Gender is required";
            return false;
        }
        if (patient.getDisease() == null || patient.getDisease().isEmpty()) {
            message = "Disease is required";
            return false;
        }
        if (patient.getRoom() == null || patient.getRoom().isEmpty()) {
            message = "Room selection is required";
            return false;
        }
        if (patient.getContact() == null || patient.getContact().isEmpty()) {
            message = "Contact is required";
            return false;
        }
        message = "";
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
