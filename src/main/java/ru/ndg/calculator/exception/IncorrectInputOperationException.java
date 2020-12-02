package ru.ndg.calculator.exception;

public class IncorrectInputOperationException extends RuntimeException {
    public IncorrectInputOperationException(String message) {
        super(message);
    }
}
