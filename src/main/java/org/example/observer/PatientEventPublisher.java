package org.example.observer;

import org.example.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientEventPublisher {
    private final List<PatientEventListener> listeners = new ArrayList<>();

    public void register(PatientEventListener listener) {
        listeners.add(listener);
    }

    protected void notifyPatientAdded(Patient patient) {
        for (PatientEventListener listener : listeners) {
            listener.onPatientAdded(patient);
        }
    }
}
