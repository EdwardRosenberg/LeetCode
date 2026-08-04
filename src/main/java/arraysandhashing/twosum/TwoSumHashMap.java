package arraysandhashing.twosum;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1 — Two Sum (Easy)
 * https://leetcode.com/problems/two-sum/
 *
 * Pattern: Arrays & Hashing
 * Cue: "complement lookup → HashMap value→index"
 *
 * Approach: Walk the array once, checking whether target - nums[i] has
 * already been seen. If so, its stored index plus the current index is the
 * answer; otherwise record this value's index for later lookups.
 *
 * Time: O(n) — single pass, O(1) HashMap lookups.
 * Space: O(n) for the value-to-index map.
 */
public class TwoSumHashMap {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (indexByValue.containsKey(complement)) {
                return new int[]{indexByValue.get(complement), i};
            } else {
                indexByValue.put(nums[i], i);
            }
        }

        return new int[]{};
    }
}
