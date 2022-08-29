package movezeroes;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ZeroesTest {

    @Test
    void moveZeroes() {

        int[] nums = new int[]{0, 1, 0};
        int[] expected = new int[]{1, 0, 0};

        new ZeroesArray().moveZeroes(nums);

        assertTrue(Arrays.equals(expected, nums));
    }

    @Test
    void moveZeroes1() {

        int[] nums = new int[]{0, 1, 0, 3, 12};
        int[] expected = new int[]{1, 3, 12, 0, 0};

        new ZeroesArray().moveZeroes(nums);

        assertTrue(Arrays.equals(expected, nums));
    }

}