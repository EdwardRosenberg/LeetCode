package stack.carfleet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarFleetTest {

    private final CarFleet carFleet = new CarFleet();

    @Test
    void exampleOne() {
        assertEquals(3, carFleet.carFleet(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}));
    }

    @Test
    void emptyInput() {
        assertEquals(0, carFleet.carFleet(5, new int[]{}, new int[]{}));
    }

    @Test
    void singleCar() {
        assertEquals(1, carFleet.carFleet(12, new int[]{3}, new int[]{3}));
    }

    @Test
    void allCarsMergeIntoOneFleet() {
        assertEquals(1, carFleet.carFleet(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}));
    }

    @Test
    void twoCarsNeverCatchUp() {
        assertEquals(2, carFleet.carFleet(10, new int[]{6, 8}, new int[]{3, 2}));
    }

    @Test
    void twoCarsArrivingAtExactlyTheSameTimeCountAsOneFleet() {
        // solo arrival times are equal (both 1.0), not just close — the
        // merge condition is "solo time <= fleet ahead's time", so an exact
        // tie must merge, not split into two fleets
        assertEquals(1, carFleet.carFleet(30, new int[]{20, 22}, new int[]{10, 8}));
    }
}
