package arraysandhashing;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceHashSet implements LongestConsecutiveSequence{

    public int longestConsecutive(int[] nums) {

        Set<Integer> input = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        int max = 0;
        int min = 0;

        // Find min and max from array & add each to set
        for (int i : nums) {
            set.add(i);
            max = Math.max(max, i);
            min = Math.min(min, i);
        }

        int c = 0; // count streak
        int res = 0; // longest streak

        for (int i = min; i <= max; ++i) {
            // Check if set contains ith value; increment count & remove from set
            if (set.contains(i)) {
                c++;
                set.remove(i);
            } else {
                // If not found set count to 0
                c = 0;
            }
            // Find the longest streak at every step in case we break out from loop
            res = Math.max(res, c);

            // If set size is less than current longest streak break as we wont get any longer streak
            if (set.size() <= res && c == 0) break;
        }
        return res;
    }
}
