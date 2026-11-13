package com.example;

/**
 * Basic integer arithmetic operations.
 */
public final class Calculator {
    private Calculator() {
    }

    /**
     * Adds two integers.
     * @param left The first addend.
     * @param right The second addend.
     * @return The sum of the two integers.
     */
    public static int add(int left, int right) {
        return left + right;
    }

    /**
     * Subtracts the second integer from the first.
     * @param left The integer to subtract from.
     * @param right The integer to subtract.
     * @return The difference between the two integers.
     */
    public static int subtract(int left, int right) {
        return left - right;
    }
}
