package ru.nsu.syspro.zagitov.operations_with_equations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestParser extends Parser {
    @Test
    void TestStringExpressionToArrayRPN0() {
        String expression = "1 - 5 * 3";  // 1 5 3 * -
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("1", "5", "3", "*", "-"));
        ArrayList<String> actual = stringExpressionToArrayRPN(expression);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void TestStringExpressionToArrayRPN1() {
        String expression = "12 - 22      / 11+(7* (10 - 5 /5))";  // 12 22 11 / - 7 10 5 5 / - * +
        ArrayList<String> expected = new ArrayList<>(Arrays.asList(
                "12", "22", "11", "/", "-", "7", "10", "5", "5", "/", "-", "*", "+")
        );
        ArrayList<String> actual = stringExpressionToArrayRPN(expression);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void TestArrayRpnToExpression0() {
        ArrayList<String> arrayRPN = new ArrayList<>(Arrays.asList("1", "5", "3", "*", "-"));
        Expression expression = arrayRpnToExpression(arrayRPN);
        Assertions.assertEquals("(1-(5*3))", expression.toString());
    }

    @Test
    void TestArrayRpnToExpression1() {
        ArrayList<String> arrayRPN = new ArrayList<>(Arrays.asList(
                "12", "22", "11", "/", "-", "7", "10", "5", "5", "/", "-", "*", "+")
        );
        Expression expression = arrayRpnToExpression(arrayRPN);
        Assertions.assertEquals("((12-(22/11))+(7*(10-(5/5))))", expression.toString());
    }
}
