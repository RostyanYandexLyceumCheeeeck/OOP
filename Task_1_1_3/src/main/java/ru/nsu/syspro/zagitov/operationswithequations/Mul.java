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

    @Override
    public Expression simplify() {
        Mul result = new Mul(left.simplify(), right.simplify());
        if (result.left.getClass() == Number.class && result.right.getClass() == Number.class) {
            return new Number(result.left.eval("x = 0") * result.right.eval("x = 0"));
        }
        if ((result.left.getClass() == Number.class && result.left.eval("x = 0") == 0) ||
                (result.right.getClass() == Number.class && result.right.eval("x = 0") == 0)) {
            return new Number(0);
        }
        if (result.left.getClass() == Number.class && result.left.eval("x = 0") == 1) {
            return result.right;
        }
        if (result.right.getClass() == Number.class && result.right.eval("x = 0") == 1) {
            return result.left;
        }
        return result;
    }
}
