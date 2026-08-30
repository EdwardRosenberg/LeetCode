package arraysandhashing.topkfrequentelements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 347 — Top K Frequent Elements (Medium)
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Pattern: Arrays & Hashing
 * Cue: "k most/least frequent elements"
 *
 * Approach: Sort the array so equal numbers become adjacent, then walk it
 * once counting each run's length. Bucket numbers by that frequency count
 * (a frequency can't exceed nums.length, so an array-sized range of buckets
 * covers every possible count), then walk buckets from the highest possible
 * frequency down to 1, collecting numbers until k are found.
 *
 * Time: O(n log n) — NOT O(n) despite the "bucket sort" framing: the
 * frequency-counting step here sorts the array first (to find runs of
 * equal numbers), and that sort dominates. Only the bucket-walk step is
 * genuinely O(n). See TopKFrequentElementsSorted.java's canonical
 * comparator-sort version, which is the same overall complexity with far
 * less code — the true benefit of bucket sort (dropping to real O(n))
 * requires counting frequencies with a HashMap instead of sorting to find
 * them.
 * Space: O(n) for the frequency-to-numbers buckets.
 *
 * See TopKFrequentElementsSorted.java for the canonical version.
 */
public class TopKFrequentElementsBucket implements TopKFrequentCalculator {

    @Override
    public int[] topKFrequent(int[] nums, int k) {

        int[] topK = new int[k];
        // frequency is the key; the value is a list, since more than one
        // number can share the same frequency
        Map<Integer, List<Integer>> numbersByFrequency = new HashMap<>();

        // Watch out: this sorts `nums` in place — the caller's array comes
        // back in a different order than it went in.
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
        // The loop only records a run once the *next* different value is
        // seen, so the final run never gets recorded inside the loop —
        // it has to be added once more after the loop ends.
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
