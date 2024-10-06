package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;

public class Div extends Expression {
    Expression left, right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(variable), right),
                        new Mul(left, right.derivative(variable))),
                new Mul(right, right)
        );
    }

    protected int __eval__(ArrayList<String> names, ArrayList<Integer> values) {
        return left.__eval__(names, values) / right.__eval__(names, values);
    }
}