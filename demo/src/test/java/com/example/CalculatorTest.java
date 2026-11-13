package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link Calculator}.
 */
class CalculatorTest {
    private static final int EXPECTED_SUM = 7;
    private static final int FIRST_ADDEND = 3;
    private static final int SECOND_ADDEND = 4;
    private static final int EXPECTED_DIFFERENCE = 6;
    private static final int MINUEND = 10;
    private static final int SUBTRAHEND = 4;

    /**
     * Verifies that two integers are added.
     */
    @Test
    void addReturnsSumOfTwoIntegers() {
        assertEquals(EXPECTED_SUM, Calculator.add(FIRST_ADDEND, SECOND_ADDEND));
    }

    /**
     * Verifies that the second integer is subtracted from the first.
     */
    @Test
    void subtractReturnsDifferenceOfTwoIntegers() {
        assertEquals(EXPECTED_DIFFERENCE, Calculator.subtract(MINUEND, SUBTRAHEND));
    }
}
