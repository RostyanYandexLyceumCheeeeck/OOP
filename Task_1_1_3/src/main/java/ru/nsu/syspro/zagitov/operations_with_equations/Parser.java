package ru.nsu.syspro.zagitov.operations_with_equations;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    final static Pattern patternBrackets = Pattern.compile("[()]");
    final static Pattern patternNumber = Pattern.compile("[-+]?\\d+");
    final static Pattern patternOperator = Pattern.compile("[+\\-*/]");
    final static Pattern patternVariable = Pattern.compile("_\\w+|[A-Za-z]\\w*");
    final static Pattern patternNumAndVar = Pattern.compile(
            patternVariable.pattern() + "|" + patternNumber.pattern()
    );

    private static int getPriority(String operation) {
        return switch (operation) {
            case "-", "+" -> 0;
            case "*", "/" -> 1;
            default -> -1;
        };
    }

    public static Expression stringToExpression(String expression) {
        return arrayRpnToExpression(stringExpressionToArrayRPN(expression));
    }

    protected static ArrayList<String> stringExpressionToArrayRPN(String expression) {
        ArrayList<String> arrayExpression = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        expression = expression.replaceAll("\\s+", "");
        Pattern patternExpression  = Pattern.compile(
                "(" + patternBrackets.pattern() + "|" + patternOperator.pattern() +
                        "|" + patternNumAndVar.pattern() + ")"
        );
        Matcher matcher = patternExpression.matcher(expression);

        int controlSum = 0;
        while (matcher.find()) {
            arrayExpression.add(matcher.group());
            controlSum += matcher.group().length();
        }
        if (controlSum != expression.length()) {
            throw new IllegalArgumentException("Expression \"" + expression + "\" is not a valid!");
        }
        recurseStringExprToRPN(arrayExpression, 0, result);

        return result;
    }

    private static int recurseStringExprToRPN(ArrayList<String> arrayExpression,
                                       int start, ArrayList<String> result) {
        Stack<String> stack = new Stack<>();
        int i;
        for (i = start; i < arrayExpression.size(); i++) {
            String expression = arrayExpression.get(i);
            boolean isBracket = Pattern.matches(patternBrackets.pattern(), expression);
            boolean isOperator = Pattern.matches(patternOperator.pattern(), expression);

            if (isBracket) {
                if (Objects.equals(expression, ")")) {
                    break;
                } // else
                i = recurseStringExprToRPN(arrayExpression, i + 1, result);
            } else if (isOperator) {
                while (!stack.empty() && getPriority(stack.peek()) >= getPriority(expression)) {
                    result.add(stack.pop());
                }
                stack.push(expression);
            } else {
                result.add(expression);
            }
        }

        while (!stack.empty()) {
            result.add(stack.pop());
        }
        return i;
    }

    private static Expression stringToNumOrVar(String expression) {
        boolean isNumberOrVariable = Pattern.matches(patternNumAndVar.pattern(), expression);
        if (!isNumberOrVariable) {
            throw new IllegalArgumentException("Expression \"" + expression +
                    "\" is not a Number or Variable!");
        }
        return (Pattern.matches(patternNumber.pattern(), expression) ?
                new Number(Integer.parseInt(expression)) : new Variable(expression));
    }

    private static Expression stringToOperation(
            String expression, Expression left, Expression right)
    {
        return switch (expression) {
            case "+" -> new Add(left, right);
            case "-" -> new Sub(left, right);
            case "*" -> new Mul(left, right);
            case "/" -> new Div(left, right);
            default -> throw new IllegalArgumentException("Expression \"" + expression +
                    "\" is not a valid operation!");
        };
    }

    protected static Expression arrayRpnToExpression(ArrayList<String> arrayRpnExp) {
        Stack<Expression> stack = new Stack<>();

        for (String expression : arrayRpnExp) {
            boolean isOperator = Pattern.matches(patternOperator.pattern(), expression);
            if (!isOperator) {
                stack.push(stringToNumOrVar(expression));
            } else if (!stack.isEmpty()) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                stack.push(stringToOperation(expression, left, right));
            } else {
                throw new IllegalArgumentException("Expression is not a valid!");
            }
        }
        return stack.pop();
    }
}
