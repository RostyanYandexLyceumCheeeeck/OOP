package ru.nsu.syspro.zagitov.operationswithequations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestExpression {

    @Test
    void testConvertStringToExpression0() {
        Add testAdd = new Add(
                new Mul(
                        new Number(3), new Number(-7)
                ),
                new Number(21)
        );
        String expected = testAdd.toString();  // ((3*-7)+21)
        Expression result = Expression.convertStringToExpression(expected);
        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    void testConvertStringToExpression1() {
        Add testAdd = new Add(
                new Mul(
                        new Number(3),
                        new Number(-7)
                ),
                new Div(
                        new Number(1000),
                        new Number(2))
        );
        String expected = testAdd.toString();  // ((3*-7)+(1000/21))
        Expression result = Expression.convertStringToExpression(expected);
        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    void testConvertStringToExpression2() {
        Div testDiv = new Div(
                new Number(21),
                new Div(
                        new Number(42),
                        new Number(2)
                )
        );
        String expected = testDiv.toString();  // (21/(42/2))
        Expression result = Expression.convertStringToExpression(expected);
        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    void testConvertStringToExpression3() {
        Div testDiv = new Div(
                new Mul(
                        new Number(3),
                        new Variable("zxc")
                ),
                new Sub(
                        new Number(1000),
                        new Number(7))
        );
        String expected = testDiv.toString();  // ((3*zxc)/(1000-7))
        Expression result = Expression.convertStringToExpression(expected);
        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    public void testConvertStringToExpressionWithoutBrackets() {
        String input = "7+3*2-8/2*3";  // 1
        Expression inputExpression = Expression.convertStringToExpression(input);

        Sub exceptExpression = new Sub(
                new Add(
                        new Number(7),
                        new Mul(
                                new Number(3),
                                new Number(2)
                        )
                ),
                new Mul(
                        new Div(
                                new Number(8),
                                new Number(2)
                        ),
                        new Number(3)
                )
        );
        Assertions.assertEquals(1, inputExpression.eval("x=10"));
        Assertions.assertEquals(inputExpression.toString(), exceptExpression.toString());
    }

    @Test
    public void testSimplify0() {
        String input = "7/7 + 3*4";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("13", simplifyExpression.toString());
    }

    @Test
    public void testSimplify1() {
        String input = "(7+3)*(4 - 2)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("20", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyAddZero() {
        String input = "(x+0)+(0+0)+(0+x)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("(x+x)", simplifyExpression.toString());
    }

    @Test
    public void testSimplifySubZero() {
        String input = "(x-0)-(0-0)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("x", simplifyExpression.toString());
    }

    @Test
    public void testSimplifySubEqualsExpressions() {
        String input = "(x-y)-(x-y)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("0", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyNumberMulNumber() {
        String input = "(7 + 1) * (0+0+4)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("32", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyMulZero() {
        String input = "(x*0)+(0*x)+(y*(0*0)+z*x)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("(z*x)", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyMulOne() {
        String input = "x*1 + 1*y";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("(x+y)", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyNumberDivNumber() {
        String input = "(8 / 1) / (0+0+4)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("2", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyZeroDivExpression() {
        String input = "x*0 / 1*y";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("0", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyZeroDivNumber() {
        String input = "x*0 / 13";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("0", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyExpressionDivOne() {
        String input = "(x*x / 1) / (0+0+1)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("(x*x)", simplifyExpression.toString());
    }

    @Test
    public void testSimplifyDivEqualsExpressions() {
        String input = "(x+(y*0)) / ((0*y)+x)";
        Expression inputExpression = Expression.convertStringToExpression(input);
        Expression simplifyExpression = inputExpression.simplify();
        Assertions.assertEquals("1", simplifyExpression.toString());
    }
}
