package twopointers.trappingrainwater;

/**
 * LeetCode 42 — Trapping Rain Water (Hard)
 * https://leetcode.com/problems/trapping-rain-water/
 *
 * Pattern: Two Pointers
 * Cue: "water trapped at each position is bounded by the shorter of its tallest left/right walls"
 *
 * Approach: Precompute, for every index, the tallest wall strictly to its
 * left and strictly to its right. The water trapped at that index is
 * min(leftMax, rightMax) - height[index] (never negative). Sum across all
 * indices.
 *
 * Time: O(n) — three linear passes (left-max, right-max, sum).
 * Space: O(n) for the two auxiliary arrays.
 *
 * Variants: TrappingRainwaterTwoPointers.java (same idea, O(1) space via
 * two inward-moving pointers instead of precomputed arrays)
 */
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
