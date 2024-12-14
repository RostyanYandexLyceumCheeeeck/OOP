package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Number.
 */
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
        Assertions.assertEquals(0, numberDerivative.eval());
    }

    @Test
    void testProtectedEval() {
        class TestProtectedEvalNumber extends Number {

            public TestProtectedEvalNumber(int number) {
                super(number);
            }

            public int getProtectedEval(Map<String, Integer> namesValues) {
                return protectedEval(namesValues);
            }
        }

        int value = 17;
        TestProtectedEvalNumber testNumber = new TestProtectedEvalNumber(value);

        Map<String, Integer> namesValues = new HashMap<>();
        namesValues.put("x", 10);
        namesValues.put("y", 13);

        Assertions.assertEquals(value, testNumber.getProtectedEval(namesValues));
    }
}
