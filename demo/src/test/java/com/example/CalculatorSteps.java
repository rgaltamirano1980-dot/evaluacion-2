package com.example;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Step definitions for the calculator feature.
 */
public class CalculatorSteps {
    private int firstOperand;
    private int secondOperand;
    private int result;

    /**
     * Stores the operands for a scenario.
     * @param first The first integer.
     * @param second The second integer.
     */
    @Dado("que ingreso los operandos {int} y {int}")
    public void recordOperands(int first, int second) {
        firstOperand = first;
        secondOperand = second;
    }

    /**
     * Adds the stored operands.
     */
    @Cuando("solicito sumarlos")
    public void addOperands() {
        result = Calculator.add(firstOperand, secondOperand);
    }

    /**
     * Subtracts the second stored operand from the first.
     */
    @Cuando("solicito restarlos")
    public void subtractOperands() {
        result = Calculator.subtract(firstOperand, secondOperand);
    }

    /**
     * Checks the result of the operation.
     * @param expectedResult The expected result.
     */
    @Entonces("el resultado debe ser {int}")
    public void verifyResult(int expectedResult) {
        assertEquals(expectedResult, result);
    }
}
