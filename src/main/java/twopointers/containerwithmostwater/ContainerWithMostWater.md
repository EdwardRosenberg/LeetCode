# Container With Most Water

- **LeetCode:** [11 — Container With Most Water](https://leetcode.com/problems/container-with-most-water/) (Medium)
- **Pattern:** Two Pointers
- **Cue:** "maximize area/product between two elements — start wide, narrow inward"

## Approach

Start with pointers at both ends (the widest possible container). At each step, capacity is bounded by the shorter of the two walls, so move whichever pointer points at the shorter wall inward — that's the only move that could possibly find a taller wall and beat the current best.

- **Time:** O(n) — each pointer moves inward at most once per index.
- **Space:** O(1)

**Why it works:** capacity is `min(height[left], height[right]) * width`. If you move the *taller* wall inward instead, the width strictly shrinks and the capacity is still capped by the same shorter wall (or an even shorter one if the new wall is shorter still) — so that move can never produce a better answer than what's already been checked. Moving the shorter wall is the only move that has any chance of raising the limiting height, so it's the only move worth making. Because every width is implicitly checked as the pointers close in — the current `left`/`right` pair *is* the widest remaining container at every step — no better container is ever skipped.

## Visualizing it

```
height = [1, 8, 6, 2, 5, 4, 8, 3, 7]

left=0(1) right=8(7): minHeight=1 width=8 area=8    bestArea=8
  height[left]=1 < height[right]=7 -> move left inward (it's the shorter wall)

left=1(8) right=8(7): minHeight=7 width=7 area=49   bestArea=49
  height[left]=8 >= height[right]=7 -> move right inward

...right keeps moving inward from here; height[left]=8 is now the tallest
wall in the array, so every remaining container is capped by whatever's on
the right side, and none of them beat width=7 * height=7 = 49.

result: bestArea = 49
```

## Code

```java
package twopointers.containerwithmostwater;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int bestArea = 0;

        while (left < right) {
            int minHeight = Math.min(height[left], height[right]);
            int width = right - left;
            int area = minHeight * width;
            if (area > bestArea) bestArea = area;

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return bestArea;
    }
}
```
