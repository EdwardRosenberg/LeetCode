package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {

    public boolean isValid(String s) {

        Map<Character, Character> dict = new HashMap<>();
        dict.put('(', ')');
        dict.put('{', '}');
        dict.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (!stack.empty() && dict.containsKey(stack.peek()) && s.charAt(i) == dict.get(stack.peek())) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        return stack.empty();
    }

}
