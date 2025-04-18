package org.example.diplomabackend.exceptions;

import org.example.diplomabackend.exceptions.alreadyExists.AlreadyExistsException;
import org.example.diplomabackend.exceptions.notFound.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public Map<String, String> notFound(NotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyExistsException.class)
    public Map<String, String> alreadyExists(AlreadyExistsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return error;
    }
}
