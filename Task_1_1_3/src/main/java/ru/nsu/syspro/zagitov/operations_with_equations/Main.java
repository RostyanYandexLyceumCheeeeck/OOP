package ru.nsu.syspro.zagitov.operations_with_equations;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        System.out.println(e); // (3+(2*x))

        Expression de = e.derivative("x");
        System.out.println(de.toString());

        int result = e.eval("x = 10; y = 13");
        int result1 = e.eval("x = 0; y = 13");
        int result2 = e.eval("x = -0; y = 13");
        int result3 = e.eval("x = -3; y = 13");
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);

        String expression = " 12-7/( 3 + ( 2 * x ) )+1111*13/13-98";
        System.out.println(Parser.stringToExpression(expression));

    }
}