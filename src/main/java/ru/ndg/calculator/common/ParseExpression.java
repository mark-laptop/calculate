package ru.ndg.calculator.common;

public interface ParseExpression {

    Expression parse(String rawInputExpression);

    String arabicNumericToRoman(int numeric);

    String romanNumericToArabic(String input);
}
