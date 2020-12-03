package ru.ndg.calculator.common;

public interface Expression {

    int getFirstOperand();

    String getOperation();

    int getSecondOperand();

    boolean isRomeNumber();
}
