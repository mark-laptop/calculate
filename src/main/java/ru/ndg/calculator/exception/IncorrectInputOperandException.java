package ru.ndg.calculator.exception;

public class IncorrectInputOperandException extends RuntimeException {
    public IncorrectInputOperandException(String message) {
        super(message);
    }

    public IncorrectInputOperandException(String message, Throwable cause) {
        super(message, cause);
    }
}
