# Valid Anagram (Counting Variant)

- **LeetCode:** [242 — Valid Anagram](https://leetcode.com/problems/valid-anagram/) (Easy)
- **Pattern:** Arrays & Hashing
- **Cue:** "two strings/values use the exact same characters/elements the same number of times"

See [IsAnagram.md](IsAnagram.md) for the canonical HashMap-counting version — this applies the same canonical-signature idea as [Group Anagrams](GroupAnagramsHash.md), but to a single comparison instead of grouping many words.

## Approach

Walk `word` incrementing a fixed 26-slot counter per letter, then walk `compareWord` decrementing the same counters. If every slot lands back at zero, the letter frequencies matched exactly.

- **Time:** O(n) — two linear passes over fixed-size counters.
- **Space:** O(1) — a flat 26-element array, no boxing or dynamic map entries.

This is asymptotically the same O(n) as the canonical HashMap version, but tighter in practice — a fixed primitive array instead of a dynamic `Map<Character, Integer>` avoids boxing and per-entry map overhead.

## Visualizing it

```
word = "rat", compareWord = "car"

letterCounts index:   0(a) 1(b) 2(c) ... 17(r) ... 19(t) ...

Walk "rat", incrementing:
  r -> letterCounts[17] = 1
  a -> letterCounts[0]  = 1
  t -> letterCounts[19] = 1

Walk "car", decrementing:
  c -> letterCounts[2]  = -1
  a -> letterCounts[0]  =  0
  r -> letterCounts[17] =  0

Final: letterCounts[2] = -1, letterCounts[19] = 1, rest 0
  -> not all zero -> false ("rat" and "car" aren't anagrams)
```

## Code

```java
package arraysandhashing;

public class IsAnagramCounting implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.length() != compareWord.length()) return false;

        int[] letterCounts = new int[26];

        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }

        for (char c : compareWord.toCharArray()) {
            letterCounts[c - 'a']--;
        }

        for (int count : letterCounts) {
            if (count != 0) return false;
        }

        return true;
    }
}
```
