package ru.nsu.syspro.zagitov.operations_with_equations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestNumber {

    @Test
    void testToString() {
        int value = 17;
        Number number = new Number(value);
        Assertions.assertEquals("17", number.toString());
    }

    @Test
    void testDerivative() {
        int value = 17;
        Number number = new Number(value);
        Expression numberDerivative = number.derivative("");
        Assertions.assertEquals("0", numberDerivative.toString());
    }

    @Test
    void testProtectedEval() {
        class TestProtectedEvalNumber extends Number {

            public TestProtectedEvalNumber(int number) {
                super(number);
            }

            public int getProtectedEval(ArrayList<String> names, ArrayList<Integer> values) {
                return protectedEval(names, values);
            }
        }

        int value = 17;
        TestProtectedEvalNumber testNumber = new TestProtectedEvalNumber(value);
        ArrayList<String> names = new ArrayList<>(Arrays.asList("x", "y"));
        ArrayList<Integer> values = new ArrayList<>(Arrays.asList(10, 13));

        Assertions.assertEquals(value, testNumber.getProtectedEval(names, values));
    }
}
