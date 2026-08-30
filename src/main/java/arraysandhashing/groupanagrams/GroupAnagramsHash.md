# Group Anagrams

- **LeetCode:** [49 — Group Anagrams](https://leetcode.com/problems/group-anagrams/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "group strings/values by some canonical signature"

Variants: [GroupAnagrams](GroupAnagrams.md) (sorts each word as its signature instead of counting letters — simpler to write, but O(k log k) per word instead of O(k))

## Approach

Build a 26-length letter-count array per word as its anagram signature — two anagrams always produce identical counts. Bucket words by that signature in a HashMap.

- **Time:** O(n * k) — n words, k = max word length; counting letters is O(k) per word.
- **Space:** O(n * k) for the signature-to-group map.

**Why this is more optimal than the sorted variant:** both approaches build a canonical signature per word and bucket by it — the only difference is how the signature is built. Counting each of the 26 possible letters costs O(k) per word; sorting costs O(k log k) per word. That's the entire gap between O(n * k) here and O(n * k log k) in the sorted variant.

**Why it works:** two words are anagrams exactly when they contain the same letters the same number of times — and since the signature is built by counting occurrences per letter independent of order, any two anagrams *always* produce the identical `char[26]` array, and any two non-anagrams differ in at least one slot. That's the whole correctness argument: the signature function is order-independent and count-exact, so "same signature" and "is an anagram of" are the same relation. See "Why this generalizes" below for the broader principle this is an instance of.

**Watch out:** the signature is a `char[26]` array — position = which letter (`c - 'a'`), value at that position = how many times it appears. `new String(letterCounts)` wraps that array so it can be used as a HashMap key, but the result is **not** a readable string like `"a1e1t1"` — each array slot becomes a raw (mostly unprintable) Unicode code point. That's fine; it's never displayed. The reason it's wrapped in a `String` at all, instead of using the `char[]` directly as the key, is that Java arrays don't have value-based `equals()`/`hashCode()` — two different `char[]` objects with identical contents are *not* equal as HashMap keys. `String` does define content-based equality, so it's what actually makes "same letters, same counts" collide into the same bucket.

## Why this generalizes

This is the "canonical signature" trick, and it's worth internalizing on its own — it's the difference between an O(n²) brute-force comparison of every pair and an O(n) hash-based grouping in a whole family of problems:

> Find a transformation where inputs that should be treated as equivalent *always* map to the exact same output — then let a HashMap's built-in equality do the matching, instead of comparing inputs to each other directly.

The transformation (the "signature function") changes per problem, but the shape of the solution doesn't:

| Problem | What "equivalent" means | Signature function |
|---|---|---|
| Group Anagrams (this one) | same letters, any order | letter-count array (or sorted string) |
| Valid Anagram | same as above, just two words instead of many | same signature, compare instead of group |
| Isomorphic Strings / Word Pattern | consistent one-to-one character mapping | normalized "pattern" — e.g. first-occurrence index of each character |
| Subarray Sum Equals K | two prefix sums differing by exactly K | running prefix sum as you scan |
| Contains Duplicate / Two Sum | the literal value itself | none needed — the raw value already *is* the signature |

Once this clicks, the question to ask when a problem smells like "grouping," "matching," or "detecting duplicates under some transformation" isn't "how do I compare every pair" — it's "what's the cheapest signature I can compute per element such that equivalent elements always produce the same one?"

## Visualizing it

```
letterCounts index:   0(a)  1(b)  2(c)  3(d)  4(e)  ...  19(t)  ...  25(z)

"eat" → a:1, e:1, t:1, everything else 0
  letterCounts = [1, 0, 0, 0, 1, 0, ..., 1, ..., 0]

"tea" → same letters, same counts, different order in the word —
        the counting is order-independent, so it produces the exact
        same array:
  letterCounts = [1, 0, 0, 0, 1, 0, ..., 1, ..., 0]   ← identical to "eat"'s

"bat" → a:1, b:1, t:1 — different letters present, so a different array:
  letterCounts = [1, 1, 0, 0, 0, 0, ..., 1, ..., 0]

new String(letterCounts) turns each array into a (unprintable) String key:

  signature("eat") == signature("tea")   → same bucket
  signature("bat")  != signature("eat")   → different bucket

result: [["eat", "tea"], ["bat"]]
```

## Code

```java
package arraysandhashing.groupanagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagramsHash {

    public List<List<String>> groupAnagrams(String[] words) {
        List<List<String>> groupedAnagrams = new ArrayList<>();
        if (words.length == 0) return groupedAnagrams;

        Map<String, List<String>> anagramsBySignature = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String signature = letterCountSignature(words[i]);
            anagramsBySignature.computeIfAbsent(signature, key -> new ArrayList<>());
            anagramsBySignature.get(signature).add(words[i]);
        }

        groupedAnagrams.addAll(anagramsBySignature.values());
        return groupedAnagrams;
    }

    /**
     * Builds this word's fingerprint: a char[26] array, one slot per letter
     * a-z, where the value at each slot is how many times that letter
     * appears. Order doesn't factor in at all, so any two words made of the
     * same letters in the same amounts always produce the identical array.
     * For "eat":
     *
     *   index:   0(a) 1(b) 2(c) 3(d) 4(e) ... 19(t) ... 25(z)
     *   value:    1    0    0    0    1   ...   1    ...   0
     *
     * This is the general move behind "group/detect by content regardless
     * of order or form" problems: find a transformation where equal inputs
     * *always* land on the exact same output, then let a HashMap's equality
     * check do the matching for you. Here the transformation is "count each
     * letter"; elsewhere it might be "sort the characters" (this problem's
     * GroupAnagrams.java variant), "compute a running prefix sum" (subarray
     * sum problems), or "normalize case/whitespace" — same underlying idea,
     * different signature function.
     */
    private static String letterCountSignature(String word) {
        char[] letterCounts = new char[26];
        for (char c : word.toCharArray()) {
            letterCounts[c - 'a']++;
        }
        // Wrap the counts in a String (not a readable one — each slot becomes
        // a raw code point) purely so it has value-based equals()/hashCode():
        // char[] doesn't, so two arrays with identical contents wouldn't
        // collide in the HashMap otherwise.
        return new String(letterCounts);
    }
}
```
