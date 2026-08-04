package arraysandhashing.validanagram;

/**
 * LeetCode 242 — Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * Pattern: Arrays & Hashing
 * Cue: "two strings/values use the exact same characters/elements the same number of times"
 *
 * Approach: Same canonical-signature idea as GroupAnagramsHash, but applied
 * to a single comparison instead of grouping many words — walk `word`
 * incrementing a fixed 26-slot counter per letter, then walk `compareWord`
 * decrementing the same counters. If every slot lands back at zero, the
 * letter frequencies matched exactly.
 *
 * Time: O(n) — two linear passes over fixed-size counters.
 * Space: O(1) — a flat 26-element array, no boxing or dynamic map entries.
 *
 * See IsAnagram.java for the canonical HashMap-counting version.
 */
public class IsAnagramCounting implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.length() != compareWord.length()) return false;

        int[] letterCounts = new int[26];

        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }

        for (char c : compareWord.toCharArray()) {
            letterCounts[c - 'a']--;
        }

        for (int count : letterCounts) {
            if (count != 0) return false;
        }

        return true;
    }
}
