# Group Anagrams (Sorted Variant)

- **LeetCode:** [49 — Group Anagrams](https://leetcode.com/problems/group-anagrams/) (Medium)
- **Pattern:** Arrays & Hashing
- **Cue:** "group strings/values by some canonical signature"

See [GroupAnagramsHash.md](GroupAnagramsHash.md) for the canonical, more optimal version — this is the sorting variant.

## Approach

Sort each word's characters and use the sorted string as its anagram signature — two anagrams always sort to the exact same string. Bucket words by that signature in a HashMap.

- **Time:** O(n * k log k) — n words, k = max word length; sorting each word dominates.
- **Space:** O(n * k) for the signature-to-group map.

**Why the hash variant is more optimal:** both approaches build a canonical signature per word and bucket by it — the only difference is how the signature is built. Sorting costs O(k log k) per word; counting each of the 26 possible letters costs O(k) per word. That drops the overall complexity from O(n * k log k) to O(n * k), which matters once words get long, even though this version is arguably the more obvious one to reach for first.

**Why it works:** sorting is a canonical form for a multiset — any two words made of the same letters, regardless of original order, sort to the exact same character sequence. So the sorted string *is* a valid signature: identical for any two anagrams, different whenever the letter counts differ. Bucketing by that signature in a HashMap is then just "same signature → same group."

## Code

```java
package arraysandhashing.groupanagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] words) {
        Map<String, List<String>> anagramsBySignature = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            String signature = sortedSignature(words[i]);
            if (!anagramsBySignature.containsKey(signature)) {
                anagramsBySignature.put(signature, new ArrayList<>());
            }

            anagramsBySignature.get(signature).add(words[i]);
        }

        return new ArrayList<>(anagramsBySignature.values());
    }

    private static String sortedSignature(String word) {
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }
}
```
