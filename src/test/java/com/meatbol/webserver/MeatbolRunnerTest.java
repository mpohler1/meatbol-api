package com.meatbol.webserver;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MeatbolRunnerTest {

    @Test
    void meatbol_runner_does_not_throw_IOException_when_given_empty_filepath() {
        try {
            MeatbolRunner.runMeatbolInterpreter("");
        } catch (IOException e) {
            fail("MeatbolRunner.runMeatbolInterpreter should not throw an IOException when given an empty filepath.");
        }
    }

    @Test
    void meatbol_runner_outputs_usage_when_given_empty_filepath() throws IOException {
        String expectedOutput = "Invalid usage: input filename required.\n" +
                "Usage: $ ./ [source filemane] <-f>\n" +
                "\tsource filename is name of file containg source code\n" +
                "\t-f option redirects output to file, by default, 'p#Input*' becomes 'p#Out*'\n" +
                "\tdo not use if filename is not in 'p#Input*' form; use command line aurgument\n" +
                "\n" +
                "java.lang.ArrayIndexOutOfBoundsException: 0\n" +
                "\tat meatbol.Meatbol.main(Meatbol.java:42)\n";

        String acualOutput = MeatbolRunner.runMeatbolInterpreter("");
        assertEquals(expectedOutput, acualOutput);
    }

    @Test
    void meatbol_runner_does_not_throw_IOException_when_given_valid_filepath() {
        try {
            String pathToSimpleForLoopCode = this.getClass().getClassLoader().getResource("simpleFor.txt").getPath();
            MeatbolRunner.runMeatbolInterpreter(pathToSimpleForLoopCode);
        } catch (IOException e) {
            fail("MeatbolRunner.runMeatbolInterpreter should not throw an IOException when given a valid filepath.");
        }
    }

    @Test
    void meatbol_runner_returns_interpreter_output_when_given_valid_filepath() throws IOException {
        String pathToSimpleForLoopCode = this.getClass().getClassLoader().getResource("simpleFor.txt").getPath();

        String expectedOutput = "\t 0 \n" +
                "\t 1 \n" +
                "\t 2 \n" +
                "ONLY ONE OF THESE SHOULD PRINT \n" +
                "EOF         EMPTY        \n";

        String actualOutput = MeatbolRunner.runMeatbolInterpreter(pathToSimpleForLoopCode);
        assertEquals(expectedOutput, actualOutput);
    }
}
