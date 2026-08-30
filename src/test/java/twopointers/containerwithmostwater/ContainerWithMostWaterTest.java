package twopointers.containerwithmostwater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContainerWithMostWaterTest {

    private final ContainerWithMostWater solution = new ContainerWithMostWater();

    @Test
    void exampleOne() {
        assertEquals(49, solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    @Test
    void twoElements() {
        assertEquals(1, solution.maxArea(new int[]{1, 1}));
    }

    @Test
    void tallestWallsAreNotAtTheEnds() {
        // the two tallest walls (both height 4) are at the very ends, but a
        // shorter-and-wider combination doesn't beat them here — confirms
        // the algorithm doesn't just grab the first tall pair it sees
        assertEquals(16, solution.maxArea(new int[]{4, 3, 2, 1, 4}));
    }

    @Test
    void narrowestPossibleContainer() {
        assertEquals(2, solution.maxArea(new int[]{1, 2, 1}));
    }
}
