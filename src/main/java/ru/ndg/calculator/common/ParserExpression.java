package ru.ndg.calculator.common;

import ru.ndg.calculator.exception.DigitOutOfRangeException;
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
public final class ParserExpression {

    private static boolean isRomeDigit;

    /**
     * Enum to help identify digits
     */
    private enum Digit {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int digit;

        Digit(int digit) {
            this.digit = digit;
        }

        public int getDigit() {
            return digit;
        }

        public static List<Digit> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((Digit e) -> e.digit).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static Expression parse(String rawInputExpression) {

        ParserExpression.isRomeDigit = false;

        if (Objects.isNull(rawInputExpression) || rawInputExpression.isEmpty())
            throw new IncorrectInputExpressionException("Данные не должны быть пустыми!");

        String[] arrayInputExpression = new String[3];
        try {
            parseStringToStringArray(arrayInputExpression, rawInputExpression);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncorrectInputExpressionException("Ввели не верное выражение!");
        }

        parseRomeDigitToArabic(arrayInputExpression);

        int firstOperand;
        int secondOperand;
        try {
            firstOperand = Integer.parseInt(arrayInputExpression[0]);
            secondOperand = Integer.parseInt(arrayInputExpression[2]);
        } catch (NumberFormatException e) {
            throw new IncorrectInputOperandException("Введены не корректные числа, числа должны быть только целые!", e);
        }

        if (firstOperand < 1 || firstOperand > 10 || secondOperand < 1 || secondOperand > 10) {
            throw new DigitOutOfRangeException("Числа должны быть в диапазоне от 1 до 10!");
        }
        String operation = arrayInputExpression[1];
        return new Expression(firstOperand, operation, secondOperand, isRomeDigit);

    }

    private static void parseStringToStringArray(String[] arrayInputExpression, String inputString) {
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

    private static void parseRomeDigitToArabic(String[] arrayInputExpression) {
        String rawFirstOperand = arrayInputExpression[0].trim();
        String rawSecondOperand = arrayInputExpression[2].trim();
        boolean isRomeFirstDigit = isRomeDigit(rawFirstOperand);
        boolean isRomeSecondDigit = isRomeDigit(rawSecondOperand);

        if (!(isRomeFirstDigit && isRomeSecondDigit) && !(!isRomeFirstDigit && !isRomeSecondDigit)) {
            throw new IncorrectInputOperandException("Цифры должны быть все или арабскими или римскими!");
        }
        if (isRomeFirstDigit) {
            ParserExpression.isRomeDigit = true;
            arrayInputExpression[0] = romanToArabic(rawFirstOperand);
            arrayInputExpression[2] = romanToArabic(rawSecondOperand);
        }
    }


    private static String romanToArabic(String input) {
        String romanDigit = input.toUpperCase();
        int result = 0;

        List<Digit> digits = Digit.getReverseSortedValues();

        int index = 0;

        int ICount = 0;
        int XCount = 0;
        int CCount = 0;
        int MCount = 0;
        int VCount = 0;
        int LCount = 0;
        int DCount = 0;

        while ((romanDigit.length() > 0) && (index < digits.size())) {
            Digit symbol = digits.get(index);
            if (romanDigit.startsWith(symbol.name())) {
                result += symbol.getDigit();

                if (symbol.name().contains(Digit.I.name())) ICount++;
                if (symbol.name().contains(Digit.X.name())) XCount++;
                if (symbol.name().contains(Digit.C.name())) CCount++;
                if (symbol.name().contains(Digit.M.name())) MCount++;
                if (symbol.name().contains(Digit.V.name())) VCount++;
                if (symbol.name().contains(Digit.L.name())) LCount++;
                if (symbol.name().contains(Digit.D.name())) DCount++;

                romanDigit = romanDigit.substring(symbol.name().length());
            } else {
                index++;
            }
        }

        if (romanDigit.length() > 0 || !isCorrectDigit(ICount, XCount, CCount, MCount, VCount, LCount, DCount)) {
            throw new DigitOutOfRangeException("Значение: " + input + " не может быть преобразована в римскую цифру");
        }

        return String.valueOf(result);
    }

    public static String arabicDigitToRoman(int number) {
        if ((number < 1) || (number > 4000)) {
            throw new DigitOutOfRangeException(number + " римское число должно быть в диапазоне от 1 до 4000");
        }

        List<Digit> digits = Digit.getReverseSortedValues();

        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();

        while ((number > 0) && (index < digits.size())) {
            Digit currentSymbol = digits.get(index);
            if (currentSymbol.getDigit() <= number) {
                stringBuilder.append(currentSymbol.name());
                number -= currentSymbol.getDigit();
            } else {
                index++;
            }
        }

        return stringBuilder.toString();
    }

    private static boolean isRomeDigit(String inputData) {
        if (Objects.isNull(inputData) || inputData.isEmpty()) return false;
        try {
            romanToArabic(inputData);
        } catch (DigitOutOfRangeException e) {
            return false;
        }
        return true;
    }

    private static boolean isCorrectDigit(int ICount, int XCount, int CCount, int MCount, int VCount, int LCount, int DCount) {
        if (ICount > 3) return false;
        if (XCount > 3) return false;
        if (CCount > 3) return false;
        if (VCount > 1) return false;
        if (LCount > 1) return false;
        if (DCount > 1) return false;
        return MCount <= 3;
    }
}
