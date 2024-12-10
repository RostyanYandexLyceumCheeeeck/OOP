package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

/**
 * Class is division.
 */
public class Div extends Expression {
    Expression left;
    Expression right;

    /**
     * Constructor classes Division.
     *
     * @param left  Expression divisible.
     * @param right Expression divider.
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String variable) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(variable), right),
                        new Mul(left, right.derivative(variable))),
                new Mul(right, right)
        );
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return left.protectedEval(names, values) / right.protectedEval(names, values);
    }

    @Override
    public Expression simplify() {
        Div result = new Div(left.simplify(), right.simplify());
        if (result.left.getClass() == Number.class && result.right.getClass() == Number.class) {
            return new Number(result.left.eval("x = 0") / result.right.eval("x = 0"));
        }
        if (result.left.getClass() == Number.class && result.left.eval("x = 0") == 0) {
            return new Number(0);
        }
        if (result.right.getClass() == Number.class && result.right.eval("x = 0") == 1) {
            return result.left;
        }
        if (result.left.toString().equals(result.right.toString())) {
            return new Number(1);
        }
        return result;
    }
}
