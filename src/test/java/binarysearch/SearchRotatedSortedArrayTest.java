package binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchRotatedSortedArrayTest {

    @Test
    void search() {
        assertEquals(4, new SearchRotatedSortedArray().search(new int[]{4,5,6,7,0,1,2}, 0));
        assertEquals(-1, new SearchRotatedSortedArray().search(new int[]{4,5,6,7,0,1,2}, 3));
        assertEquals(-1, new SearchRotatedSortedArray().search(new int[]{1}, 0));
        assertEquals(2, new SearchRotatedSortedArray().search(new int[]{5, 1198605, 3}, 3));
        assertEquals(0, new SearchRotatedSortedArray().search(new int[]{5, 1, 3}, 5));
        assertEquals(1, new SearchRotatedSortedArray().search(new int[]{1, 3}, 3));
        assertEquals(1, new SearchRotatedSortedArray().search(new int[]{3, 1}, 1));
    }
}