package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;

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
    public Expression derivative(String variable) {
        return new Add(
                new Mul(left.derivative(variable), right),
                new Mul(left, right.derivative(variable))
        );
    }

    @Override
    protected int protectedEval(Map<String, Integer> namesValues) {
        return left.protectedEval(namesValues) * right.protectedEval(namesValues);
    }

    @Override
    public Expression simplify() {
        Mul result = new Mul(left.simplify(), right.simplify());
        if (result.left instanceof Number && result.right instanceof Number) {
            return new Number(
                    ((Number) result.left).number * ((Number) result.right).number
            );
        }
        if ((result.left instanceof Number && ((Number) result.left).number == 0)
                || (result.right instanceof Number && ((Number) result.right).number == 0)) {
            return new Number(0);
        }
        if (result.left instanceof Number && ((Number) result.left).number == 1) {
            return result.right;
        }
        if (result.right instanceof Number && ((Number) result.right).number == 1) {
            return result.left;
        }
        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Mul otherMul) {
            return left.equals(otherMul.left) && right.equals(otherMul.right);
        }
        return false;
    }
}
