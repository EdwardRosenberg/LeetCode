package slidingwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class MaxSlidingWindowTreeMap {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 1) return nums;

        int[] ans = new int[nums.length - k + 1];

        int left = 0;
        int right = k - 1;

        SortedMap<Integer, List<Integer>> window = new TreeMap<>();

        for (int i = left; i <= right; i++) {
            List<Integer> indexList = window.getOrDefault(nums[i], new ArrayList<>());
            indexList.add(i);
            window.put(nums[i], indexList);
        }

        while (right < nums.length) {
            ans[left] = window.lastKey();

            List<Integer> maxWindowIndexList = window.get(nums[left]);
            if (maxWindowIndexList.size() > 1) {
                maxWindowIndexList.remove(0);
            } else {
                window.remove(nums[left]);
            }

            left++;
            right++;

            if (right < nums.length) {
                List<Integer> indexList = window.getOrDefault(nums[right], new ArrayList<>());
                indexList.add(right);
                window.put(nums[right], indexList);
            }
        }

        return ans;
    }
}
