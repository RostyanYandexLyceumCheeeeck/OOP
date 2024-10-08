package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;

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
    public void print() {
        System.out.println(this);
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(0);
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        return number;
    }
}