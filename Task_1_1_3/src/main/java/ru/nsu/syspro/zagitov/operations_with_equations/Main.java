package ru.nsu.syspro.zagitov.operations_with_equations;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x"))); // (3+(2*x))
        System.out.println(e.toString());

        Expression de = e.derivative("x");
        System.out.println(de.toString());

        int result = e.eval("x = 10; y = 13");
        System.out.println(result);
    }
}