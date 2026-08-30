package twopointers.trappingrainwater;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrappingRainwaterTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps TrappingRainwater (canonical, prefix/suffix arrays) and
    // TrappingRainwaterTwoPointers (O(1) space) in sync without duplicating
    // the whole test file per variant.
    private final List<RainTrapper> implementations =
            List.of(new TrappingRainwater(), new TrappingRainwaterTwoPointers());

    @Test
    void exampleOne() {
        assertAllImplementations(6, new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(9, new int[]{4, 2, 0, 3, 2, 5});
    }

    @Test
    void emptyInput() {
        assertAllImplementations(0, new int[]{});
    }

    @Test
    void singleBar() {
        assertAllImplementations(0, new int[]{5});
    }

    @Test
    void flatGroundTrapsNothing() {
        assertAllImplementations(0, new int[]{3, 3, 3});
    }

    @Test
    void strictlyDecreasingTrapsNothing() {
        // no wall to the right is ever taller, so nothing can pool anywhere
        assertAllImplementations(0, new int[]{5, 4, 3, 2, 1});
    }

    @Test
    void singleDip() {
        assertAllImplementations(1, new int[]{5, 4, 1, 2});
    }

    private void assertAllImplementations(int expected, int[] height) {
        for (RainTrapper implementation : implementations) {
            assertEquals(expected, implementation.trap(height),
                    implementation.getClass().getSimpleName() + " disagreed");
        }
    }
}
