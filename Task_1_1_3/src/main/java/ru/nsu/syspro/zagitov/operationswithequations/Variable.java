package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.Map;
import java.util.Objects;

/**
 * Class is variable.
 */
public class Variable extends Expression {
    String name;

    /**
     * Constructor classes Variable.
     *
     * @param name String name variable.
     */
    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression derivative(String variable) {
        return new Number(Objects.equals(variable, name) ? 1 : 0);
    }

    @Override
    protected int protectedEval(Map<String, Integer> namesValues) {
        if (!namesValues.containsKey(name)) {
            throw new IllegalArgumentException("Variable \"" + name + "\" not found!");
        }
        return namesValues.get(name);
    }

    @Override
    public Expression simplify() {
        return new Variable(name);
    }

    @Override
    public boolean equals(Expression other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Variable otherVariable) {
            return Objects.equals(name, otherVariable.name);
        }
        return false;
    }
}
