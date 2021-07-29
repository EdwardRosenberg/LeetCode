package twosum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TwoSumTest {

    @Test
    void should_return_expected_indexes() {
        // arrange
        int[] input = {2, 7, 11, 15};
        int target = 9;
        int[] expectedIndexes = new int[]{0, 1};

        // act
        int[] actualIndexes = new TwoSum().twoSum(input, target);

        // assert
        assertArrayEquals(expectedIndexes, actualIndexes);
    }

    @Test
    void should_return_empty_array_if_target_not_matched() {
        // arrange
        int[] input = {2, 7, 11, 15};
        int target = 10;
        int[] expectedIndexes = new int[]{};

        //act
        int[] actualIndexes = new TwoSum().twoSum(input, target);

        // assert
        assertArrayEquals(expectedIndexes, actualIndexes);
    }
}