package ru.ndg.calculator.exception;

public class NumericOutOfRangeException extends RuntimeException {
    public NumericOutOfRangeException(String message) {
        super(message);
    }
}
