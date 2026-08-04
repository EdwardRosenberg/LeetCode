# Valid Anagram

- **LeetCode:** [242 — Valid Anagram](https://leetcode.com/problems/valid-anagram/) (Easy)
- **Pattern:** Arrays & Hashing
- **Cue:** "two strings/values use the exact same characters/elements the same number of times"

Variants: [IsAnagramSorted](IsAnagramSorted.md) (sorts both words and compares — simpler to write, O(n log n) instead of O(n)), [IsAnagramCounting](IsAnagramCounting.md) (same canonical-signature idea as Group Anagrams, but O(1) space via a flat array instead of a HashMap)

## Approach

Count letters of the first word into a HashMap, then walk the second word decrementing counts. Any missing key or leftover count means the letter frequencies don't match.

- **Time:** O(n) — one pass to build counts, one pass to consume them.
- **Space:** O(1) — at most 26 lowercase letters in the map.

**Why it works:** the length check up front guarantees both words have the same count `n`. The first pass builds a map whose values sum to `n`. The second pass performs exactly `n` decrements (one per character of the second word) *if it completes without returning false* — and since entries are removed the instant they hit zero, every surviving entry always holds a value ≥ 1. A total of `n` decrements against a map that summed to `n` forces the remaining sum to be exactly 0 — and the only way that's possible with every entry ≥ 1 is if there are no entries left. So reaching the end of the loop *is* the proof the map is empty; no separate `isEmpty()` check is needed.

**Watch out:** Comparing the *set* of letters isn't enough — `"aacc"` and `"abcc"` share the same letters but different counts. Track counts, not just presence.

## Code

```java
package arraysandhashing.validanagram;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.equals(compareWord)) return true;
        if (word.length() != compareWord.length()) return false;

        Map<Character, Integer> letterCount = new HashMap<>();
        char[] wordLetters = word.toCharArray();
        char[] compareLetters = compareWord.toCharArray();

        // Count letters of the first word into a HashMap
        for (char wordLetter : wordLetters) {
            // If letter is already in the map - increase count
            if (letterCount.containsKey(wordLetter)) {
                Integer count = letterCount.get(wordLetter);
                letterCount.put(wordLetter, count + 1);
            // if not, add letter to map
            } else {
                letterCount.put(wordLetter, 1);
            }
        }

        // Walk second word, decrementing the mapped letter counts
        for (char compareLetter : compareLetters) {
            // any letter that doesn't exist in the map means it's not anagram
            if (!letterCount.containsKey(compareLetter)) return false;

            Integer count = letterCount.get(compareLetter);
            count = count - 1;
            if (count == 0) {
                letterCount.remove(compareLetter);
            } else {
                letterCount.put(compareLetter, count);
            }
        }

        return true;
    }
}
```
