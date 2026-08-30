package stack.validparentheses;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 20 — Valid Parentheses (Easy)
 * https://leetcode.com/problems/valid-parentheses/
 *
 * Pattern: Stack
 * Cue: "matching brackets — a closer must match the most recently opened bracket"
 *
 * Approach: Map each opening bracket to its matching closer. For each
 * character: if the stack's top is an opener whose match is the current
 * character, pop it (a pair just closed correctly). Otherwise push the
 * current character. The string is valid iff the stack is empty at the end
 * — every opener found its match, in the right order, with nothing left
 * unclosed.
 *
 * Time: O(n) — one pass, each character pushed/popped at most once.
 * Space: O(n) for the stack.
 */
public class ValidParentheses {

    public boolean isValid(String s) {

        Map<Character, Character> openerToCloser = new HashMap<>();
        openerToCloser.put('(', ')');
        openerToCloser.put('{', '}');
        openerToCloser.put('[', ']');

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (!stack.empty() && openerToCloser.containsKey(stack.peek())
                    && currentChar == openerToCloser.get(stack.peek())) {
                stack.pop();
            } else {
                stack.push(currentChar);
            }
        }

        return stack.empty();
    }
}
