package org.example.diplomabackend.exceptions.alreadyExists;

public class PersonalEventAlreadyExistsException extends AlreadyExistsException {
    public PersonalEventAlreadyExistsException(String message) {
        super(message);
    }
}
