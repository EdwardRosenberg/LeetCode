# Sliding Window

Cue: growing/shrinking a contiguous window over an array or string to satisfy a size, sum, or uniqueness condition.

## Pattern skeleton

```
left = 0
for right in 0..n:
  while window is invalid:
    shrink left++
  process(window)
  update best
```

The window `[left, right]` expands via `right`, shrinks via `left`, and stays valid (or becomes valid after shrinking) at each step. Track window contents in a data structure (HashSet for uniqueness, HashMap for counts, etc.). Record the best answer whenever the window is valid.

**When this doesn't apply:** sliding window only works when the array/string's *given order* is the order you care about — the window walks contiguous index positions. If the condition you're checking depends on *values* rather than *position* (e.g. "these numbers form a consecutive run" vs. "these characters are adjacent"), elements that belong together can be scattered anywhere in the input, and no window over the raw order will see them as adjacent. The only fix is to sort first — but that's O(n log n), so **if the only way to make sliding window fit is to sort first, and the target complexity is O(n), sliding window is already ruled out.** Reach for a HashSet/HashMap instead. See [Longest Consecutive Sequence](../arraysandhashing/longestconsecutivesequence/LongestConsecutiveSequenceRecursion.md) for a concrete case — a "longest run satisfying a condition" problem that superficially matches this pattern's cue but isn't one.

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | longest/shortest substring or subarray satisfying a condition | [LongestSubstringSliding.md](longestsubstring/LongestSubstringSliding.md) | 2026-07-11 |
| [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/) | Medium | longest substring achievable by replacing at most k characters | [LongestRepeatingCharacter.md](longestrepeatingcharacter/LongestRepeatingCharacter.md) (+ [optimized variant](longestrepeatingcharacter/LongestRepeatingCharacterOptimized.md)) | 2026-08-02 |
