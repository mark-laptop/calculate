package ru.ndg.calculator.common;

/**
 * Сlass that contains information for evaluating an expression
 */
public class Expression {

    private int firstOperand;
    private String operation;
    private int secondOperand;
    private boolean isRomeDigit;

    Expression(int firstOperand, String operation, int secondOperand, boolean isRomeDigit) {
        this.firstOperand = firstOperand;
        this.operation = operation;
        this.secondOperand = secondOperand;
        this.isRomeDigit = isRomeDigit;
    }

    public int getFirstOperand() {
        return firstOperand;
    }

    public String getOperation() {
        return operation;
    }

    public int getSecondOperand() {
        return secondOperand;
    }

    public boolean isRomeDigit() {
        return isRomeDigit;
    }
}
