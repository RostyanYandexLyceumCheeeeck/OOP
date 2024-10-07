package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class is variable.
 */
public class Variable extends Expression {
    String value;

    /**
     * Constructor classes Variable.
     *
     * @param value String name variable.
     */
    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(Objects.equals(variable, value) ? 1 : 0);
    }

    @Override
    protected int protectedEval(ArrayList<String> names, ArrayList<Integer> values) {
        for (int i = 0; i < names.size(); i++) {
            if (Objects.equals(names.get(i), value)) {
                return values.get(i);
            }
        }
        throw new ArithmeticException("Arithmetic Error! \"" + value + "\" not found!");
    }
}
