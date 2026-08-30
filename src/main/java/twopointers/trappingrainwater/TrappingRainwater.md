# Trapping Rain Water

- **LeetCode:** [42 — Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/) (Hard)
- **Pattern:** Two Pointers
- **Cue:** "water trapped at each position is bounded by the shorter of its tallest left/right walls"

Variants: [TrappingRainwaterTwoPointers](TrappingRainwaterTwoPointers.md) (same idea, O(1) space via two inward-moving pointers instead of precomputed arrays)

## Approach

Precompute, for every index, the tallest wall strictly to its left and strictly to its right. The water trapped at that index is `min(leftMax, rightMax) - height[index]` (never negative). Sum across all indices.

- **Time:** O(n) — three linear passes (left-max, right-max, sum).
- **Space:** O(n) for the two auxiliary arrays.

**Why it works:** water can only pool above a position up to the height of the *shorter* of its two bounding walls — a taller wall on one side doesn't help if water would just spill over the shorter wall on the other side first. `maxHeightLeft[i]`/`maxHeightRight[i]` capture exactly that "tallest wall on each side," computed once per index in a single pass each, so the third pass just reads off `min(leftMax, rightMax) - height[i]` directly — no cleverness needed to see why that number is the trapped depth, or why summing it across every index gives the total.

## Visualizing it

```
height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]

maxHeightLeft  (running max of everything strictly left of i):
  [0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3]

maxHeightRight (running max of everything strictly right of i):
  [3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1, 0]

At each index, trapped = min(left, right) - height[i], clamped to 0:

  i=2: min(1,3)-0 = 1
  i=4: min(2,3)-1 = 1
  i=5: min(2,3)-0 = 2
  i=6: min(2,3)-1 = 1
  i=9: min(3,2)-1 = 1
  (all other indices trap 0 — either they're a wall themselves, or one
   side has no taller wall yet)

total = 1+1+2+1+1 = 6
```

## Code

```java
package twopointers.trappingrainwater;

public class TrappingRainwater implements RainTrapper {

    @Override
    public int trap(int[] height) {
        int[] maxHeightLeft = new int[height.length];
        int[] maxHeightRight = new int[height.length];

        int totalRain = 0;
        int runningMax = 0;

        for (int i = 0; i < height.length; i++) {
            maxHeightLeft[i] = runningMax;

            if (height[i] > runningMax) {
                runningMax = height[i];
            }
        }

        runningMax = 0;

        for (int i = height.length - 1; i >= 0; i--) {
            maxHeightRight[i] = runningMax;

            if (height[i] > runningMax) {
                runningMax = height[i];
            }
        }

        for (int i = 0; i < height.length; i++) {
            int rain = Math.min(maxHeightLeft[i], maxHeightRight[i]) - height[i];
            if (rain > 0) {
                totalRain += rain;
            }
        }
        return totalRain;
    }
}
```
