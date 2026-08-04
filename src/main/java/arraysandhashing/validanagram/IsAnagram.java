package arraysandhashing.validanagram;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 242 — Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 * <p>
 * Pattern: Arrays & Hashing
 * Cue: "two strings/values use the exact same characters/elements the same number of times"
 * <p>
 * Approach: Count letters of the first word into a HashMap, then walk the
 * second word decrementing counts. Any missing key or leftover count means
 * the letter frequencies don't match.
 * <p>
 * Time: O(n) — one pass to build counts, one pass to consume them.
 * Space: O(1) — at most 26 lowercase letters in the map.
 * <p>
 * Variants: IsAnagramSorted.java (sorts both words and compares — simpler
 * to write, O(n log n) instead of O(n)), IsAnagramCounting.java (same
 * canonical-signature idea as GroupAnagramsHash, but a flat int[26] array
 * instead of a HashMap — same O(n) time, tighter constants)
 */
public class IsAnagram implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.equals(compareWord)) return true;
        if (word.length() != compareWord.length()) return false;

        Map<Character, Integer> letterCount = new HashMap<>();
        char[] wordLetters = word.toCharArray();
        char[] compareLetters = compareWord.toCharArray();

        // Count letters of the first word into a HashMap
        for (char wordLetter : wordLetters) {
            // If letter is already in the map - increase count
            if (letterCount.containsKey(wordLetter)) {
                letterCount.merge(wordLetter, 1, Integer::sum);
            // if not, add letter to the map
            } else {
                letterCount.put(wordLetter, 1);
            }
        }

        // Walk second word, decrementing the mapped letter counts
        for (char compareLetter : compareLetters) {
            // any letter that doesn't exist in the map means it's not anagram
            if (!letterCount.containsKey(compareLetter)) return false;

            Integer count = letterCount.get(compareLetter);
            count = count - 1;
            if (count == 0) {
                letterCount.remove(compareLetter);
            } else {
                letterCount.put(compareLetter, count);
            }
        }

        return true;
    }
}
