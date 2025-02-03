package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;

/**
 * Class is difference.
 */
public class Sub extends Expression {
    Expression left;
    Expression right;

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
    protected int protectedEval(Map<String, Integer> namesValues) {
        return left.protectedEval(namesValues) - right.protectedEval(namesValues);
    }

    @Override
    public Expression simplify() {
        Sub result = new Sub(left.simplify(), right.simplify());
        if (result.left instanceof Number && result.right instanceof Number) {
            return new Number(
                    ((Number) result.left).number - ((Number) result.right).number
            );
        }
        if (result.right instanceof Number && ((Number) result.right).number == 0) {
            return result.left;
        }
        if (result.left.equals(result.right)) {
            return new Number(0);
        }
        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Sub otherSub) {
            return left.equals(otherSub.left) && right.equals(otherSub.right);
        }
        return false;
    }
}
