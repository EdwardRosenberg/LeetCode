package arraysandhashing;

import java.util.Arrays;

/**
 * LeetCode 128 — Longest Consecutive Sequence (Medium)
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Pattern: Arrays & Hashing
 * Cue: "longest run of consecutive values in an unsorted array, without sorting"
 *
 * Approach: Sort the array, then do a single linear pass tracking a running
 * streak — extend it whenever adjacent elements differ by exactly 1, reset
 * it when they don't. Duplicates are skipped without resetting, since they
 * don't break a streak, they just don't extend it either.
 *
 * Time: O(n log n) — dominated by the sort; the scan afterward is O(n).
 * Space: O(1) extra beyond the input (no HashSet needed) — but see below.
 *
 * See LongestConsecutiveSequenceRecursion.java for the canonical, genuinely
 * O(n) version, and LongestConsecutiveSequenceHashSet.java for another
 * O(n + range) variant.
 */
public class LongestConsecutiveSequenceSorted implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        // Watch out: Arrays.sort mutates `nums` in place — the caller's
        // array comes back in a different order than it went in.
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
                // Handle duplicate numbers: reset the streak only if the next
                // number is genuinely different, not just a repeat.
                currentStreak = 1;
            }
        }
        return longestStreak;
    }
}
