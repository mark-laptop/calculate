package ru.ndg.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void getResultTest() {
        Assertions.assertEquals("II", Calculate.getResult("I + I"));
        Assertions.assertEquals("III", Calculate.getResult("II + I"));
        Assertions.assertEquals("XII", Calculate.getResult("IV * III"));
        Assertions.assertEquals("XI", Calculate.getResult("VI + V"));
        Assertions.assertEquals("-I", Calculate.getResult("VII - VIII"));
        Assertions.assertEquals("I", Calculate.getResult("X / IX"));

        Assertions.assertEquals("2", Calculate.getResult("1 + 1"));
        Assertions.assertEquals("3", Calculate.getResult("2 + 1"));
        Assertions.assertEquals("12", Calculate.getResult("4 * 3"));
        Assertions.assertEquals("11", Calculate.getResult("6 + 5"));
        Assertions.assertEquals("-1", Calculate.getResult("7 - 8"));
        Assertions.assertEquals("1", Calculate.getResult("10 / 9"));
    }
}
