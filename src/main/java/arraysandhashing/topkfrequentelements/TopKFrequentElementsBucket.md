# Top K Frequent Elements (Bucket Variant)

- **LeetCode:** [347 — Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "k most/least frequent elements"

See [TopKFrequentElementsSorted.md](TopKFrequentElementsSorted.md) for the canonical version.

## Approach

Sort the array so equal numbers become adjacent, then walk it once counting each run's length. Bucket numbers by that frequency count (a frequency can't exceed `nums.length`, so an array-sized range of buckets covers every possible count), then walk buckets from the highest possible frequency down to 1, collecting numbers until k are found.

- **Time:** O(n log n) — **not** O(n), despite the "bucket sort" framing. The frequency-counting step here sorts the array first (to find runs of equal numbers), and that sort dominates. Only the bucket-walk step is genuinely O(n).
- **Space:** O(n) for the frequency-to-numbers buckets.

**Why it works:** a frequency can never exceed `nums.length`, so indexing buckets by frequency (1 through n) covers every possible count with no gaps to worry about — every number lands in exactly one bucket, keyed by its exact run length. Walking buckets from the highest frequency down to 1 visits numbers in strictly non-increasing frequency order, so collecting until k numbers are gathered is guaranteed to be the k most frequent — the same guarantee a full sort-by-frequency would give, just without needing a comparator over all distinct entries at once.

**Watch out — this doesn't actually achieve the complexity "bucket sort" implies:** the textbook version of this technique counts frequencies with a HashMap (`O(n)`, no sort needed) and only uses buckets for the second step, landing on genuine O(n) overall. This variant sorts the array first to find frequency runs, which costs O(n log n) — the same complexity class as the canonical comparator-sort version, just with more code to get there. "It uses buckets" doesn't automatically mean "it's O(n)" — check what happens *before* the bucket step too.

**Watch out — `Arrays.sort(nums)` also mutates the input array in place**, same as the sorted variants of other problems in this repo — the caller's array comes back reordered.

## Visualizing it

```
nums = [1, 1, 1, 2, 2, 3]   (already sorted here for simplicity)

Walk once, counting runs of equal adjacent values:

  1,1,1 -> run length 3 -> numbersByFrequency[3] = [1]
  2,2   -> run length 2 -> numbersByFrequency[2] = [2]
  3     -> run length 1 -> numbersByFrequency[1] = [3]

Walk frequency from nums.length (6) down to 1, collecting until k=2:

  freq=6..4: no bucket
  freq=3: bucket [1] -> take 1          topK = [1]
  freq=2: bucket [2] -> take 2          topK = [1, 2]  -> k reached, stop

result: [1, 2]
```

## Code

```java
package arraysandhashing.topkfrequentelements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class TopKFrequentElementsBucket implements TopKFrequentCalculator {

    @Override
    public int[] topKFrequent(int[] nums, int k) {

        int[] topK = new int[k];
        Map<Integer, List<Integer>> numbersByFrequency = new HashMap<>();

        Arrays.sort(nums);

        int currentValue = nums[0];
        int currentRunLength = 0;

        for (int num : nums) {
            if (num == currentValue) {
                currentRunLength++;
            } else {
                numbersByFrequency.computeIfAbsent(currentRunLength, frequency -> new Stack<>());
                numbersByFrequency.get(currentRunLength).add(currentValue);
                currentValue = num;
                currentRunLength = 1;
            }
        }
        numbersByFrequency.computeIfAbsent(currentRunLength, frequency -> new Stack<>());
        numbersByFrequency.get(currentRunLength).add(currentValue);

        int topKIndex = 0;

        for (int frequency = nums.length; frequency > 0; frequency--) {
            if (numbersByFrequency.containsKey(frequency)) {
                Iterator<Integer> numbersAtThisFrequency = numbersByFrequency.get(frequency).iterator();
                while (numbersAtThisFrequency.hasNext()) {
                    topK[topKIndex] = numbersAtThisFrequency.next();
                    topKIndex++;
                    if (topKIndex >= k) return topK;
                }
            }
        }

        return topK;
    }
}
```
