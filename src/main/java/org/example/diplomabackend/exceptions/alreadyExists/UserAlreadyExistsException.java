package org.example.diplomabackend.exceptions.alreadyExists;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
