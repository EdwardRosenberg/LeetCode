public class MoveZerosToEnd {

    // Method to move zeros to the end of the array
    public void moveZeros(int[] nums) {
        if (nums == null || nums.length == 0)
            return;

        // keeps track of non-zero elements
        int index = 0;

        for (int num : nums) {
            // If the current element is not zero, move it to the front of the array
            if (num != 0) {
                nums[index++] = num;
            }
        }

        // Fill the rest of the array with zeros
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }
}
