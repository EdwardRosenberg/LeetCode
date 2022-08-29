package twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainerWithMostWaterTest {

    @Test
    void maxArea() {
        assertEquals(49, new ContainerWithMostWater().maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
        assertEquals(1, new ContainerWithMostWater().maxArea(new int[]{1,1}));
    }
}