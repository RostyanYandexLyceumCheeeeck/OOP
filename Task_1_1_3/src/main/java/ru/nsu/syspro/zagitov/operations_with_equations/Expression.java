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
        Pattern pattern = Pattern.compile("(\\w+)\\s*=\\s*(\\d+)");
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

    protected int __eval__(ArrayList<String> names, ArrayList<Integer> values) {
        return 0;
    }
}

