package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;
import java.util.Objects;

public class Variable extends Expression {
    String value;

    public Variable(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public Expression derivative(String variable) {
        return new Number(Objects.equals(variable, value) ? 1 : 0);
    }

    protected int __eval__(ArrayList<String> names, ArrayList<Integer> values) {
        for (int i = 0; i < names.size(); i++) {
            if (Objects.equals(names.get(i), value)) {
                return values.get(i);
            }
        }
        throw new ArithmeticException("Arithmetic Error! " + value + " not found!");
    }

}
