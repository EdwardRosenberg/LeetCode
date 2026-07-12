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

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | Medium | longest/shortest substring or subarray satisfying a condition | [LongestSubstringSliding.md](LongestSubstringSliding.md) | 2026-07-11 |
