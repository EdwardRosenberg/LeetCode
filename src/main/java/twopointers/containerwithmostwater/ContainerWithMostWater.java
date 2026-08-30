package twopointers.containerwithmostwater;

/**
 * LeetCode 11 — Container With Most Water (Medium)
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Pattern: Two Pointers
 * Cue: "maximize area/product between two elements — start wide, narrow inward"
 *
 * Approach: Start with pointers at both ends (the widest possible
 * container). At each step, capacity is bounded by the shorter of the two
 * walls, so move whichever pointer points at the shorter wall inward —
 * that's the only move that could possibly find a taller wall and beat
 * the current best.
 *
 * Time: O(n) — each pointer moves inward at most once per index.
 * Space: O(1)
 */
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

            // Moving the taller wall inward can never help: the width only
            // shrinks, and capacity is still capped by the same shorter
            // wall (or an even shorter one) either way. Only moving the
            // shorter wall has a chance of finding something taller.
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return bestArea;
    }
}
