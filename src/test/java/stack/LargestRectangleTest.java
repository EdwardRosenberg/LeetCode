package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestRectangleTest {

    @Test
    void largestRectangleArea() {
        assertEquals(10, new LargestRectangle().largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        assertEquals(4, new LargestRectangle().largestRectangleArea(new int[]{2, 4}));
        assertEquals(3, new LargestRectangle().largestRectangleArea(new int[]{2,1,2}));
    }
}