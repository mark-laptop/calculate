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

        int result;

        String operation = expression.getOperation();

        if (ADD.getOperation().equals(operation)) {
            result = add(expression);
        } else if (SUBTRACT.getOperation().equals(operation)) {
            result = subtract(expression);
        } else if (MULTIPLY.getOperation().equals(operation)) {
            result = multiply(expression);
        } else if (DIVIDE.getOperation().equals(operation)) {
            result = divide(expression);
        } else {
            throw new IncorrectInputOperationException("Не верный оператор должен быть '+, -, /, *'");
        }

        return expression.isRomeDigit() ? ParserExpression.arabicDigitToRoman(result) : String.valueOf(result);
    }

    private static int divide(Expression expression) {
        return expression.getFirstOperand() / expression.getSecondOperand();
    }

    private static int multiply(Expression expression) {
        return expression.getFirstOperand() * expression.getSecondOperand();
    }

    private static int add(Expression expression) {
        return expression.getFirstOperand() + expression.getSecondOperand();
    }

    private static int subtract(Expression expression) {
        return expression.getFirstOperand() - expression.getSecondOperand();
    }
}
