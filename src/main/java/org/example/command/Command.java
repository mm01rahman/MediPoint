package org.example.command;

public interface Command {
    void execute();
    default void undo() {
        // optional
    }
}
