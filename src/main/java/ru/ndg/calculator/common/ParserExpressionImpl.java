package ru.ndg.calculator.common;

import ru.ndg.calculator.exception.NumericOutOfRangeException;
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

    private boolean isRomeNumeric;

    /**
     * Enum to help identify digits
     */
    private enum Numeric {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int numeric;

        Numeric(int numeric) {
            this.numeric = numeric;
        }

        public int getNumeric() {
            return numeric;
        }

        public static List<Numeric> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((Numeric e) -> e.numeric).reversed())
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Expression parse(String rawInputExpression) {

        isRomeNumeric = false;

        if (Objects.isNull(rawInputExpression) || rawInputExpression.isEmpty())
            throw new IncorrectInputExpressionException("Данные не должны быть пустыми!");

        String[] arrayInputExpression = new String[3];
        try {
            parseStringToStringArray(arrayInputExpression, rawInputExpression);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectInputExpressionException("Ввели не верное выражение!");
        }

        parseRomeNumericToArabic(arrayInputExpression);

        int firstOperand;
        int secondOperand;
        try {
            firstOperand = Integer.parseInt(arrayInputExpression[0]);
            secondOperand = Integer.parseInt(arrayInputExpression[2]);
        } catch (NumberFormatException e) {
            throw new IncorrectInputOperandException("Введены не корректные числа, числа должны быть только целые!", e);
        }

        if (firstOperand < 1 || firstOperand > 10 || secondOperand < 1 || secondOperand > 10) {
            throw new NumericOutOfRangeException("Числа должны быть в диапазоне от 1 до 10!");
        }
        String operation = arrayInputExpression[1];
        return new ExpressionImpl(firstOperand, operation, secondOperand, isRomeNumeric);

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

    private void parseRomeNumericToArabic(String[] arrayInputExpression) {
        String rawFirstOperand = arrayInputExpression[0].trim();
        String rawSecondOperand = arrayInputExpression[2].trim();
        boolean isRomeFirstNumeric = isRomeNumeric(rawFirstOperand);
        boolean isRomeSecondNumeric = isRomeNumeric(rawSecondOperand);

        if (!(isRomeFirstNumeric && isRomeSecondNumeric) && !(!isRomeFirstNumeric && !isRomeSecondNumeric)) {
            throw new IncorrectInputOperandException("Цифры должны быть все или арабскими или римскими!");
        }
        if (isRomeFirstNumeric) {
            isRomeNumeric = true;
            arrayInputExpression[0] = romanNumericToArabic(rawFirstOperand);
            arrayInputExpression[2] = romanNumericToArabic(rawSecondOperand);
        }
    }


    public String romanNumericToArabic(String input) {
        String romanNumeric = input.toUpperCase();
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

        while ((romanNumeric.length() > 0) && (index < numbers.size())) {
            Numeric symbol = numbers.get(index);
            if (romanNumeric.startsWith(symbol.name())) {
                result += symbol.getNumeric();

                if (symbol.name().contains(Numeric.I.name())) ICount++;
                if (symbol.name().contains(Numeric.X.name())) XCount++;
                if (symbol.name().contains(Numeric.C.name())) CCount++;
                if (symbol.name().contains(Numeric.M.name())) MCount++;
                if (symbol.name().contains(Numeric.V.name())) VCount++;
                if (symbol.name().contains(Numeric.L.name())) LCount++;
                if (symbol.name().contains(Numeric.D.name())) DCount++;

                romanNumeric = romanNumeric.substring(symbol.name().length());
            } else {
                index++;
            }
        }

        if (romanNumeric.length() > 0 || !isCorrectNumeric(ICount, XCount, CCount, MCount, VCount, LCount, DCount)) {
            throw new NumericOutOfRangeException("Значение: " + input + " не может быть преобразована в римскую цифру");
        }

        return String.valueOf(result);
    }

    @Override
    public String arabicNumericToRoman(int numeric) {

        if (numeric > 4000) {
            throw new NumericOutOfRangeException(numeric + " римское число должно быть не болше 4000");
        }

        boolean isNegative = false;
        if (numeric < 0) {
            isNegative = true;
            numeric = numeric * -1;
        }

        List<Numeric> numbers = Numeric.getReverseSortedValues();

        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while ((index < numbers.size())) {
            Numeric currentSymbol = numbers.get(index);
            if (currentSymbol.getNumeric() <= numeric) {
                stringBuilder.append(currentSymbol.name());
                numeric -= currentSymbol.getNumeric();
            } else {
                index++;
            }
        }

        if (isNegative) {
            return "-" + stringBuilder.toString();
        }

        return stringBuilder.toString();
    }

    private boolean isRomeNumeric(String inputData) {
        if (Objects.isNull(inputData) || inputData.isEmpty()) return false;
        try {
            romanNumericToArabic(inputData);
        } catch (NumericOutOfRangeException e) {
            return false;
        }
        return true;
    }

    private boolean isCorrectNumeric(int ICount, int XCount, int CCount, int MCount, int VCount, int LCount, int DCount) {
        if (ICount > 3) return false;
        if (XCount > 3) return false;
        if (CCount > 3) return false;
        if (VCount > 1) return false;
        if (LCount > 1) return false;
        if (DCount > 1) return false;
        return MCount <= 3;
    }
}
