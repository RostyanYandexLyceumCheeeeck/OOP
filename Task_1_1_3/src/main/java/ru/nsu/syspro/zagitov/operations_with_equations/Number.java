package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;

public class Number extends Expression {
    int number;

    public Number(int number) {
        this.number = number;
    }

    public String toString() {
        return String.valueOf(number);
    }

    public Expression derivative(String variable) {
        return new Number(0);
    }

    protected int __eval__(ArrayList<String> names, ArrayList<Integer> values) {
        return number;
    }

}