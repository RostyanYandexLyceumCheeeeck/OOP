package ru.nsu.syspro.zagitov.operationswithequations;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class is parser strings.
 */
public class Parser {
    static final Pattern patternBrackets = Pattern.compile("[()]");
    static final Pattern patternNumber = Pattern.compile("[-+]?\\d+");
    static final Pattern patternOperator = Pattern.compile("[+\\-*/]");
    static final Pattern patternVariable = Pattern.compile("_\\w+|[A-Za-z]\\w*");
    static final Pattern patternNumAndVar = Pattern.compile(
            patternVariable.pattern() + "|" + patternNumber.pattern()
    );

    /**
     * get priority is operation.
     *
     * @param operation string operation (+/-*).
     * @return int priority.
     */
    private static int getPriority(String operation) {
        switch (operation) {
            case "+":
                return 0;
            case "-":
                return 0;
            case "*":
                return 1;
            case "/":
                return 1;
            default:
                return -1;
        }
    }

    /**
     * convert string to instance Expression.
     *
     * @param expression string representation of an expression. Example: 3 - 6/2.
     * @return instance Expression.
     */
    public static Expression stringToExpression(String expression) {
        return arrayRpnToExpression(stringExpressionToArrayRpn(expression));
    }

    /**
     * convert string to array RPN(Reverse Polish Notation).
     *
     * @param expression string representation of an expression. Example: 3 - 6/2.
     * @return array RPN. Example: 3 - 6/2 ⇨⇨ ["3", "6", "2", "/", "-"].
     */
    protected static ArrayList<String> stringExpressionToArrayRpn(String expression) {
        expression = expression.replaceAll("\\s+", "");
        Pattern patternExpression  = Pattern.compile(
                "(" + patternBrackets.pattern() + "|" + patternOperator.pattern()
                        + "|" + patternNumAndVar.pattern() + ")"
        );
        Matcher matcher = patternExpression.matcher(expression);

        int controlSum = 0;
        ArrayList<String> arrayExpression = new ArrayList<>();
        while (matcher.find()) {
            arrayExpression.add(matcher.group());
            controlSum += matcher.group().length();
        }
        if (controlSum != expression.length()) {
            throw new IllegalArgumentException("Expression \"" + expression + "\" is not a valid!");
        }

        ArrayList<String> result = new ArrayList<>();
        recurseStringExprToRpn(arrayExpression, 0, result);
        return result;
    }

    /**
     * recursive convert string to array RPN.
     *
     * @param arrayExpression expression is array.
     *                        Example: (3 - 6/2) ⇨⇨ ["(", "3", "-", "6", "/", "2", ")"].
     * @param start           index start in the arrayExpression.
     * @param result          array RPN.
     * @return index ending in the arrayExpression.
     */
    private static int recurseStringExprToRpn(ArrayList<String> arrayExpression,
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
                i = recurseStringExprToRpn(arrayExpression, i + 1, result);
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

    /**
     * convert string value to instance Number or Variable.
     *
     * @param value string name variable or string integer.
     * @return instance Number or Variable.
     */
    private static Expression stringToNumOrVar(String value) {
        boolean isNumberOrVariable = Pattern.matches(patternNumAndVar.pattern(), value);
        if (!isNumberOrVariable) {
            throw new IllegalArgumentException("Value \"" + value
                    + "\" is not a Number or Variable!");
        }
        return (Pattern.matches(patternNumber.pattern(), value)
                ? new Number(Integer.parseInt(value)) : new Variable(value));
    }

    /**
     * convert string operation and two Expression to instance Add or Sub or Mul or Div.
     *
     * @param expression string operation.
     * @param left left instance Expression.
     * @param right right instance Expression.
     * @return instance Add or Sub or Mul or Div.
     */
    private static Expression stringToOperation(
            String expression, Expression left, Expression right) {
        switch (expression) {
            case "+":
                return new Add(left, right);
            case "-":
                return new Sub(left, right);
            case "*":
                return new Mul(left, right);
            case "/":
                return new Div(left, right);
            default:
                throw new IllegalArgumentException("Expression \"" + expression
                        + "\" is not a valid operation!");
        }
    }

    /**
     * convert array RPN to instance Expression.
     *
     * @param arrayRpnExp array RPN. Example: 3 - 6/2 ⇨⇨ ["3", "6", "2", "/", "-"].
     * @return instance Expression.
     */
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
