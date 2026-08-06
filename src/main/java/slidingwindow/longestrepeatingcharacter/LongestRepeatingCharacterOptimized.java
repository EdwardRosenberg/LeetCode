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
 * Approach: Same expand/shrink window as the canonical version, but track
 * the window's most-frequent-character count incrementally — only check
 * the newly added character's count against a running max, never rescan
 * the whole frequency map. The running max is deliberately never lowered
 * when the window shrinks (see the .md's Why it works for why that's
 * still correct).
 *
 * Time: O(n) — every character is added to and removed from the window at
 * most once, and each step does O(1) work.
 * Space: O(alphabet) for the frequency map.
 *
 * See LongestRepeatingCharacter.java for the canonical (rescan-based)
 * version.
 */
public class LongestRepeatingCharacterOptimized implements CharacterReplacer {

    @Override
    public int characterReplacement(String input, int maxReplacements) {

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int mostFrequentCount = addCharAndUpdateMostFrequentCount(input, frequencyMap, right, 0);

        while (right < input.length()) {
            if (isWindowValid(left, right, mostFrequentCount, maxReplacements)) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                if (right < input.length()) {
                    mostFrequentCount = addCharAndUpdateMostFrequentCount(input, frequencyMap, right, mostFrequentCount);
                }
            } else {
                // Watch out: mostFrequentCount is NOT decremented here, even
                // though a character is leaving the window. That's
                // intentional — see the .md's Why it works.
                frequencyMap.merge(input.charAt(left), -1, Integer::sum);
                left++;
            }
        }

        return maxLength;
    }

    private int addCharAndUpdateMostFrequentCount(String input, Map<Character, Integer> frequencyMap, int right, int mostFrequentCount) {
        frequencyMap.merge(input.charAt(right), 1, Integer::sum);
        return Math.max(mostFrequentCount, frequencyMap.get(input.charAt(right)));
    }

    private boolean isWindowValid(int left, int right, int mostFrequentCount, int maxReplacements) {
        return right - left - mostFrequentCount + 1 <= maxReplacements;
    }
}
