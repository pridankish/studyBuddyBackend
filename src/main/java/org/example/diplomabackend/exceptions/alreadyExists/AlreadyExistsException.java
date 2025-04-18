package org.example.diplomabackend.exceptions.alreadyExists;

public abstract class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message) {
        super(message);
    }
}
