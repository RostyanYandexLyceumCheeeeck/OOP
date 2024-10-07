package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

/**
 * Class is difference.
 */
public class Sub extends Expression {
    Expression left, right;

    /**
     * Constructor classes Sub.
     *
     * @param left  Expression minuend.
     * @param right Expression subtrahend.
     */
    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Sub(left.derivative(variable), right.derivative(variable));
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return left.protectedEval(names, values) - right.protectedEval(names, values);
    }
}
