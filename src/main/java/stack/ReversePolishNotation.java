package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();
        List<String> operators = new ArrayList<>(4);

        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");

        for (String token : tokens) {
            if (operators.contains(token)) {
                int val2 = stack.pop();
                int val1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(val1 + val2);
                        break;
                    case "-":
                        stack.push(val1 - val2);
                        break;
                    case "*":
                        stack.push(val1 * val2);
                        break;
                    case "/":
                        stack.push(val1 / val2);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

}
