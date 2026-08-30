package stack.reversepolishnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 150 — Evaluate Reverse Polish Notation (Medium)
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Pattern: Stack
 * Cue: "evaluate postfix expression — an operator always applies to the two most recently seen operands"
 *
 * Approach: Scan tokens left to right. A number gets pushed. An operator
 * pops the top two operands (the second-popped one came first in the
 * original expression) and pushes the result back. The final value left on
 * the stack is the answer.
 *
 * Time: O(n) — each token is processed once.
 * Space: O(n) for the stack.
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        List<String> operators = initOperators();

        for (String token : tokens) {
            if (operators.contains(token)) {
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(firstOperand + secondOperand);
                        break;
                    case "-":
                        stack.push(firstOperand - secondOperand);
                        break;
                    case "*":
                        stack.push(firstOperand * secondOperand);
                        break;
                    case "/":
                        stack.push(firstOperand / secondOperand);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

    private List<String> initOperators() {
        List<String> operators = new ArrayList<>(4);

        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        return operators;
    }
}
