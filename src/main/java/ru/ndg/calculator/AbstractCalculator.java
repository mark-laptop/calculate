package ru.ndg.calculator;

import ru.ndg.calculator.common.Expression;
import ru.ndg.calculator.common.ParseExpression;

/**
 * Abstract class for evaluating an incoming expression in Arabic or Rome number
 */
public abstract class AbstractCalculator implements Calculate {

    private ParseExpression parseExpression;

    ParseExpression getParseExpression() {
        return parseExpression;
    }

    AbstractCalculator(ParseExpression parseExpression) {
        this.parseExpression = parseExpression;
    }

    @Override
    public abstract String getResult(String rawInputExpression);

    int divide(Expression expression) {
        return expression.getFirstOperand() / expression.getSecondOperand();
    }

    int multiply(Expression expression) {
        return expression.getFirstOperand() * expression.getSecondOperand();
    }

    int add(Expression expression) {
        return expression.getFirstOperand() + expression.getSecondOperand();
    }

    int subtract(Expression expression) {
        return expression.getFirstOperand() - expression.getSecondOperand();
    }
}
