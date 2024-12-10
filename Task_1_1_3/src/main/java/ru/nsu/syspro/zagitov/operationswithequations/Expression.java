package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class expression.
 */
public abstract class Expression {
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
        Pattern pattern = Pattern.compile(
                "(" + Parser.patternVariable.pattern() + ")\\s*=\\s*("
                        + Parser.patternNumber.pattern() + ")"
        );
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (String token : tokens) {
            Matcher matcher = pattern.matcher(token);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Invalid expression: " + token
                        + "! Example: x = 10");
            }
            names.add(matcher.group(1));
            values.add(Integer.valueOf(matcher.group(2)));
        }
        return protectedEval(names, values);
    }

    /**
     * Recursive eval.
     *
     * @param names  is strings names variables.
     * @param values is integers value variables.
     * @return calculating expressions.
     */
    protected abstract int protectedEval(ArrayList<String> names, ArrayList<Integer> values);

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
     * print string exception to console.
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
}


