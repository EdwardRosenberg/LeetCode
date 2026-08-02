package arraysandhashing;

import java.util.Arrays;

/**
 * LeetCode 242 — Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * Pattern: Arrays & Hashing
 * Cue: "two strings/values use the exact same characters/elements the same number of times"
 *
 * Approach: Sort both words' characters and compare the sorted arrays —
 * anagrams sort to the exact same sequence.
 *
 * Time: O(n log n) — dominated by sorting both character arrays.
 * Space: O(n) for the two sorted-character copies.
 *
 * See IsAnagram.java for the canonical O(n) HashMap-counting version.
 */
public class IsAnagramSorted implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.equals(compareWord)) return true;
        if (word.length() != compareWord.length()) return false;

        char[] wordLetters = new char[word.length()];
        char[] compareLetters = new char[compareWord.length()];

        for (int i = 0; i < word.length(); i++) {
            wordLetters[i] = word.charAt(i);
            compareLetters[i] = compareWord.charAt(i);
        }

        Arrays.sort(wordLetters);
        Arrays.sort(compareLetters);

        return Arrays.equals(wordLetters, compareLetters);
    }
}
