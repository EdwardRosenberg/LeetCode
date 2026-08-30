# Longest Substring Without Repeating Characters (Backtrack Variant)

- **LeetCode:** [3 — Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "longest/shortest substring or subarray satisfying a condition"

See [LongestSubstringSliding.md](LongestSubstringSliding.md) for the canonical, genuinely O(n) version.

## Approach

Walk forward adding characters to a set. On a duplicate, discard the set entirely and rebuild it by scanning backward from the current position until hitting a character already re-added — that reconstructs the longest repeat-free window ending here, without ever tracking a left pointer.

- **Time:** O(n * w) where w is the size of the window being rebuilt — **not** a clean O(n). Every duplicate triggers a backward scan proportional to the current window size, and that can happen at every index. For a bounded alphabet (the common case) w is capped by the alphabet size, so this is still effectively linear in practice — but it's a real, measurable constant-factor cost the sliding-left-pointer version avoids entirely.
- **Space:** O(min(n, alphabet)) for the set.

**Why it works:** the backward scan starting at `i` re-adds characters one at a time until it hits the *first* one already present in the fresh set — by construction, that's exactly the boundary of the longest repeat-free run ending at `i`, since everything added before the stop point is, by definition, distinct. Discarding the old set instead of surgically removing only the stale characters means every duplicate costs a full rebuild rather than an incremental fix — that's the tradeoff for not having to track where the window actually starts.

**Watch out — this is genuinely more expensive than it looks:** with a large or unbounded alphabet, a string that keeps growing a large window before hitting a duplicate (then repeating that pattern) forces a full-window backward scan on every reset. Verified empirically: a 4000-character string built from a 200-character repeating block does ~764,000 backward-scan operations — close to the `n * window` prediction (4000 * 200 = 800,000), not the ~4,000 a true O(n) pass would take. For LeetCode's actual constraint (a small, fixed character set), this stays fast enough in practice; it just isn't asymptotically the same as the canonical version.

## Visualizing it

```
input = "abcabcbb"

Grow the window one character at a time — no duplicates yet:

  i=0 'a' -> windowChars={a}         maxLength=1
  i=1 'b' -> windowChars={a,b}       maxLength=2
  i=2 'c' -> windowChars={a,b,c}     maxLength=3

i=3 is 'a' — already in windowChars. Discard everything and rebuild by
scanning backward from i=3 until a character repeats in the *new* set:

  backtrackIndex=3 'a' -> new set={a}
  backtrackIndex=2 'c' -> new set={a,c}
  backtrackIndex=1 'b' -> new set={a,b,c}
  backtrackIndex=0 'a' -> already in the new set -> stop, don't add

  windowChars is now {a,b,c} (representing the substring "bca", positions 1-3)
  maxLength stays 3 (3 is not > 3)

...this discard-and-rebuild repeats at every subsequent duplicate (i=4,5,6,7),
each time reconstructing a window of size <= 3, so maxLength never grows past 3.

result: maxLength = 3
```

## Code

```java
package slidingwindow.longestsubstring;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringBacktrack implements LongestSubstringFinder {

    @Override
    public int lengthOfLongestSubstring(String input) {
        if (input.length() <= 1) return input.length();

        int maxLength = 0;
        Set<Character> windowChars = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (!windowChars.contains(currentChar)) {
                windowChars.add(currentChar);
            } else {
                windowChars = new HashSet<>();
                int backtrackIndex = i;
                while (windowChars.add(input.charAt(backtrackIndex))) {
                    backtrackIndex--;
                }
            }

            maxLength = Math.max(maxLength, windowChars.size());
        }

        return maxLength;
    }
}
```
