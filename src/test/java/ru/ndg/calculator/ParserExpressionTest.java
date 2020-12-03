package ru.ndg.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ndg.calculator.common.Expression;
import ru.ndg.calculator.common.ParserExpression;

class ParserExpressionTest {

    @Test
    void arabicDigitToRomanTest() {
        Assertions.assertEquals("I", ParserExpression.arabicDigitToRoman(1));
        Assertions.assertEquals("II", ParserExpression.arabicDigitToRoman(2));
        Assertions.assertEquals("III", ParserExpression.arabicDigitToRoman(3));
        Assertions.assertEquals("IV", ParserExpression.arabicDigitToRoman(4));
        Assertions.assertEquals("V", ParserExpression.arabicDigitToRoman(5));
        Assertions.assertEquals("VI", ParserExpression.arabicDigitToRoman(6));
        Assertions.assertEquals("VII", ParserExpression.arabicDigitToRoman(7));
        Assertions.assertEquals("VIII", ParserExpression.arabicDigitToRoman(8));
        Assertions.assertEquals("IX", ParserExpression.arabicDigitToRoman(9));
        Assertions.assertEquals("X", ParserExpression.arabicDigitToRoman(10));
    }

    @Test
    void parseTest() {
        Assertions.assertEquals(new Expression(1, "+", 1, true), ParserExpression.parse("I + I"));
        Assertions.assertEquals(new Expression(6, "*", 3, true), ParserExpression.parse("VI * III"));
        Assertions.assertEquals(new Expression(1, "/", 4, true), ParserExpression.parse("I / IV"));
        Assertions.assertEquals(new Expression(4, "+", 5, true), ParserExpression.parse("IV + V"));
        Assertions.assertEquals(new Expression(10, "+", 10, true), ParserExpression.parse("X + X"));
        Assertions.assertEquals(new Expression(9, "-", 8, true), ParserExpression.parse("IX - VIII"));
        Assertions.assertEquals(new Expression(7, "+", 2, true), ParserExpression.parse("VII + II"));

        Assertions.assertEquals(new Expression(1, "+", 1, false), ParserExpression.parse("1 + 1"));
        Assertions.assertEquals(new Expression(6, "*", 3, false), ParserExpression.parse("6 * 3"));
        Assertions.assertEquals(new Expression(1, "/", 4, false), ParserExpression.parse("1 / 4"));
        Assertions.assertEquals(new Expression(4, "+", 5, false), ParserExpression.parse("4 + 5"));
        Assertions.assertEquals(new Expression(10, "+", 10, false), ParserExpression.parse("10 + 10"));
        Assertions.assertEquals(new Expression(9, "-", 8, false), ParserExpression.parse("9 - 8"));
        Assertions.assertEquals(new Expression(7, "+", 2, false), ParserExpression.parse("7 + 2"));
    }
}
