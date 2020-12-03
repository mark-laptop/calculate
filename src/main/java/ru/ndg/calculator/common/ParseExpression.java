package ru.ndg.calculator.common;

public interface ParseExpression {

    Expression parse(String rawInputExpression);

    String arabicNumberToRoman(int numeric);

    String romanNumberToArabic(String input);
}
