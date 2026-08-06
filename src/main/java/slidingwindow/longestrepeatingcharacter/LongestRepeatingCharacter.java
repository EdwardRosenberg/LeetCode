package slidingwindow.longestrepeatingcharacter;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 424 — Longest Repeating Character Replacement (Medium)
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 *
 * Pattern: Sliding Window
 * Cue: "longest substring achievable by replacing at most k characters"
 *
 * Approach: Expand/shrink window tracking character frequencies in a
 * HashMap. A window is valid when replacing every character that isn't
 * the window's most frequent one costs at most maxReplacements swaps —
 * windowLength - mostFrequentCount <= maxReplacements. Shrink from the
 * left whenever that's violated.
 *
 * Time: O(n * alphabet) — every validity check rescans the whole frequency
 * map to find the current most-frequent count. Still linear for a fixed
 * alphabet, but a real constant-factor cost the Optimized variant avoids.
 * Space: O(alphabet) for the frequency map.
 *
 * Variants: LongestRepeatingCharacterOptimized.java (tracks the most
 * frequent count incrementally instead of rescanning the map on every
 * check — genuinely O(n))
 */
public class LongestRepeatingCharacter implements CharacterReplacer {

    @Override
    public int characterReplacement(String input, int maxReplacements) {

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        // Prime the window with its first character before the loop, so the
        // loop body only ever needs to add the *next* character — mirroring
        // how a character is added after each successful expansion below.
        frequencyMap.merge(input.charAt(right), 1, Integer::sum);

        while (right < input.length()) {
            if (isWindowValid(left, right, maxReplacements, frequencyMap)) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                if (right < input.length()) {
                    frequencyMap.merge(input.charAt(right), 1, Integer::sum);
                }
            } else {
                frequencyMap.merge(input.charAt(left), -1, Integer::sum);
                left++;
            }
        }

        return maxLength;
    }

    private boolean isWindowValid(int left, int right, int maxReplacements, Map<Character, Integer> frequencyMap) {

        int mostFrequentCount = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            mostFrequentCount = Math.max(mostFrequentCount, entry.getValue());
        }

        return right - left - mostFrequentCount + 1 <= maxReplacements;
    }
}
