package org.example.memento;

import java.util.Stack;

public class PatientFormCaretaker {
    private final Stack<PatientFormMemento> history = new Stack<>();

    public void saveState(PatientFormMemento memento) {
        history.push(memento);
    }

    public PatientFormMemento undo() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }
}
