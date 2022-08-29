public class RemoveDuplicatesFromArray2 {

    public int removeDuplicates(int[] nums) {
        int offset = 0;
        int sortedNumSize = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && i < nums.length - 1 && nums[i] == nums[i - 1] && nums[i] == nums[i + 1]) {
                offset++;
            } else {
                nums[i - offset] = nums[i];
                sortedNumSize++;
            }
        }

        return sortedNumSize;
    }
}
