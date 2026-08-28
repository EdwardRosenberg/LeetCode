# Permutation in String (HashArray Variant)

- **LeetCode:** [567 — Permutation in String](https://leetcode.com/problems/permutation-in-string/) (Medium)
- **Pattern:** Sliding Window
- **Cue:** "does any substring of text contain the exact same character counts as pattern"

See [PermutationInString.md](PermutationInString.md) for the canonical (two-map) version.

## Approach

A single `int[26]` array tracks the *net* difference between `pattern`'s letter counts and the current window's — `pattern`'s letters count as +1 each, the window's letters count as -1 each. The window matches exactly when every slot in the array nets out to 0.

- **Time:** O(n * alphabet) — checking "is everything balanced" costs O(alphabet), and that check runs once per window position. Same complexity class as the canonical two-map version, just a leaner (unboxed, fixed-size) data structure.
- **Space:** O(alphabet) for the array.

**Why it works:** every occurrence of a letter in `pattern` contributes +1 to its slot, and every occurrence of that same letter currently in the window contributes -1 (via `consumeInitialWindow` for the first window, then the increment/decrement pair on each slide). A slot nets to 0 exactly when the window contains that letter the same number of times `pattern` does. All 26 slots at 0 simultaneously means every letter's count matches — which is the definition of "the window is a permutation of pattern."

**Watch out:** this is the same complexity class as the canonical version, not an improvement on it — `isBalanced` still scans all 26 slots every time, same as `Map.equals` does under the hood for the two-map version. The array just avoids `Character`/`Integer` boxing and two separate map objects; it doesn't change the Big-O. [PermutationInStringOptimized.md](PermutationInStringOptimized.md) is the one that actually gets to O(n).

## Visualizing it

```
pattern = "ab", text = "xxxxba"

buildPatternCounts: netCharCounts = {a:+1, b:+1}, rest 0

consumeInitialWindow (first 2 chars of text, "xx"):
  netCharCounts[x] -= 1 twice -> netCharCounts = {a:1, b:1, x:-2}   not balanced

Slide: each step returns the outgoing char's slot toward 0, then takes the
incoming char's slot away from 0 (only nonzero slots shown):

  return 'x', incoming 'x'  -> {a:1, b:1, x:-2}   still not balanced
  return 'x', incoming 'x'  -> {a:1, b:1, x:-2}   still not balanced
  return 'x', incoming 'b'  -> {a:1, x:-1}        ('b' returned to 0 and cancelled out — not balanced yet)
  return 'x', incoming 'a'  -> {}                 every slot is 0 -> MATCH

result: true
```

## Code

```java
package slidingwindow.permutationinstring;

public class PermutationInStringHashArray implements PermutationChecker {

    @Override
    public boolean checkInclusion(String pattern, String text) {
        if (text.length() < pattern.length()) return false;

        int[] netCharCounts = buildPatternCounts(pattern);

        int left = 0;
        int right = consumeInitialWindow(pattern, text, netCharCounts);

        if (isBalanced(netCharCounts)) return true;

        while (right < text.length()) {
            netCharCounts[charIndex(text.charAt(left))]++;

            left++;
            right++;

            if (right < text.length()) netCharCounts[charIndex(text.charAt(right))]--;
            if (isBalanced(netCharCounts)) return true;
        }
        return isBalanced(netCharCounts);
    }

    private int consumeInitialWindow(String pattern, String text, int[] netCharCounts) {
        int right = 0;

        while (right < pattern.length()) {
            netCharCounts[charIndex(text.charAt(right))]--;
            right++;
        }

        right--;
        return right;
    }

    private int charIndex(char c) {
        return c - 'a';
    }

    private int[] buildPatternCounts(String pattern) {
        int[] counts = new int[26];
        for (int i = 0; i < pattern.length(); i++) {
            counts[charIndex(pattern.charAt(i))]++;
        }
        return counts;
    }

    private boolean isBalanced(int[] netCharCounts) {
        for (int count : netCharCounts) {
            if (count != 0) return false;
        }
        return true;
    }
}
```
