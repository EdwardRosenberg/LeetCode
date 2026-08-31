# Sliding Window Maximum

- **LeetCode:** [239 — Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) (Hard)
- **Pattern:** Sliding Window
- **Cue:** "max/min of every fixed-size window as it slides across an array"

Variants: [MaxSlidingWindowTreeMap](MaxSlidingWindowTreeMap.md) (tracks window contents in a TreeMap keyed by value — O(n log k), simpler to reason about but asymptotically worse)

## Approach

Keep a deque of indices whose values are in decreasing order, front to back. The front index is always the current window's max. Before adding a new index, pop off any smaller values from the back — they can never be the max while a bigger, more-recent value is still in the window — and pop the front if it's fallen outside the window.

- **Time:** O(n) — each index is pushed onto the deque exactly once and popped at most once, regardless of window size.
- **Space:** O(k) for the deque, plus O(n) for the output array.

**Why it works:** the deque maintains the invariant "every index still in the deque could still be the max of some future window" — an index gets evicted from the back the instant a larger value shows up after it, because from that point on it can *never* be the max of any window that also contains the larger value (and every future window that still contains this index also still contains the larger one, since the larger one is more recent). That's what guarantees the values stay in decreasing order front-to-back, which in turn guarantees the front is always the max of the current window. Popping the front when it falls outside the window handles the one case decreasing-order alone doesn't: the max expiring by falling off the window's left edge.

**Watch out:** it's tempting to think the deque holds *values* — it holds *indices*. The values are only read back via `nums[index]`; this is what makes the "has the max expired?" check (`isWindowMoved`) possible at all, since you need to know *where* the max was, not just what it was.

## Visualizing it

```
nums = [1, 3, -1, -3, 5, 3, 6, 7], windowSize = 3

i=0: push 0        deque=[0]         values=[1]
i=1: 3 > nums[0]=1  -> pop back        deque=[]
     push 1        deque=[1]         values=[3]
i=2: push 2        deque=[1,2]       values=[3,-1]
     window [0..2] max = nums[1] = 3

i=3: push 3        deque=[1,2,3]     values=[3,-1,-3]
     window [1..3] max = nums[1] = 3

i=4: front index 1 fell outside window [2..4] -> pop front   deque=[2,3]
     5 > nums[3]=-3 -> pop back   deque=[2]
     5 > nums[2]=-1 -> pop back   deque=[]
     push 4        deque=[4]         values=[5]
     window [2..4] max = nums[4] = 5

...continues the same way for the rest of the array...

result: [3, 3, 5, 5, 6, 7]
```

Notice index 1 (value 3) survives two windows as the max, then gets evicted from the *front* once the window slides past it — while index 3 (value -3) never becomes anyone's max at all, and index 4 (value 5) immediately evicts everything smaller from the *back* the moment it arrives.

## Code

```java
package slidingwindow.maxslidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 239 — Sliding Window Maximum (Hard)
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Pattern: Sliding Window
 * Cue: "max/min of every fixed-size window as it slides across an array"
 *
 * Approach: Keep a deque of indices whose values are in decreasing order,
 * front to back. The front index is always the current window's max.
 * Before adding a new index, pop off any smaller values from the back —
 * they can never be the max while a bigger, more-recent value is still in
 * the window — and pop the front if it's fallen outside the window.
 *
 * Time: O(n) — each index is pushed onto the deque exactly once and
 * popped at most once, regardless of window size.
 * Space: O(k) for the deque, plus O(n) for the output array.
 *
 * Variants: MaxSlidingWindowTreeMap.java (tracks window contents in a
 * TreeMap keyed by value — O(n log k), simpler to reason about but
 * asymptotically worse)
 */
public class MaxSlidingWindowDeque implements MaxSlidingWindowSolver {

    @Override
    public int[] maxSlidingWindow(int[] nums, int windowSize) {
        int[] windowMaxes = new int[countWindowPositions(nums, windowSize)];
        int resultIndex = 0;

        // Indices of candidate maxes, values decreasing from front to back.
        Deque<Integer> maxCandidateIndices = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!maxCandidateIndices.isEmpty() && isWindowMoved(windowSize, maxCandidateIndices, i)) {
                // The current max has fallen outside the window — drop it.
                maxCandidateIndices.pollFirst();
            }

            // Anything smaller than the incoming value can never become the
            // max while this larger, more-recent value is still in play.
            while (!maxCandidateIndices.isEmpty() && nums[i] > nums[maxCandidateIndices.peekLast()]) {
                maxCandidateIndices.pollLast();
            }

            maxCandidateIndices.offer(i);

            if (isWindowInitialized(windowSize, i)) {
                windowMaxes[resultIndex++] = nums[maxCandidateIndices.peekFirst()];
            }
        }

        return windowMaxes;
    }

    private int countWindowPositions(int[] nums, int windowSize) {
        return nums.length - windowSize + 1;
    }

    private boolean isWindowMoved(int windowSize, Deque<Integer> maxCandidateIndices, int i) {
        return maxCandidateIndices.peekFirst() < i - windowSize + 1;
    }

    private boolean isWindowInitialized(int windowSize, int i) {
        return i >= windowSize - 1;
    }
}
```
