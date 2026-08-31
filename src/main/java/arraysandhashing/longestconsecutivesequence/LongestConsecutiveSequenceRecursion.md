# Longest Consecutive Sequence

- **LeetCode:** [128 — Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "longest run of consecutive values in an unsorted array, without sorting"

Variants: [LongestConsecutiveSequenceHashSet](LongestConsecutiveSequenceHashSet.md) (iterative, walks every integer from min..max instead of recursing — O(n + range), not true O(n) when values are sparse), [LongestConsecutiveSequenceSorted](LongestConsecutiveSequenceSorted.md) (sorts the array first, O(n log n) but O(1) extra space)

## Approach

Put every number in a HashSet, then only start counting a streak from numbers that are the *start* of one (no `num-1` in the set). From each streak start, recursively count forward while `num+1` exists.

- **Time:** O(n) — every number is visited as a streak-start candidate once, and because only streak starts kick off a count, the recursive counting still only walks each number once overall.
- **Space:** O(n) for the HashSet (plus O(n) recursion depth in the worst case of one long streak).

**Why it works:** every streak has exactly one member with no `num-1` present — its start — so `!numberSet.contains(num - 1)` identifies each streak's start uniquely, with no streak producing zero starts or two. From that start, `countConsecutiveFrom` walks `num+1, num+2, ...` until the chain breaks, which by construction measures the streak's *entire* length, not a partial one. Since every streak is discovered via its start exactly once and measured completely each time, the max over all discovered streaks is guaranteed to be the true longest one — nothing is double-counted and nothing is missed.

**Watch out:** Skipping the `!numberSet.contains(num - 1)` check is the classic mistake — without it, every member of a streak (not just its start) kicks off a full recount, turning O(n) into O(n²) on a single long streak.

**Why not sliding window?** This problem's phrasing ("longest run satisfying a condition") sounds like a sliding-window cue, but it isn't one — window pointers walk contiguous *index* positions, and here "consecutive" means consecutive *values*, which can sit at arbitrary, unrelated positions in the array. A window can't see `1, 2, 3, 4` as adjacent if they're scattered across the input. The only way to make it window-shaped is to sort first (see the Sorted variant below) — but sorting is O(n log n), so if the target is O(n), that route is already ruled out before you write a line of window code. General rule: **if the only way to make sliding window fit a problem is to sort first, and the expected solution is O(n), sliding window (or any of its derivatives) isn't the tool** — reach for a HashSet/HashMap instead.

## Visualizing it

```
nums = [100, 4, 200, 1, 3, 2]
numberSet = {100, 4, 200, 1, 3, 2}

Only numbers with no `num-1` in the set are streak starts:

  100 → no 99 in set → streak start
    4 → 3 IS in set → not a start, skip
  200 → no 199 in set → streak start
    1 → no 0 in set → streak start
    3 → 2 IS in set → not a start, skip
    2 → 1 IS in set → not a start, skip

Count forward from each streak start:

  100 → no 101 → streak length 1
  200 → no 201 → streak length 1
    1 → 2 → 3 → 4 → no 5 → streak length 4   ← longest

longestStreak = 4
```

## Code

```java
package arraysandhashing.longestconsecutivesequence;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128 — Longest Consecutive Sequence (Medium)
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Pattern: Arrays & Hashing
 * Cue: "longest run of consecutive values in an unsorted array, without sorting"
 *
 * Approach: Put every number in a HashSet, then only start counting a streak
 * from numbers that are the *start* of one (no num-1 in the set). From each
 * streak start, recursively count forward while num+1 exists.
 *
 * Time: O(n) — every number is visited as a streak-start candidate once, and
 * because only streak starts kick off a count, the recursive counting still
 * only walks each number once overall.
 * Space: O(n) for the HashSet (plus O(n) recursion depth in the worst case
 * of one long streak).
 *
 * Variants: LongestConsecutiveSequenceHashSet.java (iterative, walks every
 * integer from min..max instead of recursing — O(n + range), not true O(n)
 * when values are sparse), LongestConsecutiveSequenceSorted.java (sorts the
 * array first, O(n log n) but O(1) extra space)
 */
public class LongestConsecutiveSequenceRecursion implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1) return nums.length;

        Set<Integer> numberSet = new HashSet<>();
        for (int num : nums) {
            numberSet.add(num);
        }

        int longestStreak = 1;

        for (int num : numberSet) {
            // Only start counting from the beginning of a streak, so each
            // streak gets counted exactly once instead of once per member.
            if (numberSet.contains(num + 1) && !numberSet.contains(num - 1)) {
                int streakLength = countConsecutiveFrom(numberSet, num);
                if (streakLength > longestStreak) longestStreak = streakLength;
            }
        }

        return longestStreak;
    }

    private static int countConsecutiveFrom(Set<Integer> numberSet, int num) {
        int streakLength = 1;
        if (numberSet.contains(num + 1)) {
            streakLength += countConsecutiveFrom(numberSet, num + 1);
        }
        return streakLength;
    }
}
```
