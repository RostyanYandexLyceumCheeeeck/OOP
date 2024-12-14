package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class expression.
 */
public abstract class Expression {
    private static final Pattern patternEval = Pattern.compile(
            "(" + Parser.patternVariable.pattern() + ")\\s*=\\s*("
                    + Parser.patternNumber.pattern() + ")"
    );

    /**
     * Symbolic differentiation of expressions by a given variable.
     *
     * @param variable differentiable variable.
     * @return new Expression is the result of differentiation.
     */
    public abstract Expression derivative(String variable);

    /**
     * Denoting variables and calculating expressions.
     *
     * @param variables variables and values in the format: (x = 10; y = -13; ...).
     * @return calculating expressions.
     */
    public int eval(String variables) {
        String[] tokens = variables.split(";");
        Map<String, Integer> namesValues = new HashMap<>();

        for (String token : tokens) {
            if (token == null || token.isEmpty()) {
                continue;
            }
            Matcher matcher = patternEval.matcher(token);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Invalid expression: " + token
                        + "! Example: x = 10");
            }
            namesValues.put(matcher.group(1), Integer.valueOf(matcher.group(2)));
        }
        return protectedEval(namesValues);
    }

    public int eval() {
        return protectedEval(new HashMap<>());
    }

    /**
     * Recursive eval.
     *
     * @return calculating expressions.
     */
    protected abstract int protectedEval(Map<String, Integer> namesValues);

    /**
     * Convert string expression to instance Expression.
     *
     * @param expression string representation of an expression. Example: 3 - 6/2.
     * @return instance Expression.
     */
    static public Expression convertStringToExpression(String expression) {
        return Parser.stringToExpression(expression);
    }

    /**
     * print string expression to console.
     */
    public void print() {
        System.out.println(this);
    }

    /**
     * A function to simplify expressions.
     *
     * @return new Expression is the result of simplification.
     */
    abstract public Expression simplify();

    abstract public boolean equals(Expression other);
}
