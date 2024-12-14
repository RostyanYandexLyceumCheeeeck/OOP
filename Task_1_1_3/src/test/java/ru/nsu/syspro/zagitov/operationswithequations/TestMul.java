package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Mul.
 */
public class TestMul {

    @Test
    void testToStringNumberAndNumber() {
        Mul mul = new Mul(new Number(10), new Number(20));
        Assertions.assertEquals("(10*20)", mul.toString());
    }

    @Test
    void testToStringNumberAndVariable() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Assertions.assertEquals("(10*zxc)", mul.toString());
    }

    @Test
    void testToStringVariableAndVariable() {
        Mul mul = new Mul(new Variable("asd"), new Variable("zxc"));
        Assertions.assertEquals("(asd*zxc)", mul.toString());
    }

    @Test
    void testDerivativeNumberAndNumber() {
        Mul mul = new Mul(new Number(10), new Number(20));
        Expression newMul = mul.derivative("");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Number(20)
                ),
                new Mul(
                        new Number(10),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Number(10),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("z");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Number(10),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zx");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Number(10),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zXc");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Number(10),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zxc");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Number(10),
                        new Number(1)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));
        Expression newMul = mul.derivative("z");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Variable("x"),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));
        Expression newMul = mul.derivative("x");
        Expression expected = new Add(
                new Mul(
                        new Number(1),
                        new Variable("zxc")
                ),
                new Mul(
                        new Variable("x"),
                        new Number(0)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));

        Expression newMul = mul.derivative("zxc");
        Expression expected = new Add(
                new Mul(
                        new Number(0),
                        new Variable("zxc")
                ),
                new Mul(
                        new Variable("x"),
                        new Number(1)
                )
        );
        Assertions.assertTrue(expected.equals(newMul));
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        Mul testMul = new Mul(left, right);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        int mulEval = testMul.protectedEval(namesValues);
        Assertions.assertEquals(200, mulEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int mulEval = testMul.protectedEval(namesValues);
                    Assertions.assertEquals(10, mulEval);
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

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(100, testMul.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int mulEval = testMul.protectedEval(namesValues);
                    Assertions.assertEquals(10, mulEval);
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

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int mulEval = testMul.protectedEval(namesValues);
                    Assertions.assertEquals(10, mulEval);
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

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("asd", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int mulEval = testMul.protectedEval(namesValues);
                    Assertions.assertEquals(10, mulEval);
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

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put(valueRight, 13);

        Assertions.assertEquals(130, testMul.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        Mul testMul = new Mul(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);

        Assertions.assertEquals(100, testMul.protectedEval(namesValues));
    }

    @Test
    void testEvalNumberAndNumber() {
        Expression expression = new Mul(new Number(10), new Number(13));
        int result = expression.eval("x = 22; y = 33");
        Assertions.assertEquals(130, result);
    }

    @Test
    void testEvalNumberAndVariable0() {
        Expression expression = new Mul(new Number(10), new Variable("_zxc"));
        int result = expression.eval("x = 19; _zxc = -10");
        Assertions.assertEquals(-100, result);
    }

    @Test
    void testEvalNumberAndVariable1() {
        Expression expression = new Mul(new Number(10), new Variable("__"));
        int result = expression.eval("x = 19; __ = 10");
        Assertions.assertEquals(100, result);
    }

    @Test
    void testEvalNumberAndVariable3() {
        Expression expression = new Mul(new Number(10), new Variable("_123"));
        int result = expression.eval("x = 19; _123 = +123");
        Assertions.assertEquals(1230, result);
    }

    @Test
    void testEvalVariableAndVariable0() {
        Expression expression = new Mul(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      0");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalVariableAndVariable1() {
        Expression expression = new Mul(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = 19; yY =      -0");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalVariableAndVariable2() {
        Expression expression = new Mul(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =      0");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalVariableAndVariable3() {
        Expression expression = new Mul(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -19; yY =    -81");
        Assertions.assertEquals(1539, result);
    }
}
