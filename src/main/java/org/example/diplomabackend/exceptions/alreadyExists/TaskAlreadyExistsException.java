package org.example.diplomabackend.exceptions.alreadyExists;

public class TaskAlreadyExistsException extends AlreadyExistsException {
    public TaskAlreadyExistsException(String message) {
        super(message);
    }
}
