package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Mul.
 */
public class TestMul {
    /**
     * Class testing method protectedEval in Mul.
     */
    static class TestProtectedEvalMul extends Mul {
        /**
         * Constructor.
         *
         * @param left  left instance Expression.
         * @param right right instance Expression.
         */
        public TestProtectedEvalMul(Expression left, Expression right) {
            super(left, right);
        }
    }

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
        Assertions.assertEquals("((0*20)+(10*0))", newMul.toString());
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("");
        Assertions.assertEquals("((0*zxc)+(10*0))", newMul.toString());
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("z");
        Assertions.assertEquals("((0*zxc)+(10*0))", newMul.toString());
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zx");
        Assertions.assertEquals("((0*zxc)+(10*0))", newMul.toString());
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zXc");
        Assertions.assertEquals("((0*zxc)+(10*0))", newMul.toString());
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Mul mul = new Mul(new Number(10), new Variable("zxc"));
        Expression newMul = mul.derivative("zxc");
        Assertions.assertEquals("((0*zxc)+(10*1))", newMul.toString());
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));
        Expression newMul = mul.derivative("z");
        Assertions.assertEquals("((0*zxc)+(x*0))", newMul.toString());
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));
        Expression newMul = mul.derivative("x");
        Assertions.assertEquals("((1*zxc)+(x*0))", newMul.toString());
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Mul mul = new Mul(new Variable("x"), new Variable("zxc"));

        Expression newMul = mul.derivative("zxc");
        Assertions.assertEquals("((0*zxc)+(x*1))", newMul.toString());
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        int mulEval = testMul.protectedEval(names, values);
        Assertions.assertEquals(200, mulEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int mulEval = testMul.protectedEval(names, values);
            Assertions.assertEquals(10, mulEval);
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

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(100, testMul.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int mulEval = testMul.protectedEval(names, values);
            Assertions.assertEquals(10, mulEval);
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

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int mulEval = testMul.protectedEval(names, values);
            Assertions.assertEquals(10, mulEval);
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

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int mulEval = testMul.protectedEval(names, values);
            Assertions.assertEquals(10, mulEval);
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

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "zxc"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(130, testMul.protectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        TestProtectedEvalMul testMul = new TestProtectedEvalMul(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(100, testMul.protectedEval(names, values));
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
