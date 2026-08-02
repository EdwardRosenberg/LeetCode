package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 128 — Longest Consecutive Sequence (Medium)
 * https://leetcode.com/problems/longest-consecutive-sequence/
 *
 * Pattern: Arrays & Hashing
 * Cue: "longest run of consecutive values in an unsorted array, without sorting"
 *
 * Approach: Put every number in a HashSet, track the array's min and max,
 * then walk every integer from min to max, extending a streak counter on a
 * hit and resetting it on a miss. Matched numbers are removed from the set
 * so its remaining size can be used to bail out early once it's too small
 * to possibly beat the current best streak.
 *
 * Time: O(n + range), where range = max - min. This is NOT true O(n) in
 * general — it scans every *integer* between min and max, not just the n
 * values actually present. With sparse values (e.g. two numbers a billion
 * apart), range can vastly exceed n, and the early-exit check below doesn't
 * reliably save you: on an input with many isolated numbers spread evenly
 * across a huge range, the longest streak stays small (e.g. 1) for most of
 * the scan while the set shrinks only slowly, so the exit condition rarely
 * fires early. See LongestConsecutiveSequenceRecursion.java for a version
 * that's genuinely O(n) regardless of how spread out the values are.
 * Space: O(n) for the HashSet.
 *
 * See LongestConsecutiveSequenceRecursion.java for the canonical, genuinely
 * O(n) version, and LongestConsecutiveSequenceSorted.java for the sorted
 * variant.
 */
public class LongestConsecutiveSequenceHashSet implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        Set<Integer> numberSet = new HashSet<>();
        int max = 0;
        int min = 0;

        // Watch out: min/max start at 0, not nums[0]. This still ends up
        // safe — Math.min only ever moves min down and Math.max only ever
        // moves max up, so an all-positive or all-negative array just pads
        // the scan range with extra guaranteed-miss iterations rather than
        // producing a wrong answer. It's fragile, though: the seed value
        // (0) is only safe because it's inside-or-outside the true range in
        // the direction that doesn't matter. The standard, less fragile
        // idiom is to seed both from nums[0].
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
                // Consume the match so the set's remaining size reflects
                // only unvisited numbers — that's what the early-exit check
                // below relies on to mean something.
                numberSet.remove(num);
            } else {
                currentStreak = 0;
            }

            longestStreak = Math.max(longestStreak, currentStreak);

            // Early exit: once what's left in the set is too small to ever
            // beat the best streak found so far, no later streak can either.
            // Requiring currentStreak == 0 means "not mid-streak" — bailing
            // out mid-streak could cut off a run that was still growing.
            if (numberSet.size() <= longestStreak && currentStreak == 0) break;
        }

        return longestStreak;
    }
}
