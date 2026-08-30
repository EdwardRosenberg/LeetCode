package twopointers.threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15 — 3Sum (Medium)
 * https://leetcode.com/problems/3sum/
 *
 * Pattern: Two Pointers
 * Cue: "find all triples summing to a target — fix one, two-pointer the rest"
 *
 * Approach: Sort the array, then fix each index in turn as the smallest
 * value of a candidate triple, and two-pointer the remaining subarray
 * (left = fixedIndex+1, right = end) looking for pairs that complete the
 * sum to zero. Skip duplicate values for the fixed index, and for left
 * right after a match, so the same triple is never emitted twice.
 *
 * Time: O(n^2) — an O(n) two-pointer scan for each of the n choices of
 * fixed index.
 * Space: O(n) (or O(log n)) for the sort, excluding the output.
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> triples = new ArrayList<>();

        Arrays.sort(nums);

        for (int fixedIndex = 0; fixedIndex < nums.length - 2; fixedIndex++) {

            // Skip duplicate fixed values — they'd only rediscover triples
            // already found while this same value was fixed last time.
            if (fixedIndex > 0 && nums[fixedIndex] == nums[fixedIndex - 1]) continue;
            // Sorted ascending: once the smallest of the three is positive,
            // no later (equal-or-larger) pair can bring the sum back to 0.
            if (nums[fixedIndex] > 0) break;

            int left = fixedIndex + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[fixedIndex] + nums[left] + nums[right];

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    triples.add(Arrays.asList(nums[fixedIndex], nums[left], nums[right]));
                    // Skip duplicate left values so the same triple isn't
                    // emitted again with an identical left partner.
                    do {
                        left++;
                    } while (nums[left] == nums[left - 1] && left < right);
                }
            }
        }

        return triples;
    }
}
