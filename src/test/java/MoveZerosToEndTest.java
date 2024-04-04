import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MoveZerosToEndTest {
    @Test
    public void testMoveZeros() {
        MoveZerosToEnd mover = new MoveZerosToEnd();

        int[] nums = {0, 1, 0, 3, 12};
        mover.moveZeros(nums);
        assertArrayEquals(new int[]{1, 3, 12, 0, 0}, nums);
    }

    @Test
    public void testMoveZerosWithNoZeros() {
        MoveZerosToEnd mover = new MoveZerosToEnd();

        int[] nums = {1, 2, 3, 4};
        mover.moveZeros(nums);
        assertArrayEquals(new int[]{1, 2, 3, 4}, nums);
    }

    @Test
    public void testMoveZerosWithAllZeros() {
        MoveZerosToEnd mover = new MoveZerosToEnd();

        int[] nums = {0, 0, 0, 0};
        mover.moveZeros(nums);
        assertArrayEquals(new int[]{0, 0, 0, 0}, nums);
    }

    @Test
    public void testMoveZerosWithEmptyArray() {
        MoveZerosToEnd mover = new MoveZerosToEnd();

        int[] nums = {};
        mover.moveZeros(nums);
        assertArrayEquals(new int[]{}, nums);
    }

    @Test
    public void testMoveZerosWithNullArray() {
        MoveZerosToEnd mover = new MoveZerosToEnd();

        int[] nums = null;
        mover.moveZeros(nums);
        assertArrayEquals(null, nums);
    }
}