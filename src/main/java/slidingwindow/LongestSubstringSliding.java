package slidingwindow;

import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode 3 — Longest Substring Without Repeating Characters (Medium)
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Pattern: Sliding Window
 * Cue: "longest/shortest substring or subarray satisfying a condition"
 *
 * Approach: Expand right pointer; on duplicate, shrink from left until valid.
 * Window contents tracked in a HashSet.
 *
 * Time: O(n) — each element enters and leaves the window at most once.
 * Space: O(min(n, alphabet))
 */
public class LongestSubstringSliding {

    public int lengthOfLongestSubstring(String input) {
        if (input.length() <= 1) return input.length();

        int maxLength = 0;
        Set<Character> seenChars = new HashSet<>();

        int left = 0;

        for (int right = 0; right < input.length(); right++) {

            // Duplicate found: shrink from the left until it's gone, so the
            // window always holds a valid (repeat-free) substring.
            while (seenChars.contains(input.charAt(right))) {
                seenChars.remove(input.charAt(left));
                left++;
            }
            seenChars.add(input.charAt(right));
            maxLength = Math.max(maxLength, seenChars.size());
        }

        return maxLength;
    }
}
