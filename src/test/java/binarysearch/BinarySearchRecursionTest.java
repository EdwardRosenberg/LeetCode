package binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchRecursionTest {

    @Test
    void search() {
        assertEquals(4, new BinarySearchRecursion().search(new int[]{-1, 0, 3, 5, 9, 12}, 9));
        assertEquals(-1, new BinarySearchRecursion().search(new int[]{-1, 0, 3, 5, 8, 12}, 9));
        assertEquals(0, new BinarySearchRecursion().search(new int[]{5}, 5));
        assertEquals(-1, new BinarySearchRecursion().search(new int[]{-5}, 5));
        assertEquals(1, new BinarySearchRecursion().search(new int[]{2, 5}, 5));
    }
}