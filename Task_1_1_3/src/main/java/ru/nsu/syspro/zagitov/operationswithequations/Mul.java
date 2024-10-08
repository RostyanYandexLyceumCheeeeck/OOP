package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

/**
 * Class is multiplication.
 */
public class Mul extends Expression {
    Expression left;
    Expression right;

    /**
     * Constructor classes Multiplication.
     *
     * @param left  Expression first multiplier.
     * @param right Expression second multiplier.
     */
    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(
                new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))
        );
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return left.protectedEval(names, values) * right.protectedEval(names, values);
    }
}
