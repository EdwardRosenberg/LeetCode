# Valid Anagram (Sorted Variant)

- **LeetCode:** [242 — Valid Anagram](https://leetcode.com/problems/valid-anagram/) (Easy)
- **Pattern:** Arrays & Hashing
- **Cue:** "two strings/values use the exact same characters/elements the same number of times"

See [IsAnagram.md](IsAnagram.md) for the canonical O(n) HashMap-counting version — this is the sorting variant, indexed separately here since it's short enough to read on its own.

## Approach

Sort both words' characters and compare the sorted arrays — anagrams sort to the exact same sequence.

- **Time:** O(n log n) — dominated by sorting both character arrays.
- **Space:** O(n) for the two sorted-character copies.

**Why it works:** sorting is a canonical form — any two words with identical letter multisets sort to the exact same sequence, regardless of the order the letters appeared in originally. So `Arrays.equals` on the sorted arrays is really asking "do these two words have the same letters with the same counts," which is the definition of an anagram. The `word.length() != compareWord.length()` guard matters here specifically because `Arrays.equals` on two different-length arrays would just return false anyway — but without the length check first, the loop that copies characters into fixed-size arrays would need extra bounds handling.

## Code

```java
package arraysandhashing.validanagram;

import java.util.Arrays;

/**
 * LeetCode 242 — Valid Anagram (Easy)
 * https://leetcode.com/problems/valid-anagram/
 *
 * Pattern: Arrays & Hashing
 * Cue: "two strings/values use the exact same characters/elements the same number of times"
 *
 * Approach: Sort both words' characters and compare the sorted arrays —
 * anagrams sort to the exact same sequence.
 *
 * Time: O(n log n) — dominated by sorting both character arrays.
 * Space: O(n) for the two sorted-character copies.
 *
 * See IsAnagram.java for the canonical O(n) HashMap-counting version.
 */
public class IsAnagramSorted implements AnagramChecker {

    @Override
    public boolean isAnagram(String word, String compareWord) {
        if (word.equals(compareWord)) return true;
        if (word.length() != compareWord.length()) return false;

        char[] wordLetters = new char[word.length()];
        char[] compareLetters = new char[compareWord.length()];

        for (int i = 0; i < word.length(); i++) {
            wordLetters[i] = word.charAt(i);
            compareLetters[i] = compareWord.charAt(i);
        }

        Arrays.sort(wordLetters);
        Arrays.sort(compareLetters);

        return Arrays.equals(wordLetters, compareLetters);
    }
}
```
