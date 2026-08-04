# Arrays & Hashing

Cue: needing fast lookups, frequency counts, or grouping-by-signature over an array/string — anything a HashMap/HashSet turns from O(n²) into O(n).

## Concepts

Building blocks the rest of this pattern assumes. Skim-level, not a tutorial — just enough to not be re-deriving these from scratch mid-problem.

- **Dynamic arrays** — `ArrayList`/`List` grow past their backing array's current capacity by allocating a bigger array (usually double) and copying everything over. That copy is O(n), but it happens rarely enough (halving in frequency as the array grows) that the *average* cost per append across many appends is still O(1) — "amortized O(1)". This is why building up a result in a `List` and appending in a loop doesn't secretly make a solution O(n²).
- **Hash usage** — using a `HashMap`/`HashSet` as a black box: O(1) *average-case* insert/lookup/delete by key, in exchange for O(n) extra space. This is the actual trade the whole pattern skeleton below is built on — "remember what you've seen" only pays off because remembering is O(1), not O(n).
- **Hash implementation** — what's happening inside that O(1): a hash function turns a key into a bucket index, collisions within a bucket are resolved by chaining (a small list, or a tree once a bucket gets large enough in modern Java), and the whole table resizes once it gets too full (load factor) to keep buckets short. The practical consequence: a key's `hashCode()` and `equals()` must agree — two keys that are `.equals()` but hash differently will silently fail to collide in the same bucket, which is exactly the failure mode called out in [GroupAnagramsHash's `Watch out`](groupanagrams/GroupAnagramsHash.md) (why a raw `char[]` can't be used as a key, but a `String` can).
- **Prefix sums** — precompute a running total (or product) in one O(n) pass so that a range query that would otherwise cost O(n) per query costs O(1) after the precompute. [Product of Array Except Self](productofarrayexceptself/ProductOfArrayExceptSelfOptimal.md) is this pattern applied to products instead of sums; "subarray sums to K" problems (not yet in this index) are the canonical sum version.

## Pattern skeleton

```
seen = {}   // HashMap or HashSet
for item in items:
  key = derive(item)   // value, complement, sorted/counted signature, etc.
  if key in seen: found it — return / update
  else: seen[key] = item
```

The core move is always the same: trade an O(n) or O(n log n) inner search for an O(1) hash lookup by remembering what you've already seen. What varies per problem is *what* you use as the key (the raw value, its complement, a frequency count, a sorted/canonical signature).

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | complement lookup → HashMap value→index | [TwoSumHashMap.md](twosum/TwoSumHashMap.md) | 2026-08-02 |
| [Valid Anagram](https://leetcode.com/problems/valid-anagram/) | Easy | two strings/values use the exact same characters/elements the same number of times | [IsAnagram.md](validanagram/IsAnagram.md) (+ [sorted](validanagram/IsAnagramSorted.md), [counting](validanagram/IsAnagramCounting.md) variants) | 2026-08-02 |
| [Group Anagrams](https://leetcode.com/problems/group-anagrams/) | Medium | group strings/values by some canonical signature | [GroupAnagramsHash.md](groupanagrams/GroupAnagramsHash.md) (+ [sorted variant](groupanagrams/GroupAnagrams.md)) | 2026-08-02 |
| [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/) | Medium | k most/least frequent elements | [TopKFrequentElementsSorted.md](topkfrequentelements/TopKFrequentElementsSorted.md) (+ [bucket variant](topkfrequentelements/TopKFrequentElementsBucket.md)) | 2026-08-02 |
| [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/) | Medium | product/sum of everything except the current element, without division | [ProductOfArrayExceptSelfOptimal.md](productofarrayexceptself/ProductOfArrayExceptSelfOptimal.md) (+ [brute force](productofarrayexceptself/ProductOfArrayExceptSelfBruteForce.md), [division](productofarrayexceptself/ProductOfArrayExceptSelfDivision.md) variants) | 2026-08-02 |
| [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/) | Medium | no duplicates within each row/column/sub-group of a grid | [ValidSudoku.md](validsudoku/ValidSudoku.md) (+ [single hashset variant](validsudoku/ValidSudokuSingleHashset.md)) | 2026-08-02 |
| [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/) | Medium | longest run of consecutive values in an unsorted array, without sorting | [LongestConsecutiveSequenceRecursion.md](longestconsecutivesequence/LongestConsecutiveSequenceRecursion.md) (+ [hashset](longestconsecutivesequence/LongestConsecutiveSequenceHashSet.md), [sorted](longestconsecutivesequence/LongestConsecutiveSequenceSorted.md) variants) | 2026-08-02 |
