

public class RemoveDuplicatesFromArray {

    public int removeDuplicates(int[] nums) {
        // Initialize countDuplicates to keep track of the number of duplicates encountered
        int countDuplicates = 0;
        // Initialize uniqueElementsCount to keep track of the count of unique elements in the array
        int uniqueElementsCount = 0;

        // Iterate through the array
        for (int i = 0; i < nums.length; i++) {
            // If current element is a duplicate (not the first occurrence), increment countDuplicates
            if (i > 0 && nums[i] == nums[i - 1]) {
                countDuplicates++;
            } else {
                // If current element is not a duplicate
                // Move the current element to its correct position considering the count of duplicates
                nums[i - countDuplicates] = nums[i];
                // Increment uniqueElementsCount to keep track of the count of unique elements
                uniqueElementsCount++;
            }
        }

        // Return the count of unique elements in the array
        return uniqueElementsCount;
    }
}
