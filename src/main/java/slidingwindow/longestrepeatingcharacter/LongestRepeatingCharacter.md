# Longest Repeating Character Replacement

- **LeetCode:** [424 — Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "longest substring achievable by replacing at most k characters"

Variants: [LongestRepeatingCharacterOptimized](LongestRepeatingCharacterOptimized.md) (tracks the most frequent count incrementally instead of rescanning the map on every check — genuinely O(n))

## Approach

Expand/shrink window tracking character frequencies in a HashMap. A window is valid when replacing every character that isn't the window's most frequent one costs at most `maxReplacements` swaps — `windowLength - mostFrequentCount <= maxReplacements`. Shrink from the left whenever that's violated.

- **Time:** O(n * alphabet) — every validity check rescans the whole frequency map to find the current most-frequent count. Still linear for a fixed alphabet, but a real constant-factor cost the Optimized variant avoids.
- **Space:** O(alphabet) for the frequency map.

**Why it works:** for a window to become a single repeated character using at most `maxReplacements` swaps, every character *other than* the most common one in that window has to be replaced — that's exactly `windowLength - mostFrequentCount` replacements. If that's `<= maxReplacements`, the window is achievable; if not, no amount of choosing *which* characters to replace changes the count, since you'd always replace the minority characters and keep the majority one. Rescanning the map for the true max on every check keeps that count exact at all times, so the validity check is never wrong — the tradeoff is redoing that scan (bounded by alphabet size) on every single step.

## Visualizing it

```
input = "AABABBA", maxReplacements = 1

Grow while windowLength - mostFrequentCount <= 1:

  "A"     freq={A:1} most=1  len=1  1-1=0<=1  OK
  "AA"    freq={A:2} most=2  len=2  2-2=0<=1  OK
  "AAB"   freq={A:2,B:1} most=2  len=3  3-2=1<=1  OK
  "AABA"  freq={A:3,B:1} most=3  len=4  4-3=1<=1  OK   <- maxLength=4

Grow once more — now invalid:

  "AABAB" freq={A:3,B:2} most=3  len=5  5-3=2>1  INVALID -> shrink left

Shrink drops the leftmost 'A'; rescanning the map finds the true new max:

  "ABAB"  freq={A:2,B:2} most=2  len=4  4-2=2>1  still INVALID -> shrink again
  "BAB"   freq={A:1,B:2} most=2  len=3  3-2=1<=1  OK, valid again — but only length 3

...the window keeps sliding, occasionally hitting length 4 again ("BABB" -> invalid ->
"ABBA" -> invalid -> "BBA"), but 4 is never beaten.

result: maxLength = 4
```

Notice `mostFrequentCount` genuinely drops (3 → 2) the moment the window shrinks past the character that made it the majority — that accuracy is this version's whole point, at the cost of rescanning the map on every check to get it.

## Code

```java
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
```
