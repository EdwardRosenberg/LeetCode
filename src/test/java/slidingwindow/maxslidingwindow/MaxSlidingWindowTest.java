package slidingwindow.maxslidingwindow;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MaxSlidingWindowTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps MaxSlidingWindowDeque (canonical, genuinely O(n)) and
    // MaxSlidingWindowTreeMap (O(n log k)) in sync without duplicating the
    // whole test file per variant.
    private final List<MaxSlidingWindowSolver> implementations =
            List.of(new MaxSlidingWindowDeque(), new MaxSlidingWindowTreeMap());

    @Test
    void exampleOne() {
        assertAllImplementations(new int[]{3, 3, 5, 5, 6, 7}, new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
    }

    @Test
    void singleElement() {
        assertAllImplementations(new int[]{1}, new int[]{1}, 1);
    }

    @Test
    void windowSizeOne() {
        // window size 1 means every element is its own max — this is the
        // case that exercises "the max leaves the window" every single step
        assertAllImplementations(new int[]{1, -1}, new int[]{1, -1}, 1);
    }

    @Test
    void windowCoversEntireArray() {
        assertAllImplementations(new int[]{7}, new int[]{4, 2, 7, 1}, 4);
    }

    @Test
    void duplicateMaxValues() {
        assertAllImplementations(new int[]{5, 5, 5}, new int[]{5, 5, 5, 5}, 2);
    }

    @Test
    void maxLeavesWindowEachStep() {
        // strictly decreasing input: the current max exits the window on
        // every slide, forcing the next-largest still-in-window value to
        // take over each time
        assertAllImplementations(new int[]{9, 8, 7, 6}, new int[]{9, 8, 7, 6, 5}, 2);
    }

    @Test
    void largerInput() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7, 2, 8, -5, 9, 0, 4, 6, 1, 3, 9, 2, 7};
        int[] expected = {3, 3, 5, 5, 6, 7, 7, 8, 8, 9, 9, 9, 6, 6, 6, 9, 9, 9};
        assertAllImplementations(expected, nums, 3);
    }

    private void assertAllImplementations(int[] expected, int[] nums, int windowSize) {
        for (MaxSlidingWindowSolver implementation : implementations) {
            assertArrayEquals(expected, implementation.maxSlidingWindow(nums, windowSize),
                    implementation.getClass().getSimpleName() + " disagreed for windowSize=" + windowSize);
        }
    }
}
