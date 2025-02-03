package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Variable.
 */
public class TestVariable {
    
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
        Assertions.assertEquals(0, variableDerivative.eval());
    }

    @Test
    void testDerivativeNotFound1() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zx");
        Assertions.assertEquals(0, variableDerivative.eval());
    }

    @Test
    void testDerivativeNotFound2() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zXc");
        Assertions.assertEquals(0, variableDerivative.eval());
    }

    @Test
    void testDerivativeExcept() {
        String value = "zxc";
        Variable variable = new Variable(value);
        Expression variableDerivative = variable.derivative("zxc");
        Assertions.assertEquals(1, variableDerivative.eval());
    }

    @Test
    void testProtectedEvalNotFound0() {
        String value = "zxc";
        Variable testVariable = new Variable(value);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int variableEval = testVariable.protectedEval(namesValues);
                    Assertions.assertEquals(10, variableEval);
                }
        );

        String expectedMessage = "Variable \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNotFound1() {
        String value = "zxc";
        Variable testVariable = new Variable(value);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zx", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int variableEval = testVariable.protectedEval(namesValues);
                    Assertions.assertEquals(10, variableEval);
                }
        );

        String expectedMessage = "Variable \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNotFound2() {
        String value = "zxc";
        Variable testVariable = new Variable(value);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int variableEval = testVariable.protectedEval(namesValues);
                    Assertions.assertEquals(10, variableEval);
                }
        );

        String expectedMessage = "Variable \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalExcept() {
        String value = "zxc";
        Variable testVariable = new Variable(value);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(10, testVariable.protectedEval(namesValues));
    }
}
