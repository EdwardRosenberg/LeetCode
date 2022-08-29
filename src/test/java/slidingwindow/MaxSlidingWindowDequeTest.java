package slidingwindow;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MaxSlidingWindowDequeTest {
    @Test
    void maxSlidingWindow() {
        assertTrue(Arrays.equals(new int[]{3, 3, 5, 5, 6, 7}, new MaxSlidingWindowDeque().maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
//        assertTrue(Arrays.equals(new int[]{1}, new MaxSlidingWindowDeque().maxSlidingWindow(new int[]{1}, 1)));
//        assertTrue(Arrays.equals(new int[]{1, -1}, new MaxSlidingWindowDeque().maxSlidingWindow(new int[]{1, -1}, 1)));
    }

}