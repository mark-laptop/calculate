package ru.ndg.calculator.common;

/**
 * Enum is for a operation
 */
public enum Operations {
    ADD("+"), SUBTRACT("-"), DIVIDE("/"), MULTIPLY("*"), EXIT("exit");

    private String operation;

    Operations(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}
