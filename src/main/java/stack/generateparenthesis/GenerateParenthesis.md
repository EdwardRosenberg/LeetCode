# Generate Parentheses

- **LeetCode:** [22 — Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) (Medium)
- **Pattern:** Stack
- **Cue:** "generate every well-formed combination — backtrack with open/close counts as guardrails"

## Approach

Backtrack while tracking how many `(` and `)` have been placed so far. Only push `(` while `openCount < n`, and only push `)` while `openCount > closedCount` (never let closers outnumber openers so far). A combination is complete exactly when both counts reach `n`. The stack holds the current in-progress combination; pushing before a recursive call and popping after is what lets the same stack be reused across every branch of the search.

- **Time:** O(4<sup>n</sup> / √n) — bounded by the *n*th Catalan number, the count of valid combinations.
- **Space:** O(n) for the recursion depth and stack, excluding the output.

## Why it works

The two guardrails are exactly the two rules that define a well-formed bracket sequence: never place more openers than `n` allows, and never let a closer appear without a matching still-open opener ahead of it (`closedCount < openCount`). Every path the recursion explores respects both rules at every step, so anything the recursion reaches the `openCount == closedCount == n` base case with is guaranteed valid — and because the two branches (`push '('`, `push ')'`) are the *only* two legal next moves at any point, the recursion also can't skip over any valid combination.

## Visualizing it

```
n = 2, current = [] (the stack)

push ( -> "("
  push ( -> "(("
    push ) -> "(()"          (closedCount 1 < openCount 2, legal)
      push ) -> "(())"
        openCount=2, closedCount=2 -> complete: "(())"
  push ) -> "()"              (back up: pop "(", now openCount=1,closedCount=0)
    push ( -> "()("
      push ) -> "()()"
        openCount=2, closedCount=2 -> complete: "()()"

result: ["(())", "()()"]
```

Each `pop()` after a recursive call is what makes the stack usable again for the sibling branch — without it, `"(())"` and `"()()"` would end up interleaved in the same stack instead of being explored as two independent paths.

## Code

```java
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
```
