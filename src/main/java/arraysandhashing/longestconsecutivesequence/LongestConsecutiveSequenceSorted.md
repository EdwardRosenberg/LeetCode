# Longest Consecutive Sequence (Sorted Variant)

- **LeetCode:** [128 — Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "longest run of consecutive values in an unsorted array, without sorting"

See [LongestConsecutiveSequenceRecursion.md](LongestConsecutiveSequenceRecursion.md) for the canonical, genuinely O(n) version.

## Approach

Sort the array, then do a single linear pass tracking a running streak — extend it whenever adjacent elements differ by exactly 1, reset it when they don't. Duplicates are skipped without resetting, since they don't break a streak, they just don't extend it either.

- **Time:** O(n log n) — dominated by the sort; the scan afterward is O(n).
- **Space:** O(1) extra beyond the input (no HashSet needed) — the tradeoff for this variant's simplicity is the O(n log n) sort, versus the canonical's O(n) time at the cost of an O(n) HashSet.

**Why it works:** once sorted, any streak of consecutive integers occupies a contiguous run of *array positions* too — sorting is exactly what makes value-adjacency and index-adjacency coincide (the property [sliding window can't get for free](../../slidingwindow/README.md) without paying this same sort cost). A single left-to-right scan comparing each element to its predecessor then sees every streak as an unbroken run of `nums[i+1] == nums[i] + 1` steps, so tracking a reset-on-break counter captures the longest one. Duplicates don't reset the streak because a repeated value doesn't contradict "still consecutive" — it's simply not a new member either, so it's skipped rather than treated as a break.

**Watch out:** `Arrays.sort(nums)` mutates the input array in place — the caller's array comes back in a different order than it went in. This is easy to miss since the method still just *returns an int*, giving no hint that it also silently reordered the array you passed in.

## Visualizing it

```
nums = [100, 4, 200, 1, 3, 2]   (before sort)

Arrays.sort(nums):
nums = [1, 2, 3, 4, 100, 200]   (after sort — same array, new order)

Walk adjacent pairs:

  i=0: nums[1]=2, nums[0]=1  -> 2 == 1+1  -> currentStreak=2  longestStreak=2
  i=1: nums[2]=3, nums[1]=2  -> 3 == 2+1  -> currentStreak=3  longestStreak=3
  i=2: nums[3]=4, nums[2]=3  -> 4 == 3+1  -> currentStreak=4  longestStreak=4
  i=3: nums[4]=100,nums[3]=4 -> not adjacent, and 100 != 4 -> currentStreak resets to 1
  i=4: nums[5]=200,nums[4]=100 -> not adjacent, and 200 != 100 -> currentStreak resets to 1

result: longestStreak = 4
```

## Code

```java
package arraysandhashing.longestconsecutivesequence;

import java.util.Arrays;

public class LongestConsecutiveSequenceSorted implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int longestStreak = 1;
        int currentStreak = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] == nums[i] + 1) {
                currentStreak++;
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;
                }
            } else if (nums[i + 1] != nums[i]) {
                currentStreak = 1;
            }
        }
        return longestStreak;
    }
}
```
