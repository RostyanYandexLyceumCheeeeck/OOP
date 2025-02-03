package ru.nsu.syspro.zagitov.operationswithequations;

/**
 * Class for entry point to the program.
 */
public class Main {
    /**
     * Start point to the program.
     *
     * @param args arguments of command line.
     */
    public static void main(String[] args) {
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));
        e.print(); // (3+(2*x))

        Expression de = e.derivative("x");
        de.print();
        int result = e.eval("x = 10; y = 13");
        int result1 = e.eval("x = 0; y = 13");
        int result2 = e.eval("x = -0; y = 13");
        int result3 = e.eval("x = -3; y = 13");
        System.out.println(result);  // 23
        System.out.println(result1); // 3
        System.out.println(result2); // 3
        System.out.println(result3); // -3

        String expression = " 12-7/( 3 + ( 2 * x ) )+1111*13/13-98";
        // (((12-(7/(3+(2*x))))+((1111*13)/13))-98)
        System.out.println(Parser.stringToExpression(expression));
    }
}