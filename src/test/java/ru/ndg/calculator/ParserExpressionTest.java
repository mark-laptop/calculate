package ru.ndg.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ndg.calculator.common.ExpressionImpl;
import ru.ndg.calculator.common.ParseExpression;
import ru.ndg.calculator.common.ParserExpressionImpl;

class ParserExpressionTest {

    @Test
    void arabicDigitToRomanTest() {
        ParseExpression parseExpression = new ParserExpressionImpl();
        Assertions.assertEquals("I", parseExpression.arabicNumericToRoman(1));
        Assertions.assertEquals("II", parseExpression.arabicNumericToRoman(2));
        Assertions.assertEquals("III", parseExpression.arabicNumericToRoman(3));
        Assertions.assertEquals("IV", parseExpression.arabicNumericToRoman(4));
        Assertions.assertEquals("V", parseExpression.arabicNumericToRoman(5));
        Assertions.assertEquals("VI", parseExpression.arabicNumericToRoman(6));
        Assertions.assertEquals("VII", parseExpression.arabicNumericToRoman(7));
        Assertions.assertEquals("VIII", parseExpression.arabicNumericToRoman(8));
        Assertions.assertEquals("IX", parseExpression.arabicNumericToRoman(9));
        Assertions.assertEquals("X", parseExpression.arabicNumericToRoman(10));
    }

    @Test
    void romanNumericToArabicTest() {
        ParseExpression parseExpression = new ParserExpressionImpl();
        Assertions.assertEquals("1", parseExpression.romanNumericToArabic("I"));
        Assertions.assertEquals("2", parseExpression.romanNumericToArabic("II"));
        Assertions.assertEquals("3", parseExpression.romanNumericToArabic("III"));
        Assertions.assertEquals("4", parseExpression.romanNumericToArabic("IV"));
        Assertions.assertEquals("5", parseExpression.romanNumericToArabic("V"));
        Assertions.assertEquals("6", parseExpression.romanNumericToArabic("VI"));
        Assertions.assertEquals("7", parseExpression.romanNumericToArabic("VII"));
        Assertions.assertEquals("8", parseExpression.romanNumericToArabic("VIII"));
        Assertions.assertEquals("9", parseExpression.romanNumericToArabic("IX"));
        Assertions.assertEquals("10", parseExpression.romanNumericToArabic("X"));
    }

    @Test
    void parseTest() {
        ParseExpression parseExpression = new ParserExpressionImpl();
        Assertions.assertEquals(new ExpressionImpl(1, "+", 1, true), parseExpression.parse("I + I"));
        Assertions.assertEquals(new ExpressionImpl(6, "*", 3, true), parseExpression.parse("VI * III"));
        Assertions.assertEquals(new ExpressionImpl(1, "/", 4, true), parseExpression.parse("I / IV"));
        Assertions.assertEquals(new ExpressionImpl(4, "+", 5, true), parseExpression.parse("IV + V"));
        Assertions.assertEquals(new ExpressionImpl(10, "+", 10, true), parseExpression.parse("X + X"));
        Assertions.assertEquals(new ExpressionImpl(9, "-", 8, true), parseExpression.parse("IX - VIII"));
        Assertions.assertEquals(new ExpressionImpl(7, "+", 2, true), parseExpression.parse("VII + II"));

        Assertions.assertEquals(new ExpressionImpl(1, "+", 1, false), parseExpression.parse("1 + 1"));
        Assertions.assertEquals(new ExpressionImpl(6, "*", 3, false), parseExpression.parse("6 * 3"));
        Assertions.assertEquals(new ExpressionImpl(1, "/", 4, false), parseExpression.parse("1 / 4"));
        Assertions.assertEquals(new ExpressionImpl(4, "+", 5, false), parseExpression.parse("4 + 5"));
        Assertions.assertEquals(new ExpressionImpl(10, "+", 10, false), parseExpression.parse("10 + 10"));
        Assertions.assertEquals(new ExpressionImpl(9, "-", 8, false), parseExpression.parse("9 - 8"));
        Assertions.assertEquals(new ExpressionImpl(7, "+", 2, false), parseExpression.parse("7 + 2"));
    }
}
