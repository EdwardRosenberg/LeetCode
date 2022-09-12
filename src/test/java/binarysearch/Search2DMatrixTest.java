package binarysearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Search2DMatrixTest {

    @Test
    void searchMatrix() {
        assertTrue(new Search2DMatrix().searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 3));
        assertFalse(new Search2DMatrix().searchMatrix(new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}}, 13));
        assertTrue(new Search2DMatrix().searchMatrix(new int[][]{{1, 3}}, 3));
        assertTrue(new Search2DMatrix().searchMatrix(new int[][]{{3}, {10}, {23}}, 3));
        assertTrue(new Search2DMatrix().searchMatrix(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}}, 30));
    }
}