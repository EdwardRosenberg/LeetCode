# Permutation in String (Optimized Variant)

- **LeetCode:** [567 — Permutation in String](https://leetcode.com/problems/permutation-in-string/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "does any substring of text contain the exact same character counts as pattern"

See [PermutationInString.md](PermutationInString.md) for the canonical version.

## Approach

Same fixed-size sliding window as the canonical version, but instead of comparing the two 26-letter count maps on every step, track a running `matches` counter — how many of the 26 letters currently have equal counts between `pattern` and the window. Every single-character update can only flip *one* letter's agreement status, so `matches` only ever needs adjusting by at most 1 per update instead of being recomputed from scratch.

- **Time:** O(n) — every window shift does O(1) work (two single-letter updates), instead of an O(alphabet) comparison.
- **Space:** O(alphabet) for the two fixed-size (26-entry) count maps.

**Why it works:** `matches` counts how many of the 26 letters currently have identical counts in `patternCharCounts` and `windowCharCounts` — `matches == 26` is exactly equivalent to the two maps being fully equal (what the canonical version checks directly). Each update touches exactly one letter's count, which can only change *that* letter's agreement status: it was agreeing and now isn't (`matches--`), wasn't and now is (`matches++`), or its status didn't change either way (no adjustment needed). Tracking the delta this way is what turns an O(alphabet) re-check into an O(1) one.

**Watch out:** `matches` starts *high*, not at 0 — most of the 26 letters have a count of 0 on both sides trivially (neither `pattern` nor the window contains them), so those already "match" before any real comparison happens. Only the letters actually present in `pattern` or the window can disagree. Don't be surprised to see `matches` start at something like 23 or 24 out of 26 for a short pattern.

## Visualizing it

```
pattern = "ab", text = "xxxxba"

Initial window is text[0:2] = "xx":
  patternCharCounts: a=1, b=1, everything else 0
  windowCharCounts:  x=2, everything else 0
  matches = 23   (all 26 letters except a, b, x agree trivially at 0; those three disagree)

Slide, updating `matches` by at most ±1 per character swapped:

  window="xx"  outgoing='x' incoming='x'  matches: 23 -> 23  (x count still doesn't match either way)
  window="xx"  outgoing='x' incoming='x'  matches: 23 -> 23
  window="xb"  outgoing='x' incoming='b'  matches: 23 -> 24  ('b' now agrees: window has 1, pattern has 1)
  window="ba"  outgoing='x' incoming='a'  matches: 24 -> 26  ('x' drops to 0 and agrees; 'a' now agrees too)

matches == 26 -> MATCH
```

## Code

```java
package slidingwindow.permutationinstring;

import java.util.HashMap;
import java.util.Map;

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
```
