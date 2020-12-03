package ru.ndg.calculator;

import ru.ndg.calculator.common.Expression;
import ru.ndg.calculator.common.ParseExpression;
import ru.ndg.calculator.exception.IncorrectInputOperationException;

import static ru.ndg.calculator.common.Operations.ADD;
import static ru.ndg.calculator.common.Operations.DIVIDE;
import static ru.ndg.calculator.common.Operations.MULTIPLY;
import static ru.ndg.calculator.common.Operations.SUBTRACT;

/**
 * Сlass for evaluating an incoming expression in Arabic or Rome number
 */
public final class CalculateImpl extends AbstractCalculator {

    public CalculateImpl(ParseExpression parseExpression) {
        super(parseExpression);
    }

    /**
     * Method return result executing
     * @param rawInputExpression input string expression
     *
     * @return string Roman or Arabic number
     *
     * @throws IncorrectInputOperationException
     *          when the incoming operation not supported
     * */
    @Override
    public String getResult(String rawInputExpression) {
        Expression expression = getParseExpression().parse(rawInputExpression);

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

        return expression.isRomeNumber() ? getParseExpression().arabicNumberToRoman(result) : String.valueOf(result);
    }
}
