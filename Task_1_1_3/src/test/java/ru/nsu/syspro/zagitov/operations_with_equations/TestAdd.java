package ru.nsu.syspro.zagitov.operations_with_equations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestAdd {
    static class TestProtectedEvalAdd extends Add {

        public TestProtectedEvalAdd(Expression left, Expression right) {
            super(left, right);
        }

        public int getProtectedEval(ArrayList<String> names, ArrayList<Integer> values) {
            return protectedEval(names, values);
        }
    }

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
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeNumberAndVariable0() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("");
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeNumberAndVariable1() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("z");
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeNumberAndVariable2() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zx");
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeNumberAndVariable3() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zXc");
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeNumberAndVariable4() {
        Add add = new Add(new Number(10), new Variable("zxc"));
        Expression newAdd = add.derivative("zxc");
        Assertions.assertEquals("(0+1)", newAdd.toString());
    }

    @Test
    void testDerivativeVariableAndVariable() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));
        Expression newAdd = add.derivative("z");
        Assertions.assertEquals("(0+0)", newAdd.toString());
    }

    @Test
    void testDerivativeVariableAndVariableLeft() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));
        Expression newAdd = add.derivative("x");
        Assertions.assertEquals("(1+0)", newAdd.toString());
    }

    @Test
    void testDerivativeVariableAndVariableRight() {
        Add add = new Add(new Variable("x"), new Variable("zxc"));

        Expression newAdd = add.derivative("zxc");
        Assertions.assertEquals("(0+1)", newAdd.toString());
    }

    @Test
    void testProtectedEvalNumberAndNumber() {
        Number left = new Number(10);
        Number right = new Number(20);
        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        int addEval = testAdd.getProtectedEval(names, values);
        Assertions.assertEquals(30, addEval);
    }

    @Test
    void testProtectedEvalNumberAndVariableNotFound() {
        String value = "zxc";
        Number left = new Number(10);
        Variable right = new Variable(value);

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zXc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int addEval = testAdd.getProtectedEval(names, values);
            Assertions.assertEquals(10, addEval);
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

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(20, testAdd.getProtectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableNotFoundLeft() {
        String valueLeft = "asd";
        String valueRight = "zxc";
        Variable left = new Variable(valueLeft);
        Variable right = new Variable(valueRight);

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int addEval = testAdd.getProtectedEval(names, values);
            Assertions.assertEquals(10, addEval);
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

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int addEval = testAdd.getProtectedEval(names, values);
            Assertions.assertEquals(10, addEval);
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

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        ArithmeticException exception = Assertions.assertThrows(ArithmeticException.class, () -> {
            int addEval = testAdd.getProtectedEval(names, values);
            Assertions.assertEquals(10, addEval);
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

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("asd", "zxc"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(23, testAdd.getProtectedEval(names, values));
    }

    @Test
    void testProtectedEvalVariableAndVariableExcept1() {
        String value = "zxc";
        Variable left = new Variable(value);
        Variable right = new Variable(value);

        TestProtectedEvalAdd testAdd = new TestProtectedEvalAdd(left, right);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("zxc", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(20, testAdd.getProtectedEval(names, values));
    }

    @Test
    void testEvalNumberAndNumber() {
        Expression expression = new Add(new Number(10), new Number(13));
        int result = expression.eval("x = 22; y = 33");
        Assertions.assertEquals(23, result);
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
