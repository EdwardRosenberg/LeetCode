# Top K Frequent Elements

- **LeetCode:** [347 — Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "k most/least frequent elements"

Variants: [TopKFrequentElementsBucket](TopKFrequentElementsBucket.md) (buckets numbers by frequency instead of comparator-sorting entries — same O(n log n) overall, since it sorts the array first to find frequencies; the bucket-walk step itself is O(n), but the sort dominates)

## Approach

Count occurrences into a HashMap, then sort entries by frequency descending and take the first k keys.

- **Time:** O(n log n) — dominated by sorting all distinct entries by frequency.
- **Space:** O(n) for the frequency map.

**Why it works:** the HashMap pass gives an exact count of every distinct number with no possibility of miscounting (each occurrence increments its own key independent of everything else). Sorting those counts descending and taking the first k is then just "top k by a fully-known ranking" — there's no approximation step, so as long as the comparator is a true descending sort by frequency, the first k entries are, by definition, the k highest frequencies.

**Watch out:** When frequencies tie, LeetCode doesn't guarantee an order among the tied elements — don't write a test that asserts exact array order unless the chosen inputs have strictly distinct frequencies among the top k.

## Code

```java
package arraysandhashing.topkfrequentelements;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * LeetCode 347 — Top K Frequent Elements (Medium)
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Pattern: Arrays & Hashing
 * Cue: "k most/least frequent elements"
 *
 * Approach: Count occurrences into a HashMap, then sort entries by frequency
 * descending and take the first k keys.
 *
 * Time: O(n log n) — dominated by sorting all distinct entries by frequency.
 * Space: O(n) for the frequency map.
 *
 * Variants: TopKFrequentElementsBucket.java (buckets numbers by frequency
 * instead of comparator-sorting entries — same O(n log n) overall as
 * implemented here, since it sorts the array first to find frequencies;
 * the bucket-walk step itself is O(n), but the sort dominates)
 */
public class TopKFrequentElementsSorted implements TopKFrequentCalculator {

    @Override
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyByNumber = new HashMap<>();
        int[] topK = new int[k];

        for (int num : nums) {
            frequencyByNumber.merge(num, 1, Integer::sum);
        }

        Stream<Map.Entry<Integer, Integer>> entriesByFrequencyDesc =
                frequencyByNumber.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<Integer> topKNumbers = entriesByFrequencyDesc.limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for (int i = 0; i < topKNumbers.size(); i++) {
            topK[i] = topKNumbers.get(i);
        }

        return topK;
    }
}
```
