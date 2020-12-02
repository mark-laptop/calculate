package ru.ndg.calculator;

import ru.ndg.calculator.common.Expression;
import ru.ndg.calculator.common.ParserExpression;
import ru.ndg.calculator.exception.IncorrectInputExpressionException;
import ru.ndg.calculator.exception.IncorrectInputOperationException;

import static ru.ndg.calculator.common.Operations.*;

/**
 * Сlass for evaluating an incoming expression
 */
public final class Calculate {

    public static String getResult(String rawInputExpression) {
        Expression expression = ParserExpression.parse(rawInputExpression);

        String operation = expression.getOperation();

        if (ADD.getOperation().equals(operation)) {
            return add(expression);
        } else if (SUBTRACT.getOperation().equals(operation)) {
            return subtract(expression);
        } else if (MULTIPLY.getOperation().equals(operation)) {
            return multiply(expression);
        } else if (DIVIDE.getOperation().equals(operation)) {
            return divide(expression);
        } else {
            throw new IncorrectInputOperationException("Не верный оператор должен быть '+, -, /, *'");
        }
    }

    private static String divide(Expression expression) {
        int result = expression.getFirstOperand() / expression.getSecondOperand();
        if (expression.isRomeDigit()) {
            return ParserExpression.arabicDigitToRoman(result);
        }
        return String.valueOf(result);
    }

    private static String multiply(Expression expression) {
        int result = expression.getFirstOperand() * expression.getSecondOperand();
        if (expression.isRomeDigit()) {
            return ParserExpression.arabicDigitToRoman(result);
        }
        return String.valueOf(result);
    }

    private static String add(Expression expression) {
        int result = expression.getFirstOperand() + expression.getSecondOperand();
        if (expression.isRomeDigit()) {
            return ParserExpression.arabicDigitToRoman(result);
        }
        return String.valueOf(result);
    }

    private static String subtract(Expression expression) {
        int result = expression.getFirstOperand() - expression.getSecondOperand();
        if (expression.isRomeDigit()) {
            return ParserExpression.arabicDigitToRoman(result);
        }
        return String.valueOf(result);
    }
}
