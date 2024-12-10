package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

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
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return left.protectedEval(names, values) - right.protectedEval(names, values);
    }

    @Override
    public Expression simplify() {
        Sub result = new Sub(left.simplify(), right.simplify());
        if (result.left.getClass() == Number.class && result.right.getClass() == Number.class) {
            return new Number(result.left.eval("x = 0") - result.right.eval("x = 0"));
        }
        if (result.right.getClass() == Number.class && result.right.eval("x = 0") == 0) {
            return result.left;
        }
        if (result.left.toString().equals(result.right.toString())) {
            return new Number(0);
        }
        return result;
    }
}
