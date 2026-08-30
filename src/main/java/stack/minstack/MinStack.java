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
