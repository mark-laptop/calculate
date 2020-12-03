package ru.ndg.calculator.common;

import java.util.Objects;

/**
 * Сlass that contains information for evaluating an expression
 */
public class Expression {

    private int firstOperand;
    private String operation;
    private int secondOperand;
    private boolean isRomeDigit;

    public Expression(int firstOperand, String operation, int secondOperand, boolean isRomeDigit) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expression that = (Expression) o;

        if (firstOperand != that.firstOperand) return false;
        if (secondOperand != that.secondOperand) return false;
        if (isRomeDigit != that.isRomeDigit) return false;
        return Objects.equals(operation, that.operation);
    }

    @Override
    public int hashCode() {
        int result = firstOperand;
        result = 31 * result + (operation != null ? operation.hashCode() : 0);
        result = 31 * result + secondOperand;
        result = 31 * result + (isRomeDigit ? 1 : 0);
        return result;
    }
}
