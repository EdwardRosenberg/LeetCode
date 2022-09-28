package binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumRotatedSortedArrayTest {

    @Test
    void findMin() {
        assertEquals(1, new MinimumRotatedSortedArray().findMin(new int[]{3, 4, 5, 1, 2}));
        assertEquals(0, new MinimumRotatedSortedArray().findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        assertEquals(11, new MinimumRotatedSortedArray().findMin(new int[]{11, 13, 15, 17}));
        assertEquals(1, new MinimumRotatedSortedArray().findMin(new int[]{5,1,2,3,4}));

    }
}