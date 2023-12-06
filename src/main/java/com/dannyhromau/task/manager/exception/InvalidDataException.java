package com.dannyhromau.task.manager.exception;

public class InvalidDataException extends IllegalArgumentException {
    public InvalidDataException(String message) {
        super(message);
    }
}
