package twosum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TwoSumTest {

    @Test
    void should_return_expected_indexes() {
        int[] input = {2, 7, 11, 15};
        int target = 9;

        int[] expectedIndexes = new int[]{0, 1};
        int[] actualIndexes = new TwoSum().twoSum(input, target);
        assertArrayEquals(expectedIndexes, actualIndexes);
    }
}