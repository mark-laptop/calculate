package ru.ndg.calculator.common;

/**
 * Сlass that contains information for evaluating an expression
 */
public class ExpressionImpl implements Expression {

    private int firstOperand;
    private String operation;
    private int secondOperand;
    private boolean isRomeNumeric;

    public ExpressionImpl(int firstOperand, String operation, int secondOperand, boolean isRomeNumeric) {
        this.firstOperand = firstOperand;
        this.operation = operation;
        this.secondOperand = secondOperand;
        this.isRomeNumeric = isRomeNumeric;
    }

    @Override
    public int getFirstOperand() {
        return firstOperand;
    }

    @Override
    public String getOperation() {
        return operation;
    }

    @Override
    public int getSecondOperand() {
        return secondOperand;
    }

    @Override
    public boolean isRomeNumeric() {
        return isRomeNumeric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpressionImpl that = (ExpressionImpl) o;

        if (firstOperand != that.firstOperand) return false;
        if (secondOperand != that.secondOperand) return false;
        if (isRomeNumeric != that.isRomeNumeric) return false;
        return operation.equals(that.operation);
    }

    @Override
    public int hashCode() {
        int result = firstOperand;
        result = 31 * result + operation.hashCode();
        result = 31 * result + secondOperand;
        result = 31 * result + (isRomeNumeric ? 1 : 0);
        return result;
    }
}
