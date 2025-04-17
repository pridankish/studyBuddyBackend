package org.example.diplomabackend.exceptions.notFound;

public class GroupNotFoundException extends NotFoundException {
    public GroupNotFoundException(String message) {
        super(message);
    }
}