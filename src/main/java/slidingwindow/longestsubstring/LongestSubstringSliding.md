# Longest Substring Without Repeating Characters

- **LeetCode:** [3 — Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "longest/shortest substring or subarray satisfying a condition"

Variants: [LongestSubstringBacktrack](LongestSubstringBacktrack.md) (discards and rebuilds the window by scanning backward on every duplicate instead of sliding a left pointer — same idea, but O(n * window size) worst case)

## Approach

Expand the right pointer; on a duplicate, shrink from the left until the window is valid again. Window contents are tracked in a `HashSet`.

- **Time:** O(n) — each element enters and leaves the window at most once.
- **Space:** O(min(n, alphabet))

**Why it works:** for every `right`, the `while` loop shrinks `left` only as far as necessary to remove the *one* duplicate that `input.charAt(right)` introduced — so after the loop, `[left, right]` is the largest valid (repeat-free) window that ends exactly at `right`. Checking `maxLength` at every `right` therefore compares the best-possible window ending at *every* position in the string, which by construction includes whichever position the true longest substring ends at. `left` only ever moves forward, never back, which is what keeps the total shrink work across the whole scan bounded by n instead of re-scanning from earlier positions.

**Watch out:** Easy to shrink *after* adding the new char instead of *before* — that lets duplicates into the window. The order is: check for duplicate → shrink → add.

## Visualizing it

```
input = "abcabcbb"

Grow the window while chars stay unique:

  a  b  c
  ^     ^
 left  right      seenChars = {a,b,c}   maxLength = 3

right hits 'a' again — it's already in the window:

  a  b  c  a
  ^        ^
 left     right      duplicate!

Shrink `left` one step at a time until the duplicate drops out:

  a  b  c  a
     ^     ^
    left  right       dropped 'a' — no longer a duplicate, stop shrinking

Add the new char, window valid again:

  b  c  a
  ^     ^
 left  right      seenChars = {b,c,a}   maxLength stays 3

...repeat expand/shrink to the end of the string.
```

## Code

```java
package slidingwindow.longestsubstring;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringSliding implements LongestSubstringFinder {

    @Override
    public int lengthOfLongestSubstring(String input) {
        if (input.length() <= 1) return input.length();

        int maxLength = 0;
        Set<Character> seenChars = new HashSet<>();

        int left = 0;

        for (int right = 0; right < input.length(); right++) {

            // Duplicate found: shrink from the left until it's gone, so the
            // window always holds a valid (repeat-free) substring.
            while (seenChars.contains(input.charAt(right))) {
                seenChars.remove(input.charAt(left));
                left++;
            }
            seenChars.add(input.charAt(right));
            maxLength = Math.max(maxLength, seenChars.size());
        }

        return maxLength;
    }
}
```
