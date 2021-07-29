package twosum;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int firstIndex = 0;

        while (firstIndex < nums.length - 1) {
            int secondIndex = firstIndex + 1;

            while (secondIndex < nums.length) {
                if (nums[firstIndex] + nums[secondIndex] == target) {
                    return new int[]{firstIndex, secondIndex};
                }
                secondIndex++;
            }
            firstIndex++;
        }
        return new int[]{};
    }
}
