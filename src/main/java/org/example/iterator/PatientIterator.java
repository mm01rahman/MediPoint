package org.example.iterator;

import org.example.model.Patient;

import java.util.Iterator;
import java.util.List;

public class PatientIterator implements Iterator<Patient> {
    private final List<Patient> patients;
    private int position = 0;

    public PatientIterator(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean hasNext() {
        return position < patients.size();
    }

    @Override
    public Patient next() {
        return patients.get(position++);
    }
}
