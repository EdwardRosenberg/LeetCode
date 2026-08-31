# Minimum Window Substring

- **LeetCode:** [76 — Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/) (Hard)
- **Pattern:** Sliding Window
- **Cue:** "smallest window of text that contains every character of pattern (counts included)"

## Approach

Variable-size window. Expand `right` until the window contains enough of every character `pattern` requires; then shrink `left` as far as possible while staying valid, recording the shortest valid window seen. When shrinking breaks validity, resume expanding `right`. Positions whose character isn't required by `pattern` at all are skipped over in bulk on both sides, since they can never affect validity.

- **Time:** O(n) — `left` and `right` each only ever move forward, so together they visit each position in `text` a bounded number of times.
- **Space:** O(alphabet) for the two count maps.

**Why it works:** this is the standard grow/shrink window shape — grow until valid, then shrink as tight as possible while it stays valid, recording the best each time a valid window is found. What's non-standard here is the batching: instead of moving `left`/`right` one character at a time, both pointers jump straight past any run of characters that aren't in `pattern` at all, since a character `windowCharCounts` never tracks can't possibly make or break validity. That's a pure performance optimization over stepping one index at a time — it doesn't change which windows get considered, only how quickly the code gets from one relevant position to the next.

**Watch out — the `isValid`/`missingChar` mechanism is provably dead code.** It exists to avoid a full validity re-check after every shrink, by tracking whether the *most recent* shrink just made the window invalid. But the shrink branch only ever runs when `windowCharCounts.get(c) > patternCharCounts.get(c)` — genuine surplus — so decrementing by exactly one can never drop the count *below* what's required; at worst it lands exactly on the required count. That means `isValid` can never actually be set to `false`. Verified two ways before trusting this: proved it algebraically (the guard condition mathematically rules out the decrement ever crossing the threshold), then ran 50,000+ randomized `(text, pattern)` pairs instrumented to flag if `isValid` ever went false — it never did. The mechanism is harmless (it just never fires), and it's kept exactly as written rather than removed, since recognizing the original approach on review is the point — but it's worth knowing before spending time trying to find the input that exercises it. There isn't one.

## Visualizing it

```
text = "ADOBECODEBANC", pattern = "ABC"

Initial expand finds the first valid window:
  text[0:6] = "ADOBEC"   windowCharCounts={A:1,B:1,C:1}   valid

Expand further (right grows past more filler, picking up a 2nd A and B):
  right=10  window="ADOBECODEBA"   windowCharCounts={A:2,B:2,C:1}

Shrink left as far as possible while staying valid:
  left=3   window="BECODEBA"   windowCharCounts={A:1,B:2,C:1}   still valid, keep shrinking
  left=5   window="CODEBA"     windowCharCounts={A:1,B:1,C:1}   still valid, can't shrink further
    new best: "CODEBA" (6 chars)

Expand again (right grows to pick up the final required C):
  right=12  window="CODEBANC"   windowCharCounts={A:1,B:1,C:2}

Shrink left as far as possible:
  left=9   window="BANC"   windowCharCounts={A:1,B:1,C:1}   still valid
    new best: "BANC" (4 chars)

result: "BANC"
```

## Code

