package slidingwindow.maxslidingwindow;

import java.util.Deque;
import java.util.LinkedList;

/**
 * LeetCode 239 — Sliding Window Maximum (Hard)
 * https://leetcode.com/problems/sliding-window-maximum/
 *
 * Pattern: Sliding Window
 * Cue: "max/min of every fixed-size window as it slides across an array"
 *
 * Approach: Keep a deque of indices whose values are in decreasing order,
 * front to back. The front index is always the current window's max.
 * Before adding a new index, pop off any smaller values from the back —
 * they can never be the max while a bigger, more-recent value is still in
 * the window — and pop the front if it's fallen outside the window.
 *
 * Time: O(n) — each index is pushed onto the deque exactly once and
 * popped at most once, regardless of window size.
 * Space: O(k) for the deque, plus O(n) for the output array.
 *
 * Variants: MaxSlidingWindowTreeMap.java (tracks window contents in a
 * TreeMap keyed by value — O(n log k), simpler to reason about but
 * asymptotically worse)
 */
public class MaxSlidingWindowDeque implements MaxSlidingWindowSolver {

    @Override
    public int[] maxSlidingWindow(int[] nums, int windowSize) {
        int[] windowMaxes = new int[countWindowPositions(nums, windowSize)];
        int resultIndex = 0;

        // Indices of candidate maxes, values decreasing from front to back.
        Deque<Integer> maxCandidateIndices = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!maxCandidateIndices.isEmpty() && isWindowMoved(windowSize, maxCandidateIndices, i)) {
                // The current max has fallen outside the window — drop it.
                maxCandidateIndices.pollFirst();
            }

            // Anything smaller than the incoming value can never become the
            // max while this larger, more-recent value is still in play.
            while (!maxCandidateIndices.isEmpty() && nums[i] > nums[maxCandidateIndices.peekLast()]) {
                maxCandidateIndices.pollLast();
            }

            maxCandidateIndices.offer(i);

            if (isWindowInitialized(windowSize, i)) {
                windowMaxes[resultIndex++] = nums[maxCandidateIndices.peekFirst()];
            }
        }

        return windowMaxes;
    }

    private int countWindowPositions(int[] nums, int windowSize) {
        return nums.length - windowSize + 1;
    }

    private boolean isWindowMoved(int windowSize, Deque<Integer> maxCandidateIndices, int i) {
        return maxCandidateIndices.peekFirst() < i - windowSize + 1;
    }

    private boolean isWindowInitialized(int windowSize, int i) {
        return i >= windowSize - 1;
    }
}
