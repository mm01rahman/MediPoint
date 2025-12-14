package org.example.observer;

import org.example.model.Patient;

public interface PatientEventListener {
    void onPatientAdded(Patient patient);
}
