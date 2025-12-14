package org.example.memento;

import org.example.model.Patient;

public class PatientFormMemento {
    private final Patient patient;

    public PatientFormMemento(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }
}
