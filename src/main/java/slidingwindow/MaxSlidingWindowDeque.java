package slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

public class MaxSlidingWindowDeque {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[getWindowMovements(nums, k)];
        int answerIndex = 0;

        // Store indexes of numbers in descending order of their values
        Deque<Integer> windowQue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (!windowQue.isEmpty() && isWindowMoved(k, windowQue, i)) {
                // remove index of the largest element if the window moved past it
                windowQue.pollFirst();
            }

            // remove indexes of smaller values from the que, since the next incoming number will be larger
            while (!windowQue.isEmpty() && nums[i] > nums[windowQue.peekLast()]) {
                windowQue.pollLast();
            }

            // add new number to the queue
            windowQue.offer(i);

            if (isWindowInitialized(k, i)) {
                ans[answerIndex++] = nums[windowQue.peekFirst()];
            }
        }

        return ans;
    }

    /*
     * Determine the number of time the window of size k will move through the array nums
     */
    private int getWindowMovements(int[] nums, int k) {
        return nums.length - k + 1;
    }

    /*
     * Determine if the window has moved past the index stored in the head of the que
     */
    private boolean isWindowMoved(int k, Deque<Integer> windowQue, int i) {
        return windowQue.peekFirst() < i - k + 1;
    }

    private boolean isWindowInitialized(int k, int i) {
        return i >= k - 1;
    }
}
