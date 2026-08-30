package stack.largestrectangle;

import java.util.Stack;

/**
 * LeetCode 84 — Largest Rectangle in Histogram (Hard)
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 *
 * Pattern: Stack
 * Cue: "largest rectangle under a histogram — width is limited by the shortest bar in the span"
 *
 * Approach: Keep two parallel stacks, in strictly increasing height order:
 * heightStack (a bar's height) and startIndices (the leftmost index a run
 * of that height could stretch back to, including any shorter bars it
 * absorbed). When a bar shorter than the stack's top arrives, pop every bar
 * taller than it — each pop's rectangle spans from its recorded start
 * index up to the current index, since nothing between them is shorter.
 * The last popped bar's start index is reused as the new bar's start index,
 * since the incoming (shorter) bar could have formed a rectangle stretching
 * that far back too.
 *
 * Time: O(n) — each index is pushed once and popped at most once.
 * Space: O(n) for the two stacks.
 */
public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {

        int maxArea = 0;
        Stack<Integer> heightStack = new Stack<>();
        Stack<Integer> startIndices = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            if (heightStack.isEmpty() || heightStack.peek() < heights[i]) {
                heightStack.push(heights[i]);
                startIndices.push(i);
            } else {
                int startIndex = startIndices.peek();
                while (!heightStack.isEmpty() && heightStack.peek() > heights[i]) {
                    startIndex = startIndices.pop();
                    int area = heightStack.pop() * (i - startIndex);
                    maxArea = Math.max(maxArea, area);
                }

                if (heightStack.isEmpty() || heightStack.peek() < heights[i]) {
                    heightStack.push(heights[i]);
                    startIndices.push(startIndex);
                }
            }
        }

        while (!heightStack.isEmpty() && !startIndices.isEmpty()) {
            int area = heightStack.pop() * (heights.length - startIndices.pop());
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
