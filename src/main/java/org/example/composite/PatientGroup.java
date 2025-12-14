package org.example.composite;

import org.example.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientGroup implements PatientComponent {
    private final String label;
    private final List<Patient> patients = new ArrayList<>();

    public PatientGroup(String label) {
        this.label = label;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public List<Patient> getPatients() {
        return patients;
    }
}
