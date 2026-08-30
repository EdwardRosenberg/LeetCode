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
