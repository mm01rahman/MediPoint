package org.example.strategy;

import org.example.model.Patient;

public interface ValidationStrategy {
    boolean isValid(Patient patient);
    String getMessage();
}
