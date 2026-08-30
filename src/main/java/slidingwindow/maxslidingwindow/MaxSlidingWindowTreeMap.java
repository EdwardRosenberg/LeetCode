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
