package binarysearch;

public class MinimumRotatedSortedArray {

    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int min = nums[right];

        while (left <= right) {
            int middle = (left + right) / 2;

            // left part of the array
            if (nums[middle] > nums[left]) {
                min = Math.min(min, nums[left]);
                left = middle + 1;
            } else { // right part of the array
                min = Math.min(min, nums[right]);
                min = Math.min(min, nums[middle]);
                right = middle - 1;
            }
        }

        return min;
    }

}
