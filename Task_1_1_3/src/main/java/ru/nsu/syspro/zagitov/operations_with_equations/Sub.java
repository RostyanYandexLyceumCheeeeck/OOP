package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;

public class Sub extends Expression {
    Expression left, right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }

    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    protected int __eval__(ArrayList<String> names, ArrayList<Integer> values) {
        return left.__eval__(names, values) - right.__eval__(names, values);
    }
}