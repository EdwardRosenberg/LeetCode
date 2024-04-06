import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveDuplicatesFromArrayTest {

    @Test
    void testNoDuplicates() {
        int[] nums = {1, 2, 3, 4, 5};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(5, actualLength);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, copyArray(nums, actualLength));
    }

    @Test
    void testDuplicatesAtBeginning() {
        int[] nums = {1, 1, 2, 3, 4, 5};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(5, actualLength);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, copyArray(nums, actualLength));
    }

    @Test
    void testDuplicatesAtEnd() {
        int[] nums = {1, 2, 3, 4, 5, 5};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(5, actualLength);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, copyArray(nums, actualLength));
    }

    @Test
    void testDuplicatesInMiddle() {
        int[] nums = {1, 2, 2, 3, 4, 5, 5};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(5, actualLength);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, copyArray(nums, actualLength));
    }

    @Test
    void testAllDuplicates() {
        int[] nums = {1, 1, 1, 1, 1};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(1, actualLength);
        assertArrayEquals(new int[]{1}, copyArray(nums, actualLength));
    }

    @Test
    void testEmptyArray() {
        int[] nums = {};
        RemoveDuplicatesFromArray remover = new RemoveDuplicatesFromArray();
        int actualLength = remover.removeDuplicates(nums);
        assertEquals(0, actualLength);
        assertArrayEquals(new int[]{}, copyArray(nums, actualLength));
    }

    // Helper method to copy array up to the specified length
    private int[] copyArray(int[] nums, int length) {
        int[] copy = new int[length];
        System.arraycopy(nums, 0, copy, 0, length);
        return copy;
    }
}