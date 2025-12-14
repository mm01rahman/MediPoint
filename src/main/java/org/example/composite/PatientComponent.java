package org.example.composite;

import org.example.iterator.PatientIterator;
import org.example.model.Patient;

import java.util.Iterator;

public interface PatientComponent extends Iterable<Patient> {
    String getLabel();

    @Override
    default Iterator<Patient> iterator() {
        return new PatientIterator(getPatients());
    }

    java.util.List<Patient> getPatients();
}
