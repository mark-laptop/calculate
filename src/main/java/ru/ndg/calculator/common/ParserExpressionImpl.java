package ru.ndg.calculator.common;

import ru.ndg.calculator.exception.NumberOutOfRangeException;
import ru.ndg.calculator.exception.IncorrectInputExpressionException;
import ru.ndg.calculator.exception.IncorrectInputOperandException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Сlass for parsing an incoming string in an expression
 */
public class ParserExpressionImpl implements ParseExpression {

    private boolean isRomeNumber;

    /**
     * Enum to help identify digits
     */
    private enum Numeric {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50),
        XC(90), C(100), CD(400), D(500), CM(900), M(1000);

        private int number;

        Numeric(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public static List<Numeric> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((Numeric e) -> e.number).reversed())
                    .collect(Collectors.toList());
        }
    }

    /**
     * Method parsing an incoming string in an expression
     * @param rawInputExpression string input expression
     *
     * @return expression object that contains the result of executing the method
     *
     * @throws  IncorrectInputExpressionException
     *          if the incoming string expression not correct
     * @throws  IncorrectInputOperandException
     *          if the incoming string expression not correct
     * @throws NumberOutOfRangeException
     *          when the number is out of the range from 1 to 10
     * */
    @Override
    public Expression parse(String rawInputExpression) {

        isRomeNumber = false;

        if (Objects.isNull(rawInputExpression) || rawInputExpression.isEmpty())
            throw new IncorrectInputExpressionException("Данные не должны быть пустыми!");

        String[] arrayInputExpression = new String[3];
        try {
            parseStringToStringArray(arrayInputExpression, rawInputExpression);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectInputExpressionException("Ввели не верное выражение!");
        }

        parseRomeNumberToArabic(arrayInputExpression);

        int firstOperand;
        int secondOperand;
        try {
            firstOperand = Integer.parseInt(arrayInputExpression[0]);
            secondOperand = Integer.parseInt(arrayInputExpression[2]);
        } catch (NumberFormatException e) {
            throw new IncorrectInputOperandException("Введены не корректные числа, числа должны быть только целые!", e);
        }

        if (firstOperand < 1 || firstOperand > 10 || secondOperand < 1 || secondOperand > 10) {
            throw new NumberOutOfRangeException("Числа должны быть в диапазоне от 1 до 10!");
        }
        String operation = arrayInputExpression[1];
        return new ExpressionImpl(firstOperand, operation, secondOperand, isRomeNumber);

    }

    private void parseStringToStringArray(String[] arrayInputExpression, String inputString) {
        int index = 0;
        String[] arrayString = inputString.split("\\s");
        if (arrayString.length < 3) throw new IncorrectInputExpressionException("Ввели не верное выражение!");
        for (String element : arrayString) {
            if (!element.isEmpty()) {
                arrayInputExpression[index] = element;
                index++;
            }
        }
    }

    private void parseRomeNumberToArabic(String[] arrayInputExpression) {
        String rawFirstOperand = arrayInputExpression[0].trim();
        String rawSecondOperand = arrayInputExpression[2].trim();
        boolean isRomeFirstNumber = isRomeNumber(rawFirstOperand);
        boolean isRomeSecondNumber = isRomeNumber(rawSecondOperand);

        if (!(isRomeFirstNumber && isRomeSecondNumber) && !(!isRomeFirstNumber && !isRomeSecondNumber)) {
            throw new IncorrectInputOperandException("Цифры должны быть все или арабскими или римскими!");
        }
        if (isRomeFirstNumber) {
            isRomeNumber = true;
            arrayInputExpression[0] = romanNumberToArabic(rawFirstOperand);
            arrayInputExpression[2] = romanNumberToArabic(rawSecondOperand);
        }
    }

    /**
     * Method parsing an incoming string in an string Roman number
     * @param input string input Roman number
     *
     * @return string Arabic number
     *
     * @throws NumberOutOfRangeException
     *          when the number is out of the range from 1 to 10
     * */
    public String romanNumberToArabic(String input) {
        String romanNumber = input.toUpperCase();
        int result = 0;

        List<Numeric> numbers = Numeric.getReverseSortedValues();

        int index = 0;

        int ICount = 0;
        int XCount = 0;
        int CCount = 0;
        int MCount = 0;
        int VCount = 0;
        int LCount = 0;
        int DCount = 0;

        while ((romanNumber.length() > 0) && (index < numbers.size())) {
            Numeric symbol = numbers.get(index);
            if (romanNumber.startsWith(symbol.name())) {
                result += symbol.getNumber();

                if (symbol.name().contains(Numeric.I.name())) ICount++;
                if (symbol.name().contains(Numeric.X.name())) XCount++;
                if (symbol.name().contains(Numeric.C.name())) CCount++;
                if (symbol.name().contains(Numeric.M.name())) MCount++;
                if (symbol.name().contains(Numeric.V.name())) VCount++;
                if (symbol.name().contains(Numeric.L.name())) LCount++;
                if (symbol.name().contains(Numeric.D.name())) DCount++;

                romanNumber = romanNumber.substring(symbol.name().length());
            } else {
                index++;
            }
        }

        if (romanNumber.length() > 0 || !isCorrectNumber(ICount, XCount, CCount, MCount, VCount, LCount, DCount)) {
            throw new NumberOutOfRangeException("Значение: " + input + " не может быть преобразовано в римскую цифру");
        }

        return String.valueOf(result);
    }

    /**
     * Method parsing an incoming integer in an string Roman number
     * @param number input integer Arabic number
     *
     * @return string Roman number
     *
     * @throws NumberOutOfRangeException
     *          when the number is out of the range from 4000
     * */
    @Override
    public String arabicNumberToRoman(int number) {

        if (number > 4000) {
            throw new NumberOutOfRangeException(number + " римское число должно быть не болше 4000");
        }

        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number = number * -1;
        }

        List<Numeric> numbers = Numeric.getReverseSortedValues();

        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while ((index < numbers.size())) {
            Numeric currentSymbol = numbers.get(index);
            if (currentSymbol.getNumber() <= number) {
                stringBuilder.append(currentSymbol.name());
                number -= currentSymbol.getNumber();
            } else {
                index++;
            }
        }

        if (isNegative) {
            return "-" + stringBuilder.toString();
        }

        return stringBuilder.toString();
    }

    private boolean isRomeNumber(String inputData) {
        if (Objects.isNull(inputData) || inputData.isEmpty()) return false;
        try {
            romanNumberToArabic(inputData);
        } catch (NumberOutOfRangeException e) {
            return false;
        }
        return true;
    }

    private boolean isCorrectNumber(int ICount, int XCount, int CCount, int MCount, int VCount, int LCount, int DCount) {
        if (ICount > 3) return false;
        if (XCount > 3) return false;
        if (CCount > 3) return false;
        if (VCount > 1) return false;
        if (LCount > 1) return false;
        if (DCount > 1) return false;
        return MCount <= 3;
    }
}
