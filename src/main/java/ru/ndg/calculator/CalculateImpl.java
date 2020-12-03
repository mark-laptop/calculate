package ru.ndg.calculator;

import ru.ndg.calculator.common.Expression;
import ru.ndg.calculator.common.ParseExpression;
import ru.ndg.calculator.common.ParserExpressionImpl;
import ru.ndg.calculator.exception.IncorrectInputOperationException;

import static ru.ndg.calculator.common.Operations.ADD;
import static ru.ndg.calculator.common.Operations.DIVIDE;
import static ru.ndg.calculator.common.Operations.MULTIPLY;
import static ru.ndg.calculator.common.Operations.SUBTRACT;

/**
 * Сlass for evaluating an incoming expression
 */
public final class CalculateImpl implements Calculate {

    @Override
    public String getResult(String rawInputExpression) {
        ParseExpression parseExpression = new ParserExpressionImpl();
        Expression expression = parseExpression.parse(rawInputExpression);

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
            throw new IncorrectInputOperationException("Не верный оператор, должен быть '+, -, /, *'");
        }

        return expression.isRomeNumeric() ? parseExpression.arabicNumericToRoman(result) : String.valueOf(result);
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
