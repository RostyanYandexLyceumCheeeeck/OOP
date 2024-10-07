package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Mul.
 */
public class TestSub {
    /**
     * Class testing method protectedEval in Mul.
     */
    static class TestProtectedEvalSub extends Sub {
        /**
         * Constructor.
         *
         * @param left  left instance Expression.
         * @param right right instance Expression.
         */
        public TestProtectedEvalSub(Expression left, Expression right) {
            super(left, right);
        }
    }
    
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
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("");
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("z");
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zx");
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zXc");
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Sub sub = new Sub(new Number(10), new Variable("zxc"));
        Expression newSub = sub.derivative("zxc");
        Assertions.assertEquals("(0-1)", newSub.toString());
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));
        Expression newSub = sub.derivative("z");
        Assertions.assertEquals("(0-0)", newSub.toString());
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));
        Expression newSub = sub.derivative("x");
        Assertions.assertEquals("(1-0)", newSub.toString());
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Sub sub = new Sub(new Variable("x"), new Variable("zxc"));

        Expression newSub = sub.derivative("zxc");
        Assertions.assertEquals("(0-1)", newSub.toString());
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        int subEval = testSub.protectedEval(names, values);
        Assertions.assertEquals(-10, subEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int subEval = testSub.protectedEval(names, values);
            Assertions.assertEquals(10, subEval);
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

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(0, testSub.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int subEval = testSub.protectedEval(names, values);
            Assertions.assertEquals(10, subEval);
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

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int subEval = testSub.protectedEval(names, values);
            Assertions.assertEquals(10, subEval);
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

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int subEval = testSub.protectedEval(names, values);
            Assertions.assertEquals(10, subEval);
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

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "zxc"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(-3, testSub.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        TestProtectedEvalSub testSub = new TestProtectedEvalSub(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(0, testSub.protectedEval(names, values));
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
