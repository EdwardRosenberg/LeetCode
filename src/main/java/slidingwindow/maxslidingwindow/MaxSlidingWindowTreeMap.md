# Sliding Window Maximum (TreeMap Variant)

- **LeetCode:** [239 — Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum/) (Hard)
- **Pattern:** Sliding Window
- **Cue:** "max/min of every fixed-size window as it slides across an array"

See [MaxSlidingWindowDeque.md](MaxSlidingWindowDeque.md) for the canonical, genuinely O(n) version.

## Approach

Keep the current window's values in a TreeMap keyed by value (value → list of indices currently holding that value), so the max is always whatever key sorts last. Slide by removing the outgoing index from its value's list (dropping the key entirely once its list is empty) and adding the incoming value/index.

- **Time:** O(n log k) — each insert/remove touches a TreeMap of at most k entries, and `lastKey()` also costs O(log k).
- **Space:** O(k) for the map, plus O(n) for the output array.

**Why it works:** the map always holds exactly the window's current contents — nothing more, nothing less — because every slide removes exactly the one outgoing index and adds exactly the one incoming index. A `TreeMap` keeps its keys sorted, so `lastKey()` is always the greatest value currently in the window by construction. Storing a *list* of indices per value (rather than a single index, or just a count) is what makes it safe to remove one occurrence of a duplicated value without losing track of the others still in the window.

**Watch out:** this reasons about the window in terms of *values*, not positions — unlike the deque version, nothing here explicitly checks whether an index has "expired." Removal is driven entirely by knowing which value is leaving (`nums[left]`) each step, not by comparing indices against the window boundary.

## Visualizing it

```
nums = [1, 3, -1, -3, 5, 3, 6, 7], windowSize = 3

Initial window [0..2] = [1, 3, -1]:
  countsByValue = {-1:[2], 1:[0], 3:[1]}
  max = lastKey() = 3

Slide: drop the outgoing value's index, add the incoming value's index:

  drop 1 (idx0), add -3 (idx3)  -> {-3:[3], -1:[2], 3:[1]}
  window [1..3]=[3,-1,-3]  max = 3

  drop 3 (idx1), add 5 (idx4)   -> {-3:[3], -1:[2], 5:[4]}
  window [2..4]=[-1,-3,5]  max = 5

  drop -1 (idx2), add 3 (idx5)  -> {-3:[3], 3:[5], 5:[4]}
  window [3..5]=[-3,5,3]  max = 5

  drop -3 (idx3), add 6 (idx6)  -> {3:[5], 5:[4], 6:[6]}
  window [4..6]=[5,3,6]  max = 6

  drop 5 (idx4), add 7 (idx7)   -> {3:[5], 6:[6], 7:[7]}
  window [5..7]=[3,6,7]  max = 7

result: [3, 3, 5, 5, 6, 7]
```

## Code

```java
package slidingwindow.maxslidingwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * LeetCode 239 — Sliding Window Maximum (Hard)
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Pattern: Sliding Window
 * Cue: "max/min of every fixed-size window as it slides across an array"
 *
 * Approach: Keep the current window's values in a TreeMap keyed by value
 * (value -> list of indices currently holding that value), so the max is
 * always whatever key sorts last. Slide by removing the outgoing index
 * from its value's list (dropping the key entirely once its list is
 * empty) and adding the incoming value/index.
 *
 * Time: O(n log k) — each insert/remove touches a TreeMap of at most k
 * entries, and lastKey() also costs O(log k).
 * Space: O(k) for the map, plus O(n) for the output array.
 *
 * See MaxSlidingWindowDeque.java for the canonical, genuinely O(n) version.
 */
public class MaxSlidingWindowTreeMap implements MaxSlidingWindowSolver {

    @Override
    public int[] maxSlidingWindow(int[] nums, int windowSize) {
        if (nums.length == 1) return nums;

        int[] windowMaxes = new int[nums.length - windowSize + 1];

        int left = 0;
        int right = windowSize - 1;

        SortedMap<Integer, List<Integer>> countsByValue = new TreeMap<>();

        for (int i = left; i <= right; i++) {
            countsByValue.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }

        while (right < nums.length) {
            windowMaxes[left] = countsByValue.lastKey();

            List<Integer> indicesAtOutgoingValue = countsByValue.get(nums[left]);
            if (indicesAtOutgoingValue.size() > 1) {
                indicesAtOutgoingValue.remove(0);
            } else {
                countsByValue.remove(nums[left]);
            }

            left++;
            right++;

            if (right < nums.length) {
                countsByValue.computeIfAbsent(nums[right], v -> new ArrayList<>()).add(right);
            }
        }

        return windowMaxes;
    }
}
```
