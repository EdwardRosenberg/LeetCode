package arraysandhashing.groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
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
 * Approach: Sort each word's characters and use the sorted string as its
 * anagram signature — two anagrams always sort to the exact same string.
 * Bucket words by that signature in a HashMap.
 *
 * Time: O(n * k log k) — n words, k = max word length; sorting each word
 * dominates.
 * Space: O(n * k) for the signature-to-group map.
 *
 * See GroupAnagramsHash.java for the canonical, more optimal version: a
 * 26-length letter-count signature avoids the per-word sort, bringing this
 * down to O(n * k).
 */
public class GroupAnagrams implements GroupAnagramsChecker {

    @Override
    public List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> anagramsBySignature = new HashMap<>();

        for (String word : words) {
            String signature = sortedSignature(word);
            if (!anagramsBySignature.containsKey(signature)) {
                anagramsBySignature.put(signature, new ArrayList<>());
            }

            anagramsBySignature.get(signature).add(word);
        }

        return new ArrayList<>(anagramsBySignature.values());
    }

    private static String sortedSignature(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
}
