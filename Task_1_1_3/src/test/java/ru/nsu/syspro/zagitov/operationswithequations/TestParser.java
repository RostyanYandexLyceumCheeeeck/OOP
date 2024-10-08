package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Class testing Parser.
 */
public class TestParser extends Parser {
    @Test
    void testStringExpressionToArrayRpn0() {
        String expression = "1 - 5 * 3";  // 1 5 3 * -
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "5", "3", "*", "-"));
        ArrayList<String> actual = stringExpressionToArrayRpn(expression);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testStringExpressionToArrayRpn1() {
        String expression = "12 - 22      / 11+(7* (10 - 5 /5))";  // 12 22 11 / - 7 10 5 5 / - * +
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "12", "22", "11", "/", "-", "7", "10", "5", "5", "/", "-", "*", "+")
        );
        ArrayList<String> actual = stringExpressionToArrayRpn(expression);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testArrayRpnToExpression0() {
        ArrayList<String> arrayRpn = new ArrayList<>(Arrays.asList("1", "5", "3", "*", "-"));
        Expression expression = arrayRpnToExpression(arrayRpn);
        Assertions.assertEquals("(1-(5*3))", expression.toString());
    }

    @Test
    void testArrayRpnToExpression1() {
        ArrayList<String> arrayRpn = new ArrayList<>(Arrays.asList(
                "12", "22", "11", "/", "-", "7", "10", "5", "5", "/", "-", "*", "+")
        );
        Expression expression = arrayRpnToExpression(arrayRpn);
        Assertions.assertEquals("((12-(22/11))+(7*(10-(5/5))))", expression.toString());
    }
}
