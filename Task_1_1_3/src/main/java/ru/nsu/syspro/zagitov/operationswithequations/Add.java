package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;

/**
 * Class is summary.
 */
public class Add extends Expression {
    Expression left;
    Expression right;

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
    protected int protectedEval(Map<String, Integer> namesValues) {
        return left.protectedEval(namesValues) + right.protectedEval(namesValues);
    }

    @Override
    public Expression simplify() {
        Add result = new Add(left.simplify(), right.simplify());
        if (result.left instanceof Number && result.right instanceof Number) {
            return new Number(
                    ((Number) result.left).number + ((Number) result.right).number
            );
        }
        if (result.left instanceof Number && ((Number) result.left).number == 0) {
            return result.right;
        }
        if (result.right instanceof Number && ((Number) result.right).number == 0) {
            return result.left;
        }
        return result;
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Add otherAdd) {
            return left.equals(otherAdd.left) && right.equals(otherAdd.right);
        }
        return false;
    }
}
