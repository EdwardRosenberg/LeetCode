package slidingwindow;

import java.util.*;

public class MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) return nums;

        int[] ans = new int[nums.length - k + 1];

        int left = 0;
        int right = k - 1;

        Deque<Integer> windowQue = new LinkedList<>();

        // init window - find first max val
        for (int i = left; i <= right; i++) {
            // find max val
            // find max index

        }

        while (right < nums.length) {
            // add max to answer array
            ans[left] =

            // move window
            left++;
            right++;

            // if
            //  check if new number is bigger than max
            // *** max is new num - update max and max index
            // else if
            // check if previous max is within window
            // max is previous num
            // else
            // scan window again to find max and max index

        }

        return ans;
    }
}
