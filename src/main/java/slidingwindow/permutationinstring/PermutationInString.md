# Permutation in String

- **LeetCode:** [567 — Permutation in String](https://leetcode.com/problems/permutation-in-string/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "does any substring of text contain the exact same character counts as pattern"

Variants: [PermutationInStringHashArray](PermutationInStringHashArray.md) (same O(n * alphabet) idea, fixed int[26] array instead of two HashMaps), [PermutationInStringOptimized](PermutationInStringOptimized.md) (tracks a running match count instead of comparing maps each step — genuinely O(n))

## Approach

Slide a fixed-size window (`pattern.length()`) across `text`, tracking each side's letter counts in a HashMap. The window matches whenever its count map is exactly equal to `pattern`'s count map.

- **Time:** O(n * alphabet) — comparing two maps for equality costs O(alphabet), and that comparison runs once per window position.
- **Space:** O(alphabet) for the two count maps.

**Why it works:** a substring is a permutation of `pattern` exactly when it contains the same letters the same number of times, in any order — which is precisely what `patternCharCounts.equals(windowCharCounts)` checks, since counts (not positions) are all `equals()` compares. Sliding the window one step at a time and re-checking after every shift means every possible fixed-size substring of `text` gets tested, so the check can't skip over the one that matches. Removing the outgoing character's key entirely at 0 (rather than leaving a 0 behind) matters for correctness, not just tidiness — `patternCharCounts` never contains a 0-value entry for a letter not in `pattern`, so a stray 0-entry in `windowCharCounts` would make the maps compare as unequal even when the letter counts genuinely match.

**Watch out:** the `patternCharCounts.equals(windowCharCounts)` check is doing real (if bounded) work every iteration — it's easy to assume equality checks are "free" and miss that this is where the O(alphabet) factor in the time complexity actually comes from.

## Visualizing it

```
pattern = "ab", text = "xxxxba"

Initial window is text[0:2] = "xx":
  windowCharCounts={x:2}   patternCharCounts={a:1,b:1}   not equal

Slide one step at a time, checking equality after each shift:

  window="xx"  (left=1,right=2)  not equal
  window="xx"  (left=2,right=3)  not equal
  window="xb"  (left=3,right=4)  not equal
  window="ba"  (left=4,right=5)  windowCharCounts={a:1,b:1} == patternCharCounts -> MATCH

result: true
```

## Code

```java
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
```
