package arraysandhashing.groupanagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49 — Group Anagrams (Medium)
 * https://leetcode.com/problems/group-anagrams/
 *
 * Pattern: Arrays & Hashing
 * Cue: "group strings/values by some canonical signature"
 *
 * Approach: Build a 26-length letter-count array per word as its anagram
 * signature — two anagrams always produce identical counts. Bucket words by
 * that signature in a HashMap.
 *
 * Time: O(n * k) — n words, k = max word length; counting letters is O(k)
 * per word.
 * Space: O(n * k) for the signature-to-group map.
 *
 * Variants: GroupAnagrams.java (sorts each word as its signature instead of
 * counting letters — simpler to write, O(k log k) per word)
 */
public class GroupAnagramsHash implements GroupAnagramsChecker {

    @Override
    public List<List<String>> groupAnagrams(String[] words) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        if (words.length == 0) return groupedAnagrams;

        Map<String, List<String>> anagramsBySignature = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String signature = letterCountSignature(words[i]);
            anagramsBySignature.computeIfAbsent(signature, key -> new ArrayList<>());
            anagramsBySignature.get(signature).add(words[i]);
        }

        groupedAnagrams.addAll(anagramsBySignature.values());
        return groupedAnagrams;
    }

    /**
     * Builds this word's fingerprint: a char[26] array, one slot per letter
     * a-z, where the value at each slot is how many times that letter
     * appears. Order doesn't factor in at all, so any two words made of the
     * same letters in the same amounts always produce the identical array.
     * For "eat":
     *
     *   index:   0(a) 1(b) 2(c) 3(d) 4(e) ... 19(t) ... 25(z)
     *   value:    1    0    0    0    1   ...   1    ...   0
     *
     * This is the general move behind "group/detect by content regardless
     * of order or form" problems: find a transformation where equal inputs
     * *always* land on the exact same output, then let a HashMap's equality
     * check do the matching for you. Here the transformation is "count each
     * letter"; elsewhere it might be "sort the characters" (this problem's
     * GroupAnagrams.java variant), "compute a running prefix sum" (subarray
     * sum problems), or "normalize case/whitespace" — same underlying idea,
     * different signature function.
     */
    private static String letterCountSignature(String word) {
        char[] letterCounts = new char[26];
        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }
        // Wrap the counts in a String (not a readable one — each slot becomes
        // a raw code point) purely so it has value-based equals()/hashCode():
        // char[] doesn't, so two arrays with identical contents wouldn't
        // collide in the HashMap otherwise.
        return new String(letterCounts);
    }
}
