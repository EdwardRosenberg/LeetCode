# Valid Parentheses

- **LeetCode:** [20 — Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) (Easy)
- **Pattern:** Stack
- **Cue:** "matching brackets — a closer must match the most recently opened bracket"

## Approach

Map each opening bracket to its matching closer. For each character: if the stack's top is an opener whose match is the current character, pop it (a pair just closed correctly). Otherwise push the current character. The string is valid iff the stack is empty at the end — every opener found its match, in the right order, with nothing left unclosed.

- **Time:** O(n) — one pass, each character pushed/popped at most once.
- **Space:** O(n) for the stack.

## Why it works

A stack is exactly the right structure because the *most recently opened, still-unclosed* bracket is always the only one a closer is allowed to match — that's a LIFO relationship. Pushing every unmatched character (opener or wrongly-placed closer alike) and only popping on an exact opener→closer match means: a mismatched pair, a closer with nothing open, or a still-open bracket at the end all leave something stranded on the stack, so "stack empty at the end" is both necessary and sufficient for validity.

## Visualizing it

```
s = "({[}])"   -- looks balanced by bracket type/count, but nesting order is wrong

i=0 '(' -> stack top is empty, no match           -> push   stack: [ ( ]
i=1 '{' -> stack top '(' maps to ')', not '{'      -> push   stack: [ ( { ]
i=2 '[' -> stack top '{' maps to '}', not '['      -> push   stack: [ ( { [ ]
i=3 '}' -> stack top '[' maps to ']', not '}'      -> push   stack: [ ( { [ } ]
i=4 ']' -> stack top '}' isn't a key in the map     -> push   stack: [ ( { [ } ] ]
i=5 ')' -> stack top ']' isn't a key in the map     -> push   stack: [ ( { [ } ] ) ]

end of string, stack still has 6 entries -> invalid
```

The `[` at i=2 never gets to match its `]` at i=4, because the `}` at i=3 is sitting on top of the stack in between — the LIFO order enforces that `[` must close *before* `{` can, and this input tries to close them in the opposite order.

## Watch out

The check is `stack top is an opener whose mapped closer equals the current char` — not just "is the top bracket the same family." A closing bracket that shows up when the top of the stack is itself a *closing* bracket (or the stack is empty) always falls into the `push` branch, which is what correctly flags it as unmatched rather than accidentally comparing it against the wrong kind of top.

## Code

```java
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
```
