package org.example.command;

import org.example.facade.HospitalFacade;
import org.example.memento.PatientFormCaretaker;
import org.example.memento.PatientFormMemento;
import org.example.model.Patient;
import org.example.strategy.ValidationStrategy;

import javax.swing.*;

public class AddPatientCommand implements Command {
    private final HospitalFacade facade;
    private final Patient patient;
    private final ValidationStrategy validationStrategy;
    private final PatientFormCaretaker caretaker;

    public AddPatientCommand(HospitalFacade facade, Patient patient, ValidationStrategy validationStrategy,
                             PatientFormCaretaker caretaker) {
        this.facade = facade;
        this.patient = patient;
        this.validationStrategy = validationStrategy;
        this.caretaker = caretaker;
    }

    @Override
    public void execute() {
        if (!validationStrategy.isValid(patient)) {
            JOptionPane.showMessageDialog(null, validationStrategy.getMessage());
            return;
        }
        facade.addPatient(patient);
        caretaker.saveState(new PatientFormMemento(patient));
        JOptionPane.showMessageDialog(null, "Patient Added & Room Marked as Occupied!");
    }

    @Override
    public void undo() {
        PatientFormMemento memento = caretaker.undo();
        if (memento != null) {
            facade.reopenRoom(memento.getPatient().getRoom());
        }
    }
}
