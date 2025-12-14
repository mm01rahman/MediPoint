package org.example.composite;

import org.example.model.Patient;

import java.util.Collections;
import java.util.List;

public class PatientLeaf implements PatientComponent {
    private final Patient patient;

    public PatientLeaf(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getLabel() {
        return patient.getName();
    }

    @Override
    public List<Patient> getPatients() {
        return Collections.singletonList(patient);
    }
}
