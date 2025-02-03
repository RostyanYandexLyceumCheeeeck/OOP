package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Div.
 */
public class TestDiv {

    @Test
    void testToStringNumberAndNumber() {
        Div div = new Div(new Number(10), new Number(20));
        Assertions.assertEquals("(10/20)", div.toString());
    }

    @Test
    void testToStringNumberAndVariable() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Assertions.assertEquals("(10/zxc)", div.toString());
    }

    @Test
    void testToStringVariableAndVariable() {
        Div div = new Div(new Variable("asd"), new Variable("zxc"));
        Assertions.assertEquals("(asd/zxc)", div.toString());
    }

    @Test
    void testDerivativeNumberAndNumber() {
        Div div = new Div(new Number(10), new Number(20));
        Expression newDiv = div.derivative("");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Number(20)
                        ),
                        new Mul(
                                new Number(10),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Number(20),
                        new Number(20)
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Number(10),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("z");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Number(10),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zx");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Number(10),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zXc");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Number(10),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zxc");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Number(10),
                                new Number(1)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));
        Expression newDiv = div.derivative("z");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Variable("x"),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));
        Expression newDiv = div.derivative("x");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(1),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Variable("x"),
                                new Number(0)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));
        Expression newDiv = div.derivative("zxc");
        Expression expected = new Div(
                new Sub(
                        new Mul(
                                new Number(0),
                                new Variable("zxc")
                        ),
                        new Mul(
                                new Variable("x"),
                                new Number(1)
                        )
                ),
                new Mul(
                        new Variable("zxc"),
                        new Variable("zxc")
                )
        );
        Assertions.assertTrue(expected.equals(newDiv));
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        int divEval = testDiv.protectedEval(namesValues);
        Assertions.assertEquals(0, divEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("zXc", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int divEval = testDiv.protectedEval(namesValues);
                    Assertions.assertEquals(10, divEval);
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

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(1, testDiv.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int divEval = testDiv.protectedEval(namesValues);
                    Assertions.assertEquals(10, divEval);

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

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int divEval = testDiv.protectedEval(namesValues);
                    Assertions.assertEquals(10, divEval);
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

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("asd", 10);
        namesValues.put("y", 13);

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> {
                    int divEval = testDiv.protectedEval(namesValues);
                    Assertions.assertEquals(10, divEval);
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

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(valueLeft, 10);
        namesValues.put(valueRight, 13);

        Assertions.assertEquals(0, testDiv.protectedEval(namesValues));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        Div testDiv = new Div(left, right);
        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put(value, 10);

        Assertions.assertEquals(1, testDiv.protectedEval(namesValues));
    }

    @Test
    void testEvalNumberAndNumber() {
        Expression expression = new Div(new Number(10), new Number(13));
        int result = expression.eval("x = 22; y = 33");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalNumberAndVariable0() {
        Expression expression = new Div(new Number(10), new Variable("_zxc"));
        int result = expression.eval("x = 19; _zxc = -10");
        Assertions.assertEquals(-1, result);
    }

    @Test
    void testEvalNumberAndVariable1() {
        Expression expression = new Div(new Number(10), new Variable("__"));
        int result = expression.eval("x = 19; __ = 10");
        Assertions.assertEquals(1, result);
    }

    @Test
    void testEvalNumberAndVariable3() {
        Expression expression = new Div(new Number(10), new Variable("_123"));
        int result = expression.eval("x = 19; _123 = +123");
        Assertions.assertEquals(0, result);
    }

    @Test
    void testEvalVariableAndVariable3() {
        Expression expression = new Div(new Variable("x"), new Variable("yY"));
        int result = expression.eval("x = -162; yY =    81");
        Assertions.assertEquals(-2, result);
    }
}
