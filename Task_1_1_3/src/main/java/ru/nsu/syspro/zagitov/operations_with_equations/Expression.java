package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Expression {
    protected Expression derivative(String variable) {
        return null;
    }

    public int eval(String variables) {
        String[] tokens = variables.split(";");
        Pattern pattern = Pattern.compile(
                "(" + Parser.patternVariable.pattern() + ")\\s*=\\s*(" +
                        Parser.patternNumber.pattern() + ")"
        );  // x = 10
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> values = new ArrayList<>();

        for (String token : tokens) {
            Matcher matcher = pattern.matcher(token);
            if (!matcher.find()) {
                throw new RuntimeException("Invalid expression: " + token);
            }
            names.add(matcher.group(1));
            values.add(Integer.valueOf(matcher.group(2)));
        }
        return __eval__(names, values);
    }

    public Expression convertString(String expression) {
        return Parser.stringToExpression(expression);
    }

    protected abstract int __eval__(ArrayList<String> names, ArrayList<Integer> values);
}

