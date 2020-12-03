package ru.ndg.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void getResultTest() {
        Calculate calculate = new CalculateImpl();
        Assertions.assertEquals("II", calculate.getResult("I + I"));
        Assertions.assertEquals("III", calculate.getResult("II + I"));
        Assertions.assertEquals("XII", calculate.getResult("IV * III"));
        Assertions.assertEquals("XI", calculate.getResult("VI + V"));
        Assertions.assertEquals("-I", calculate.getResult("VII - VIII"));
        Assertions.assertEquals("I", calculate.getResult("X / IX"));

        Assertions.assertEquals("2", calculate.getResult("1 + 1"));
        Assertions.assertEquals("3", calculate.getResult("2 + 1"));
        Assertions.assertEquals("12", calculate.getResult("4 * 3"));
        Assertions.assertEquals("11", calculate.getResult("6 + 5"));
        Assertions.assertEquals("-1", calculate.getResult("7 - 8"));
        Assertions.assertEquals("1", calculate.getResult("10 / 9"));
    }
}
