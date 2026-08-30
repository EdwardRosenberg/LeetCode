# Min Stack

- **LeetCode:** [155 — Min Stack](https://leetcode.com/problems/min-stack/) (Medium)
- **Pattern:** Stack
- **Cue:** "stack that also supports O(1) getMin alongside push/pop"

## Approach

Maintain a second stack in lockstep with the main one, where each entry is the minimum seen so far *including* the value just pushed to the main stack at that position. Popping both stacks together keeps the min stack's top always equal to the current minimum of whatever remains on the main stack.

- **Time:** O(1) for push, pop, top, and getMin.
- **Space:** O(n) — the min stack duplicates one entry per push.

## Why it works

Each `minStack` entry records "the minimum of everything in `valueStack` from the bottom up through this position" — not just the global minimum at push time. That's what makes popping safe: when the current minimum is popped off `valueStack`, popping `minStack` in the same step throws away exactly that stale minimum and exposes the minimum of the *remaining* elements underneath, which was already computed and stored back when that lower element was pushed. No recomputation is ever needed.

## Visualizing it

```
push(5)          valueStack: [5]        minStack: [5]
push(1)          valueStack: [5, 1]     minStack: [5, 1]      <- 1 is a new min
push(3)          valueStack: [5, 1, 3]  minStack: [5, 1, 1]   <- 3 isn't a new min, so minStack repeats 1
pop()            valueStack: [5, 1]     minStack: [5, 1]      <- both tops removed together

getMin() now reads minStack.peek() = 1, correctly reflecting {5, 1}
```

## Code

```java
package stack.minstack;

import java.util.Stack;

/**
 * LeetCode 155 — Min Stack (Medium)
 * https://leetcode.com/problems/min-stack/
 *
 * Pattern: Stack
 * Cue: "stack that also supports O(1) getMin alongside push/pop"
 *
 * Approach: Maintain a second stack in lockstep with the main one, where
 * each entry is the minimum seen so far *including* the value just pushed
 * to the main stack at that position. Popping both stacks together keeps
 * the min stack's top always equal to the current minimum of whatever
 * remains on the main stack.
 *
 * Time: O(1) for push, pop, top, and getMin.
 * Space: O(n) — the min stack duplicates one entry per push.
 */
public class MinStack {

    private final Stack<Integer> valueStack;
    private final Stack<Integer> minStack;

    public MinStack() {
        valueStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        valueStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            minStack.push(Math.min(val, minStack.peek()));
        }
    }

    public void pop() {
        valueStack.pop();
        minStack.pop();
    }

    public int top() {
        return valueStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
```
