package arraysandhashing.twosum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TwoSumHashMapTest {

    private final TwoSumHashMap solution = new TwoSumHashMap();

    @Test
    void exampleOne() {
        assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    @Test
    void answerNotAtStart() {
        assertArrayEquals(new int[]{1, 2}, solution.twoSum(new int[]{3, 2, 4}, 6));
    }

    @Test
    void twoElements() {
        assertArrayEquals(new int[]{0, 1}, solution.twoSum(new int[]{3, 3}, 6));
    }

    @Test
    void noMatchReturnsEmptyArray() {
        assertArrayEquals(new int[]{}, solution.twoSum(new int[]{1, 2, 3}, 100));
    }

    @Test
    void negativeNumbers() {
        assertArrayEquals(new int[]{0, 2}, solution.twoSum(new int[]{-3, 4, 3, 90}, 0));
    }

    @Test
    void largerInput() {
        int[] nums = {11, 15, 2, 7, 8, 3, 6, 5, 10, 1};
        assertArrayEquals(new int[]{2, 3}, solution.twoSum(nums, 9));
    }
}