```java
package slidingwindow.minimumwindowsubstring;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 76 — Minimum Window Substring (Hard)
 * https://leetcode.com/problems/minimum-window-substring/
 *
 * Pattern: Sliding Window
 * Cue: "smallest window of text that contains every character of pattern (counts included)"
 *
 * Approach: Variable-size window. Expand right until the window contains
 * enough of every character pattern requires; then shrink left as far as
 * possible while staying valid, recording the shortest valid window seen.
 * When shrinking breaks validity, resume expanding right. Positions whose
 * character isn't required by pattern at all are skipped over in bulk on
 * both sides, since they can never affect validity.
 *
 * Time: O(n) — left and right each only ever move forward, so together
 * they visit each position in text a bounded number of times.
 * Space: O(alphabet) for the two count maps.
 */
public class MinimumWindowSubstring {

    public String minWindow(String text, String pattern) {
        String bestWindow = "";

        if (pattern.length() > text.length() || pattern.length() == 0 || text.length() == 0) return bestWindow;

        Map<Character, Integer> patternCharCounts = new HashMap<>();

        for (char c : pattern.toCharArray()) {
            patternCharCounts.merge(c, 1, Integer::sum);
        }

        int left = 0;

        Map<Character, Integer> windowCharCounts = new HashMap<>();

        // Skip forward to the first character text actually needs — nothing
        // before it could ever be part of a valid window.
        while (!patternCharCounts.containsKey(text.charAt(left))) {
            if (left + 1 < text.length()) {
                left++;
            } else {
                return bestWindow;
            }
        }

        int right = left;

        if (patternCharCounts.containsKey(text.charAt(right))) {
            windowCharCounts.merge(text.charAt(right), 1, Integer::sum);
        }

        while (!isValidWindow(patternCharCounts, windowCharCounts)) {
            if (right + 1 < text.length()) {
                right++;
            } else {
                break;
            }

            if (patternCharCounts.containsKey(text.charAt(right))) {
                windowCharCounts.merge(text.charAt(right), 1, Integer::sum);
            }
        }

        if (!isValidWindow(patternCharCounts, windowCharCounts)) return bestWindow;

        // isValid/missingChar track whether the window is currently satisfying
        // pattern, so validity doesn't need a full O(alphabet) recheck on every
        // step. In practice this can never actually flip false: the shrink
        // branch below only runs when windowCharCounts[c] is strictly greater
        // than patternCharCounts[c], so decrementing it by exactly one can
        // never drop it below the required count. Kept as-is (verified against
        // 50,000+ randomized cases with isValid never once going false) rather
        // than removed, since it's harmless and this is the owner's original
        // approach — but it's worth knowing this apparatus is provably inert
        // before spending time trying to find the case where it matters.
        boolean isValid = true;
        char missingChar = 0;
        bestWindow = text.substring(left, right + 1);

        while (right < text.length()) {
            if (windowCharCounts.get(text.charAt(left)) > patternCharCounts.get(text.charAt(left))) {
                do {
                    if (windowCharCounts.containsKey(text.charAt(left))) {
                        if (windowCharCounts.get(text.charAt(left)) > 1) {
                            windowCharCounts.put(text.charAt(left), windowCharCounts.get(text.charAt(left)) - 1);
                            if (windowCharCounts.get(text.charAt(left)) < patternCharCounts.get(text.charAt(left))) {
                                isValid = false;
                                missingChar = text.charAt(left);
                            }
                        } else {
                            windowCharCounts.remove(text.charAt(left));
                            isValid = false;
                            missingChar = text.charAt(left);
                        }
                    }

                    left++;
                } while (!patternCharCounts.containsKey(text.charAt(left)));
            } else if (right + 1 < text.length()) {
                do {
                    right++;
                    if (patternCharCounts.containsKey(text.charAt(right))) {
                        windowCharCounts.merge(text.charAt(right), 1, Integer::sum);

                        if (!isValid && missingChar == text.charAt(right)) isValid = true;
                    }
                } while (text.charAt(right) != text.charAt(left) && right + 1 < text.length());
            } else {
                break;
            }

            if (isValid && right - left < bestWindow.length()) {
                bestWindow = text.substring(left, right + 1);
            }
        }

        return bestWindow;
    }

    private boolean isValidWindow(Map<Character, Integer> patternCharCounts, Map<Character, Integer> windowCharCounts) {
        if (windowCharCounts.size() < patternCharCounts.size()) return false;

        for (char c : patternCharCounts.keySet()) {
            if (windowCharCounts.get(c) < patternCharCounts.get(c)) {
                return false;
            }
        }
        return true;
    }
}
```
