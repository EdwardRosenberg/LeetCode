# Longest Consecutive Sequence (HashSet Range-Scan Variant)

- **LeetCode:** [128 — Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "longest run of consecutive values in an unsorted array, without sorting"

See [LongestConsecutiveSequenceRecursion.md](LongestConsecutiveSequenceRecursion.md) for the canonical, genuinely O(n) version.

## Approach

Put every number in a HashSet, track the array's min and max, then walk every integer from min to max, extending a streak counter on a hit and resetting it on a miss. Matched numbers are removed from the set so its remaining size can be used to bail out early once it's too small to possibly beat the current best streak.

- **Time:** O(n + range), where range = max - min. **This is not true O(n) in general** — it scans every *integer* between min and max, not just the n values actually present. With sparse values (e.g. two numbers a billion apart), range can vastly exceed n.
- **Space:** O(n) for the HashSet.

**Watch out — the early-exit doesn't reliably save you:** on an input with many isolated numbers spread evenly across a huge range (streak length capped at 1 for most of the scan), the set shrinks only slowly while the exit threshold (`longestStreak`) stays at 1, so `numberSet.size() <= longestStreak` rarely holds until nearly the whole range has been walked. The heuristic helps a lot in some shapes of input (see the diagram below) and barely at all in others — it's not a guarantee.

**Watch out — the min/max seed:** `min`/`max` start at `0`, not `nums[0]`. This still ends up safe — `Math.min` only ever moves `min` down and `Math.max` only ever moves `max` up, so an all-positive or all-negative array just pads the scan range with extra guaranteed-miss iterations rather than producing a wrong answer. It's fragile, though: the seed value is only safe because of that one-directional property. The standard, less fragile idiom is to seed both from `nums[0]`.

## Visualizing it

```
nums = [100, 4, 200, 1, 3, 2]
numberSet = {100, 4, 200, 1, 3, 2}
min = 0 (wrong — true min is 1, but harmless: see "min/max seed" above)
max = 200 (correct)

Walk num = 0 .. 200:

  num=0: miss                                    longestStreak=0
  num=1: hit, remove 1               numberSet={100,4,200,3,2}   longestStreak=1
  num=2: hit, remove 2               numberSet={100,4,200,3}     longestStreak=2
  num=3: hit, remove 3               numberSet={100,4,200}       longestStreak=3
  num=4: hit, remove 4               numberSet={100,200}         longestStreak=4
  num=5: miss, currentStreak resets to 0
         numberSet.size()=2 <= longestStreak=4 and currentStreak==0
         -> early exit here, skipping num=6..200 entirely

result: longestStreak = 4
```

This case shows the early-exit working well — it skips ~195 unnecessary iterations. But if `100` and `200` above were instead spread across a much larger range with no long streak ever found, the exit wouldn't fire nearly as early (see the complexity note above).

## Code

```java
package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceHashSet implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numberSet = new HashSet<>();
        int max = 0;
        int min = 0;

        for (int num : nums) {
            numberSet.add(num);
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        int currentStreak = 0;
        int longestStreak = 0;

        for (int num = min; num <= max; num++) {
            if (numberSet.contains(num)) {
                currentStreak++;
                numberSet.remove(num);
            } else {
                currentStreak = 0;
            }

            longestStreak = Math.max(longestStreak, currentStreak);

            if (numberSet.size() <= longestStreak && currentStreak == 0) break;
        }

        return longestStreak;
    }
}
```
