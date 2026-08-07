package slidingwindow.longestsubstring;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3 — Longest Substring Without Repeating Characters (Medium)
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Pattern: Sliding Window
 * Cue: "longest/shortest substring or subarray satisfying a condition"
 *
 * Approach: Walk forward adding characters to a set. On a duplicate,
 * discard the set entirely and rebuild it by scanning backward from the
 * current position until hitting a character already re-added — that
 * reconstructs the longest repeat-free window ending here, without
 * tracking a left pointer.
 *
 * Time: O(n * w) where w is the size of the window being rebuilt — NOT a
 * clean O(n). Every duplicate triggers a backward scan proportional to the
 * current window size, and that can happen at most once per index. For a
 * bounded alphabet (the common case) w is capped by the alphabet size, so
 * this is still effectively linear in practice — but it's a real,
 * measurable constant-factor cost the sliding-left-pointer version avoids
 * entirely. See LongestSubstringSliding.java for that O(n) version.
 * Space: O(min(n, alphabet)) for the set.
 *
 * See LongestSubstringSliding.java for the canonical, genuinely O(n)
 * version.
 */
public class LongestSubstringBacktrack implements LongestSubstringFinder {

    @Override
    public int lengthOfLongestSubstring(String input) {
        if (input.length() <= 1) return input.length();

        int maxLength = 0;
        Set<Character> windowChars = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!windowChars.contains(currentChar)) {
                windowChars.add(currentChar);
            } else {
                // Duplicate: throw away the window and rebuild it from
                // scratch by scanning backward from i, stopping the instant
                // a character repeats within the *new* set — that's the
                // longest repeat-free run ending at i.
                windowChars = new HashSet<>();
                int backtrackIndex = i;
                while (windowChars.add(input.charAt(backtrackIndex))) {
                    backtrackIndex--;
                }
            }

            maxLength = Math.max(maxLength, windowChars.size());
        }

        return maxLength;
    }
}
