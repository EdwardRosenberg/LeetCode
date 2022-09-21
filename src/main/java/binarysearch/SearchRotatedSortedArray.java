package binarysearch;

public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (nums[middle] == target) return middle;

            // in the left sorted portion of the array
            if (nums[left] <= nums[middle]) {
                if (nums[middle] < target || nums[left] > target) {
                    left = middle + 1;
                } else {
                    right = middle - 1;
                }
            // in the rigtht portion of the array
            } else {
                if (nums[middle] > target || nums[right] < target) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
        }

        return -1;
    }
}
