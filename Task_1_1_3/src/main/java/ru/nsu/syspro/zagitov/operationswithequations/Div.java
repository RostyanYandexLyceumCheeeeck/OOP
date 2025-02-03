package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;

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
    protected int protectedEval(Map<String, Integer> namesValues) {
        return left.protectedEval(namesValues) / right.protectedEval(namesValues);
    }

    @Override
    public Expression simplify() {
        Div result = new Div(left.simplify(), right.simplify());
        if (result.left instanceof Number && result.right instanceof Number) {
            return new Number(
                    ((Number) result.left).number / ((Number) result.right).number
            );
        }
        if (result.left instanceof Number && ((Number) result.left).number == 0) {
            return new Number(0);
        }
        if (result.right instanceof Number && ((Number) result.right).number == 1) {
            return result.left;
        }
        if (result.left.equals(result.right)) {
            return new Number(1);
        }
        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Div otherDiv) {
            return left.equals(otherDiv.left) && right.equals(otherDiv.right);
        }
        return false;
    }
}
