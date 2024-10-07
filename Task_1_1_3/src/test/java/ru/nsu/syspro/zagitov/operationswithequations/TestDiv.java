package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Div.
 */
public class TestDiv {
    /**
     * Class testing method protectedEval in Div.
     */
    static class TestProtectedEvalDiv extends Div {
        /**
         * Constructor.
         *
         * @param left  left instance Expression.
         * @param right right instance Expression.
         */
        public TestProtectedEvalDiv(Expression left, Expression right) {
            super(left, right);
        }
    }

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
        Assertions.assertEquals("(((0*20)-(10*0))/(20*20))", newDiv.toString());
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("");
        Assertions.assertEquals("(((0*zxc)-(10*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("z");
        Assertions.assertEquals("(((0*zxc)-(10*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zx");
        Assertions.assertEquals("(((0*zxc)-(10*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zXc");
        Assertions.assertEquals("(((0*zxc)-(10*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Div div = new Div(new Number(10), new Variable("zxc"));
        Expression newDiv = div.derivative("zxc");
        Assertions.assertEquals("(((0*zxc)-(10*1))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));
        Expression newDiv = div.derivative("z");
        Assertions.assertEquals("(((0*zxc)-(x*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));
        Expression newDiv = div.derivative("x");
        Assertions.assertEquals("(((1*zxc)-(x*0))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Div div = new Div(new Variable("x"), new Variable("zxc"));

        Expression newDiv = div.derivative("zxc");
        Assertions.assertEquals("(((0*zxc)-(x*1))/(zxc*zxc))", newDiv.toString());
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        int divEval = testDiv.protectedEval(names, values);
        Assertions.assertEquals(0, divEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int divEval = testDiv.protectedEval(names, values);
            Assertions.assertEquals(10, divEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalNumberAndVariableExcept() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(1, testDiv.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int divEval = testDiv.protectedEval(names, values);
            Assertions.assertEquals(10, divEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + valueLeft + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }


    @Test
    void testProtectedEvalVariableAndVariableNotFoundRight() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int divEval = testDiv.protectedEval(names, values);
            Assertions.assertEquals(10, divEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + valueRight + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFound() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int divEval = testDiv.protectedEval(names, values);
            Assertions.assertEquals(10, divEval);
        });

        String expectedMessage = "Arithmetic Error! \"" + value + "\" not found!";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept0() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "zxc"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(0, testDiv.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        TestProtectedEvalDiv testDiv = new TestProtectedEvalDiv(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(1, testDiv.protectedEval(names, values));
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
