package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceRecursion implements LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        if (nums.length == 0 || nums.length == 1) return nums.length;

        Set<Integer> input = new HashSet<>();

        for (int n : nums) {
            input.add(n);
        }

        int max = 1;

        for (int i : input) {
            if (input.contains(i + 1) && !input.contains(i - 1)) {
                int count = getNextNums(input, i);
                if (count > max) max = count;
            }
        }

        return max;
    }

    static int getNextNums(Set<Integer> input, int num) {
        int count = 1;
        if (input.contains(num + 1)) {
            count += getNextNums(input, num + 1);
        }

        return count;
    }
}
