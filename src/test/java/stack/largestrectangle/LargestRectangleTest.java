package stack.largestrectangle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LargestRectangleTest {

    private final LargestRectangle largestRectangle = new LargestRectangle();

    @Test
    void exampleOne() {
        assertEquals(10, largestRectangle.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    @Test
    void exampleTwo() {
        assertEquals(4, largestRectangle.largestRectangleArea(new int[]{2, 4}));
    }

    @Test
    void emptyInput() {
        assertEquals(0, largestRectangle.largestRectangleArea(new int[]{}));
    }

    @Test
    void singleBar() {
        assertEquals(5, largestRectangle.largestRectangleArea(new int[]{5}));
    }

    @Test
    void allZeroHeight() {
        assertEquals(0, largestRectangle.largestRectangleArea(new int[]{0, 0, 0}));
    }

    @Test
    void flatHistogramUsesFullWidth() {
        assertEquals(20, largestRectangle.largestRectangleArea(new int[]{5, 5, 5, 5}));
    }

    @Test
    void dipInTheMiddleForcesShorterRunsToMerge() {
        // the 1 at index 1 forces both the leading 2 and the trailing 2 to
        // be treated as height-1 runs before the taller bars can resolve —
        // this is the case the two-stack start-index carry-forward exists for
        assertEquals(3, largestRectangle.largestRectangleArea(new int[]{2, 1, 2}));
    }

    @Test
    void strictlyDecreasingHeights() {
        assertEquals(9, largestRectangle.largestRectangleArea(new int[]{5, 4, 3, 2, 1}));
    }

    @Test
    void largerInputWithMultipleDips() {
        assertEquals(12, largestRectangle.largestRectangleArea(new int[]{6, 2, 5, 4, 5, 1, 6}));
    }
}
