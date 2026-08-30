# Largest Rectangle in Histogram

- **LeetCode:** [84 — Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) (Hard)
- **Pattern:** Stack
- **Cue:** "largest rectangle under a histogram — width is limited by the shortest bar in the span"

## Approach

Keep two parallel stacks, in strictly increasing height order: `heightStack` (a bar's height) and `startIndices` (the leftmost index a run of that height could stretch back to, including any shorter bars it absorbed). When a bar shorter than the stack's top arrives, pop every bar taller than it — each pop's rectangle spans from its recorded start index up to the current index, since nothing between them is shorter. The last popped bar's start index is reused as the new bar's start index, since the incoming (shorter) bar could have formed a rectangle stretching that far back too.

- **Time:** O(n) — each index is pushed once and popped at most once.
- **Space:** O(n) for the two stacks.

## Why it works

Every maximal rectangle in a histogram is bounded above by some bar's height and extends left/right until it hits a shorter bar — so the answer is always "some bar's height × the width of the run of bars at least that tall." Keeping the stacks in strictly increasing height order means a bar only gets popped when a strictly shorter bar arrives, at which point everything about that bar's maximal-width rectangle is finally knowable (nothing shorter has shown up yet, and now something has, closing off its right edge). Carrying `startIndex` forward to the replacement entry is what correctly extends the *next* run back through the bars just popped — those bars were all `>= ` the incoming height, so a rectangle at the incoming height could validly span all the way back to wherever the first of them started.

## Visualizing it

```
heights = [2, 1, 5, 6, 2, 3]
            0  1  2  3  4  5

i=0 h=2: stack empty -> push            heightStack=[2]        startIndices=[0]
i=1 h=1: 1 < top(2) -> pop
           area = 2*(1-0) = 2                                    max=2
         push, carrying start=0        heightStack=[1]        startIndices=[0]
i=2 h=5: 5 > top(1) -> push             heightStack=[1,5]      startIndices=[0,2]
i=3 h=6: 6 > top(5) -> push             heightStack=[1,5,6]    startIndices=[0,2,3]
i=4 h=2: 2 < top(6) -> pop
           area = 6*(4-3) = 6                                    max=6
         2 < new top(5) -> pop
           area = 5*(4-2) = 10                                   max=10
         push, carrying start=2        heightStack=[1,2]      startIndices=[0,2]
i=5 h=3: 3 > top(2) -> push             heightStack=[1,2,3]    startIndices=[0,2,5]

end of array — drain what's left, using the array length as the right edge:
  pop h=3 start=5 -> area=3*(6-5)=3     max stays 10
  pop h=2 start=2 -> area=2*(6-2)=8     max stays 10
  pop h=1 start=0 -> area=1*(6-0)=6     max stays 10

largest rectangle = 10   (the bars [5, 6] at height >= 5, width 2)
```

The `start=2` carried forward at `i=4` is the key move: the height-2 run starting at `i=4` is recorded as if it started back at index 2, because both the popped 5 and 6 were tall enough to also support a height-2 rectangle spanning that whole stretch.

## Watch out

The `if (heightStack.isEmpty() || heightStack.peek() < heights[i])` check appears *twice* — once to decide whether to enter the pop-loop at all, and again right after the pop-loop to decide whether to push the current bar. Missing the second check (e.g. always pushing after popping) would push a duplicate entry when the popped-down stack's new top already equals the current height, breaking the strictly-increasing invariant.

## Code

```java
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
```
