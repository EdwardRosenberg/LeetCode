package twopointers.threesum;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreeSumTest {

    private final ThreeSum solution = new ThreeSum();

    @Test
    void exampleOne() {
        List<List<Integer>> expected = List.of(
                List.of(-1, -1, 2),
                List.of(-1, 0, 1)
        );
        assertEquals(expected, solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    @Test
    void noTripleSumsToZero() {
        assertEquals(List.of(), solution.threeSum(new int[]{0, 1, 1}));
    }

    @Test
    void allZeros() {
        assertEquals(List.of(List.of(0, 0, 0)), solution.threeSum(new int[]{0, 0, 0}));
    }

    @Test
    void fewerThanThreeElements() {
        assertEquals(List.of(), solution.threeSum(new int[]{1, -1}));
    }

    @Test
    void duplicateValuesDontProduceDuplicateTriples() {
        // multiple repeated values (-4, -2, 0) that could each combine with
        // several partners — checks the fixedIndex and left dedup both work
        int[] nums = {-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        List<List<Integer>> expected = List.of(
                List.of(-5, 1, 4),
                List.of(-4, 0, 4),
                List.of(-4, 1, 3),
                List.of(-2, -2, 4),
                List.of(-2, 1, 1),
                List.of(0, 0, 0)
        );
        assertEquals(expected, solution.threeSum(nums));
    }
}
