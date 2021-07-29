package twosum;

public class TwoSum {

    /**
     * @param inputNumbers - array of integers
     * @param target - integer that two numbers from the input array should add up to
     * @return array with indexes of two numbers that add up to the target number
     */
    public int[] twoSum(int[] inputNumbers, int target) {
        int firstIndex = 0; // start with the first element in the array

        // try every element in the array except the last one
        while (firstIndex < inputNumbers.length - 1) {
            int secondIndex = firstIndex + 1;

            // add every following number in the array and check if it's a match
            while (secondIndex < inputNumbers.length) {
                if (inputNumbers[firstIndex] + inputNumbers[secondIndex] == target) {
                    return new int[]{firstIndex, secondIndex};
                }
                secondIndex++;
            }
            // if no match move to the next element in the array
            firstIndex++;
        }
        return new int[]{};
    }
}
