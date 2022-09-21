package arraysandhashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequenceSorted {

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0) return 0;

        Arrays.sort(nums);

        int max = 1;
        int count = 1;

        for(int i=0; i<nums.length - 1; i++){
            if(nums[i+1] == nums[i] + 1){
                count++;
                if(count > max){
                    max = count;
                }
            } else if (nums[i+1] != nums[i]) {
                // handle duplicate numbers - reset count only if next number is not duplicate
                // duplicates will get skipped without resetting the count
                count = 1;
            }
        }
        return max;
    }

}
