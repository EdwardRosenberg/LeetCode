package twopointers.trappingrainwater;

/**
 * LeetCode 42 — Trapping Rain Water (Hard)
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Pattern: Two Pointers
 * Cue: "water trapped at each position is bounded by the shorter of its tallest left/right walls"
 *
 * Approach: Two pointers move inward from both ends, each tracking the
 * tallest wall seen so far on its own side. Whichever side's tracked max
 * is currently the smaller one is guaranteed accurate for computing
 * trapped water there, regardless of what the far side's true max
 * eventually turns out to be — so that side is always safe to resolve.
 *
 * Time: O(n) — each pointer moves inward exactly once per index.
 * Space: O(1) extra, versus O(n) for the canonical version's arrays.
 *
 * See TrappingRainwater.java for the canonical version.
 */
public class TrappingRainwaterTwoPointers implements RainTrapper {

    @Override
    public int trap(int[] height) {

        int maxRain = 0;
        int maxLeft = 0;
        int maxRight = 0;

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int rain = Math.min(maxLeft, maxRight) - height[left];
            if (rain > 0) maxRain += rain;

            rain = Math.min(maxLeft, maxRight) - height[right];
            if (rain > 0) maxRain += rain;

            if (maxLeft < height[left]) maxLeft = height[left];
            if (maxRight < height[right]) maxRight = height[right];

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxRain;
    }
}
