package stack.generateparenthesis;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 22 — Generate Parentheses (Medium)
 * https://leetcode.com/problems/generate-parentheses/
 *
 * Pattern: Stack
 * Cue: "generate every well-formed combination — backtrack with open/close counts as guardrails"
 *
 * Approach: Backtrack while tracking how many '(' and ')' have been placed
 * so far. Only push '(' while openCount < n, and only push ')' while
 * openCount > closedCount (never let closers outnumber openers so far).
 * A combination is complete exactly when both counts reach n. The stack
 * holds the current in-progress combination; pushing before a recursive
 * call and popping after is what lets the same stack be reused across every
 * branch of the search.
 *
 * Time: O(4^n / sqrt(n)) — bounded by the nth Catalan number, the count of
 * valid combinations.
 * Space: O(n) for the recursion depth and stack, excluding the output.
 */
public class GenerateParenthesis {

    private final List<String> combinations = new ArrayList<>();
    private final Stack<String> current = new Stack<>();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return combinations;
    }

    private void backtrack(int openCount, int closedCount, int n) {
        if (openCount == closedCount && closedCount == n) {
            combinations.add(String.join("", current));
        }

        if (openCount < n) {
            current.push("(");
            backtrack(openCount + 1, closedCount, n);
            current.pop();
        }

        if (openCount > closedCount) {
            current.push(")");
            backtrack(openCount, closedCount + 1, n);
            current.pop();
        }
    }
}
