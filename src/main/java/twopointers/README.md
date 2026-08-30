# Two Pointers

Cue: two indices moving toward each other (or in the same direction at different speeds) to narrow a search space in O(n), typically over a sorted array or a string being compared to its own reverse.

## Pattern skeleton

```
left = 0, right = n - 1
while left < right:
  evaluate(left, right)
  if condition favors moving left:
    left++
  else:
    right--
```

Opposite-direction pointers shrink the space between them by one side per step, based on a comparison that's provably safe to act on — the eliminated side could never have produced a better answer than what's already been considered. This differs from Sliding Window in that the two pointers bound a *search space over index pairs* rather than a single contiguous window whose contents are tracked as a unit.

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) | Easy | ignore non-alphanumeric characters, compare case-insensitively front-to-back | [ValidPalindrome.md](validpalindrome/ValidPalindrome.md) | 2026-08-29 |
| [3Sum](https://leetcode.com/problems/3sum/) | Medium | find all triples summing to a target — fix one, two-pointer the rest | [ThreeSum.md](threesum/ThreeSum.md) | 2026-08-29 |
| [Container With Most Water](https://leetcode.com/problems/container-with-most-water/) | Medium | maximize area/product between two elements — start wide, narrow inward | [ContainerWithMostWater.md](containerwithmostwater/ContainerWithMostWater.md) | 2026-08-29 |
| [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) | Hard | water trapped at each position is bounded by the shorter of its tallest left/right walls | [TrappingRainwater.md](trappingrainwater/TrappingRainwater.md) (+ [two-pointer variant](trappingrainwater/TrappingRainwaterTwoPointers.md)) | 2026-08-29 |
