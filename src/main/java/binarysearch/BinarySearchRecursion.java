package binarysearch;

public class BinarySearchRecursion {
    int targetIndex = -1;

    public int search(int[] nums, int target) {
        if (nums.length == 1) {
            return (nums[0] == target ? 0 : -1);
        }

        search(nums, 0, nums.length - 1, target);
        return targetIndex;
    }

    private void search(int[] nums, int left, int right, int target) {
        if (right - left == 1) {
            if (nums[right] == target) targetIndex = right;
            if (nums[left] == target) targetIndex = left;
            return;
        }

        int middle = (left + right) / 2;

        if (nums[middle] < target) {
            search(nums, middle, right, target);
        } else if (nums[middle] > target) {
            search(nums, left, middle, target);
        } else {
            targetIndex = middle;
            return;
        }
    }
}
