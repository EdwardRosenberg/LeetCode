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
 * Approach: Slide a fixed-size window (pattern.length()) across text,
 * tracking each side's letter counts in a HashMap. The window matches
 * whenever its count map is exactly equal to pattern's count map.
 *
 * Time: O(n * alphabet) — comparing two maps for equality costs
 * O(alphabet), and that comparison runs once per window position.
 * Space: O(alphabet) for the two count maps.
 *
 * Variants: PermutationInStringHashArray.java (same O(n * alphabet) idea,
 * fixed int[26] array instead of two HashMaps), PermutationInStringOptimized.java
 * (tracks a running match count instead of comparing maps each step —
 * genuinely O(n))
 */
public class PermutationInString implements PermutationChecker {

    @Override
    public boolean checkInclusion(String pattern, String text) {
        if (pattern.equals(text)) return true;
        if (pattern.length() > text.length()) return false;

        int left = 0;
        int right = pattern.length() - 1;

        Map<Character, Integer> patternCharCounts = new HashMap<>();
        Map<Character, Integer> windowCharCounts = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            patternCharCounts.merge(pattern.charAt(i), 1, Integer::sum);
        }

        for (int i = 0; i < pattern.length(); i++) {
            windowCharCounts.merge(text.charAt(i), 1, Integer::sum);
        }

        while (right < text.length()) {
            if (patternCharCounts.equals(windowCharCounts)) {
                return true;
            }

            if (right + 1 < text.length()) {
                left++;
                right++;

                // Remove the outgoing char — must drop the key entirely at
                // zero, not just leave a 0 behind, or this map could never
                // equal patternCharCounts (which never holds 0-value keys).
                char outgoing = text.charAt(left - 1);
                if (windowCharCounts.get(outgoing) > 1) {
                    windowCharCounts.merge(outgoing, -1, Integer::sum);
                } else {
                    windowCharCounts.remove(outgoing);
                }

                windowCharCounts.merge(text.charAt(right), 1, Integer::sum);
            } else {
                break;
            }
        }

        return false;
    }
}
