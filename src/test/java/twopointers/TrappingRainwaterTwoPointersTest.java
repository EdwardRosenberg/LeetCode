package twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrappingRainwaterTwoPointersTest {

    @Test
    void trap() {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        assertEquals(6, new TrappingRainwaterTwoPointers().trap(height));

        int[] height2 = {4,2,0,3,2,5};
        assertEquals(9, new TrappingRainwaterTwoPointers().trap(height2));
    }
}