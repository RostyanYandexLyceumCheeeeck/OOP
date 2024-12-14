package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Sub.
 */
public class TestSub {

    @Test
    void testToStringNumberAndNumber() {
        Sub sub = new Sub(new Number(10), new Number(20));
        Assertions.assertEquals("(10-20)", sub.toString());
    }

    @Test
    void testToStringNumberAndVariable() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Assertions.assertEquals("(10-zxc)", sub.toString());
    }

    @Test
    void testToStringVariableAndVariable() {
        Sub sub = new Sub(new Variable("asd"), new Variable("zxc"));
        Assertions.assertEquals("(asd-zxc)", sub.toString());
    }

    @Test
    void testDerivativeNumberAndNumber() {
        Sub sub = new Sub(new Number(10), new Number(20));
        Expression newSub = sub.derivative("");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("z");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zx");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zXc");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zxc");
        Expression expected = new Sub(
                new Number(0),
                new Number(1)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));
        Expression newSub = sub.derivative("z");
        Expression expected = new Sub(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));
        Expression newSub = sub.derivative("x");
        Expression expected = new Sub(
                new Number(1),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));
        Expression newSub = sub.derivative("zxc");
        Expression expected = new Sub(
                new Number(0),
                new Number(1)
        );
        Assertions.assertTrue(expected.equals(newSub));
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        int subEval = testSub.protectedEval(namesValues);
        Assertions.assertEquals(-10, subEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int subEval = testSub.protectedEval(namesValues);
                    Assertions.assertEquals(10, subEval);
                }
        );

        String expectedMessage = "Variable \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNumberAndVariableExcept() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(0, testSub.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int subEval = testSub.protectedEval(namesValues);
                    Assertions.assertEquals(10, subEval);
                }
        );

        String expectedMessage = "Variable \"" + valueLeft + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void testProtectedEvalVariableAndVariableNotFoundRight() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int subEval = testSub.protectedEval(namesValues);
                    Assertions.assertEquals(10, subEval);
                }
        );

        String expectedMessage = "Variable \"" + valueRight + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFound() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("asd", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int subEval = testSub.protectedEval(namesValues);
                    Assertions.assertEquals(10, subEval);
                }
        );

        String expectedMessage = "Variable \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept0() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put(valueRight, 13);

        Assertions.assertEquals(-3, testSub.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        Sub testSub = new Sub(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);

        Assertions.assertEquals(0, testSub.protectedEval(namesValues));
    }

    @Test
    void testEvalNumberAndNumber() {
        Expression expression = new Sub(new Number(10), new Number(13));
        int result = expression.eval("x = 22; y = 33");
        Assertions.assertEquals(-3, result);
    }

    @Test
    void testEvalNumberAndVariable0() {
        Expression expression = new Sub(new Number(10), new Variable("_zxc"));
        int result = expression.eval("x = 19; _zxc = -10");
        Assertions.assertEquals(20, result);
    }

    @Test
    void testEvalNumberAndVariable1() {
        Expression expression = new Sub(new Number(10), new Variable("__"));
        int result = expression.eval("x = 19; __ = 10");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalNumberAndVariable3() {
        Expression expression = new Sub(new Number(10), new Variable("_123"));
        int result = expression.eval("x = 19; _123 = +123");
        Assertions.assertEquals(-113, result);
    }

    @Test
    void testEvalVariableAndVariable0() {
        Expression expression = new Sub(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      0");
        Assertions.assertEquals(19, result);
    }

    @Test
    void testEvalVariableAndVariable1() {
        Expression expression = new Sub(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      -0");
        Assertions.assertEquals(19, result);
    }

    @Test
    void testEvalVariableAndVariable2() {
        Expression expression = new Sub(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =      0");
        Assertions.assertEquals(-19, result);
    }

    @Test
    void testEvalVariableAndVariable3() {
        Expression expression = new Sub(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =    -81");
        Assertions.assertEquals(62, result);
    }
}
