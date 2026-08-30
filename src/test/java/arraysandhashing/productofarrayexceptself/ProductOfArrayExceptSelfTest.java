package arraysandhashing.productofarrayexceptself;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductOfArrayExceptSelfTest {

    // All implementations must agree on every scenario — looping over them
    // here keeps ProductOfArrayExceptSelfOptimal (canonical, prefix/postfix),
    // ProductOfArrayExceptSelf (brute force), and
    // ProductOfArrayExceptSelfDivision (division, with zero-handling) in
    // sync without duplicating the whole test file per variant.
    private final List<ProductExceptSelfCalculator> implementations = List.of(
            new ProductOfArrayExceptSelfOptimal(),
            new ProductOfArrayExceptSelfBruteForce(),
            new ProductOfArrayExceptSelfDivision());

    @Test
    void exampleOne() {
        assertAllImplementations(new int[]{24, 12, 8, 6}, new int[]{1, 2, 3, 4});
    }

    @Test
    void twoElements() {
        assertAllImplementations(new int[]{2, 1}, new int[]{1, 2});
    }

    @Test
    void containsAZero() {
        // every index except the zero's own gets 0, since the zero is included
        // in their product; the zero's own index gets the product of the rest
        assertAllImplementations(new int[]{0, 0, 8, 0}, new int[]{1, 2, 0, 4});
    }

    @Test
    void containsMultipleZeros() {
        // every index's remaining product still includes at least one of the
        // other zeros, so every answer is 0 — this is the case the division
        // approach's naive form (0/0 at each zero's own index) breaks on.
        assertAllImplementations(new int[]{0, 0, 0, 0}, new int[]{0, 4, 0, 2});
    }

    @Test
    void singleElementArrayThatIsZero() {
        // excluding the only element leaves an empty product, which is 1 by
        // convention — not a real LeetCode input (n >= 2 there), but a good
        // boundary check for the zero-handling logic itself
        assertAllImplementations(new int[]{1}, new int[]{0});
    }

    @Test
    void negativeNumbers() {
        assertAllImplementations(new int[]{-6, 3, -2}, new int[]{-1, 2, -3});
    }

    @Test
    void largerInput() {
        int[] nums = {1, 2, 3, 4, 5, 6};
        assertAllImplementations(new int[]{720, 360, 240, 180, 144, 120}, nums);
    }

    private void assertAllImplementations(int[] expected, int[] nums) {
        for (ProductExceptSelfCalculator implementation : implementations) {
            assertArrayEquals(expected, implementation.productExceptSelf(nums),
                    implementation.getClass().getSimpleName() + " disagreed for input " + Arrays.toString(nums));
        }
    }
}
