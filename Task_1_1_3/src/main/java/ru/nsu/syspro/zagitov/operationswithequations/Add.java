package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

/**
 * Class is summary.
 */
public class Add extends Expression {
    Expression left, right;

    /**
     * Constructor classes Add.
     *
     * @param left  Expression first summand.
     * @param right Expression second summand.
     */
    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Add(left.derivative(variable), right.derivative(variable));
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return left.protectedEval(names, values) + right.protectedEval(names, values);
    }
}
