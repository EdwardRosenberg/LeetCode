package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarFleetTest {

    @Test
    void carFleetTest() {
        assertEquals(3, new CarFleet().carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
        assertEquals(1, new CarFleet().carFleet(12, new int[]{3}, new int[]{3}));
        assertEquals(1, new CarFleet().carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));
        assertEquals(2, new CarFleet().carFleet(10, new int[]{6,8}, new int[]{3,2}));
    }
}