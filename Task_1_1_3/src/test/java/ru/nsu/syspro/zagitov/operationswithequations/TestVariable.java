package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Variable.
 */
public class TestVariable {
    /**
     * Class testing method protectedEval in Variable.
     */
    static class TestProtectedEvalVariable extends Variable {
        /**
         * Constructor.
         *
         * @param value is string name variable.
         */
        public TestProtectedEvalVariable(String value) {
            super(value);
        }
    }

    @Test
    void testToString() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Assertions.assertEquals(value, variable.toString());
    }

    @Test
    void testDerivativeNotFound0() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("");
        Assertions.assertEquals("0", variableDerivative.toString());
    }

    @Test
    void testDerivativeNotFound1() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zx");
        Assertions.assertEquals("0", variableDerivative.toString());
    }

    @Test
    void testDerivativeNotFound2() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zXc");
        Assertions.assertEquals("0", variableDerivative.toString());
    }

    @Test
    void testDerivativeExcept() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zxc");
        Assertions.assertEquals("1", variableDerivative.toString());
    }

    @Test
    void testProtectedEvalNotFound0() {
        String value = "zxc";
        TestProtectedEvalVariable testVariable = new TestProtectedEvalVariable(value);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int variableEval = testVariable.protectedEval(names, values);
            Assertions.assertEquals(10, variableEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNotFound1() {
        String value = "zxc";
        TestProtectedEvalVariable testVariable = new TestProtectedEvalVariable(value);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zx", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int variableEval = testVariable.protectedEval(names, values);
            Assertions.assertEquals(10, variableEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNotFound2() {
        String value = "zxc";
        TestProtectedEvalVariable testVariable = new TestProtectedEvalVariable(value);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int variableEval = testVariable.protectedEval(names, values);
            Assertions.assertEquals(10, variableEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalExcept() {
        String value = "zxc";
        TestProtectedEvalVariable testVariable = new TestProtectedEvalVariable(value);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(10, testVariable.protectedEval(names, values));
    }
}
