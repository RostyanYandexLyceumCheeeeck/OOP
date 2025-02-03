package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Add.
 */
public class TestAdd {

    @Test
    void testToStringNumberAndNumber() {
        Add add = new Add(new Number(10), new Number(20));
        Assertions.assertEquals("(10+20)", add.toString());
    }

    @Test
    void testToStringNumberAndVariable() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Assertions.assertEquals("(10+zxc)", add.toString());
    }

    @Test
    void testToStringVariableAndVariable() {
        Add add = new Add(new Variable("asd"), new Variable("zxc"));
        Assertions.assertEquals("(asd+zxc)", add.toString());
    }

    @Test
    void testDerivativeNumberAndNumber() {
        Add add = new Add(new Number(10), new Number(20));
        Expression newAdd = add.derivative("");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("z");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zx");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zXc");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zxc");
        Expression expected = new Add(
                new Number(0),
                new Number(1)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));
        Expression newAdd = add.derivative("z");
        Expression expected = new Add(
                new Number(0),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));
        Expression newAdd = add.derivative("x");
        Expression expected = new Add(
                new Number(1),
                new Number(0)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));

        Expression newAdd = add.derivative("zxc");
        Expression expected = new Add(
                new Number(0),
                new Number(1)
        );
        Assertions.assertTrue(expected.equals(newAdd));
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        int addEval = testAdd.protectedEval(namesValues);
        Assertions.assertEquals(30, addEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int addEval = testAdd.protectedEval(namesValues);
                    Assertions.assertEquals(10, addEval);
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
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(20, testAdd.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int addEval = testAdd.protectedEval(namesValues);
                    Assertions.assertEquals(10, addEval);
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
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int addEval = testAdd.protectedEval(namesValues);
                    Assertions.assertEquals(10, addEval);
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
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("asd", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int addEval = testAdd.protectedEval(namesValues);
                    Assertions.assertEquals(10, addEval);
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
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put(valueRight, 13);

        Assertions.assertEquals(23, testAdd.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);
        Add testAdd = new Add(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);

        Assertions.assertEquals(20, testAdd.protectedEval(namesValues));
    }

    @Test
    void testEvalNumberAndVariable0() {
        Expression expression = new Add(new Number(10), new Variable("_zxc"));
        int result = expression.eval("x = 19; _zxc = -10");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalNumberAndVariable1() {
        Expression expression = new Add(new Number(10), new Variable("__"));
        int result = expression.eval("x = 19; __ = 10");
        Assertions.assertEquals(20, result);
    }

    @Test
    void testEvalNumberAndVariable3() {
        Expression expression = new Add(new Number(10), new Variable("_123"));
        int result = expression.eval("x = 19; _123 = +123");
        Assertions.assertEquals(133, result);
    }

    @Test
    void testEvalVariableAndVariable0() {
        Expression expression = new Add(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      0");
        Assertions.assertEquals(19, result);
    }

    @Test
    void testEvalVariableAndVariable1() {
        Expression expression = new Add(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      -0");
        Assertions.assertEquals(19, result);
    }

    @Test
    void testEvalVariableAndVariable2() {
        Expression expression = new Add(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =      0");
        Assertions.assertEquals(-19, result);
    }

    @Test
    void testEvalVariableAndVariable3() {
        Expression expression = new Add(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =    -81");
        Assertions.assertEquals(-100, result);
    }

}
