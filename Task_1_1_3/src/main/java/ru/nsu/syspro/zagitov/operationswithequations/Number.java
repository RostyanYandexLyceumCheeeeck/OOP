package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;

/**
 * Class is number.
 */
public class Number extends Expression {
    int number;

    /**
     * Constructor classes Number.
     *
     * @param number int value.
     */
    public Number(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    protected int protectedEval(Map<String, Integer> namesValues) {
        return number;
    }

    @Override
    public Expression simplify() {
        return new Number(number);
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Number otherNumber) {
            return number == otherNumber.number;
        }
        return false;
    }
}