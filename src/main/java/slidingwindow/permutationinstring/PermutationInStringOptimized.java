package slidingwindow.permutationinstring;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 567 — Permutation in String (Medium)
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Pattern: Sliding Window
 * Cue: "does any substring of text contain the exact same character counts as pattern"
 *
 * Approach: Same fixed-size sliding window as the canonical version, but
 * instead of comparing the two 26-letter count maps on every step, track
 * a running `matches` counter — how many of the 26 letters currently have
 * equal counts between pattern and the window. Every single-character
 * update can only flip ONE letter's agreement status, so `matches` only
 * ever needs adjusting by at most 1 per update instead of being
 * recomputed from scratch.
 *
 * Time: O(n) — every window shift does O(1) work (two single-letter
 * updates), instead of an O(alphabet) comparison.
 * Space: O(alphabet) for the two fixed-size (26-entry) count maps.
 *
 * See PermutationInString.java for the canonical version.
 */
public class PermutationInStringOptimized implements PermutationChecker {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    @Override
    public boolean checkInclusion(String pattern, String text) {
        if (pattern.length() > text.length()) return false;

        int left = 0;
        int right = pattern.length() - 1;

        Map<Character, Integer> patternCharCounts = initCharacterMap();
        Map<Character, Integer> windowCharCounts = initCharacterMap();

        for (int i = 0; i < pattern.length(); i++) {
            patternCharCounts.merge(pattern.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < pattern.length(); i++) {
            windowCharCounts.merge(text.charAt(i), 1, Integer::sum);
        }

        int matches = countInitialMatches(patternCharCounts, windowCharCounts);

        while (right < text.length()) {

            if (matches == 26) {
                return true;
            }

            if (right + 1 < text.length()) {
                left++;
                right++;

                char outgoing = text.charAt(left - 1);
                windowCharCounts.merge(outgoing, -1, Integer::sum);
                matches = updateMatchesOnDecrease(patternCharCounts, windowCharCounts, outgoing, matches);

                char incoming = text.charAt(right);
                windowCharCounts.merge(incoming, 1, Integer::sum);
                matches = updateMatchesOnIncrease(patternCharCounts, windowCharCounts, incoming, matches);
            } else {
                break;
            }
        }

        return false;
    }

    private Map<Character, Integer> initCharacterMap() {
        Map<Character, Integer> map = new HashMap<>(26);
        for (char c : ALPHABET.toCharArray()) {
            map.put(c, 0);
        }
        return map;
    }

    private int countInitialMatches(Map<Character, Integer> patternCharCounts, Map<Character, Integer> windowCharCounts) {
        int matches = 0;

        for (Map.Entry<Character, Integer> entry : patternCharCounts.entrySet()) {
            if (entry.getValue().equals(windowCharCounts.get(entry.getKey()))) {
                matches++;
            }
        }

        return matches;
    }

    private int updateMatchesOnDecrease(Map<Character, Integer> patternCharCounts, Map<Character, Integer> windowCharCounts, char currentChar, int matches) {
        boolean currentlyMatches = windowCharCounts.get(currentChar).equals(patternCharCounts.get(currentChar));
        boolean previouslyMatched = patternCharCounts.get(currentChar).equals(windowCharCounts.get(currentChar) + 1);

        if (!previouslyMatched && currentlyMatches) {
            matches++;
        } else if (previouslyMatched && !currentlyMatches) {
            matches--;
        }
        return matches;
    }

    private int updateMatchesOnIncrease(Map<Character, Integer> patternCharCounts, Map<Character, Integer> windowCharCounts, char currentChar, int matches) {
        boolean currentlyMatches = windowCharCounts.get(currentChar).equals(patternCharCounts.get(currentChar));
        boolean previouslyMatched = patternCharCounts.get(currentChar).equals(windowCharCounts.get(currentChar) - 1);

        if (!previouslyMatched && currentlyMatches) {
            matches++;
        } else if (previouslyMatched && !currentlyMatches) {
            matches--;
        }
        return matches;
    }
}
