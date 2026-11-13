package com.example;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {
    /**
     * Rigorous Test.
     */
    @Test
    void testApp() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        PrintStream testOut = new PrintStream(output);

        try {
            System.setOut(testOut);
            App.main(new String[0]);
        } finally {
            System.setOut(originalOut);
            testOut.close();
        }

        assertEquals("Hello World!" + System.lineSeparator(), output.toString());
    }
}
