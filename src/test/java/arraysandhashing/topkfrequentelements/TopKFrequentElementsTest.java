package arraysandhashing.topkfrequentelements;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TopKFrequentElementsTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps TopKFrequentElementsSorted (canonical, comparator sort) and
    // TopKFrequentElementsBucket (bucket sort by frequency) in sync without
    // duplicating the whole test file per variant.
    private final List<TopKFrequentCalculator> implementations =
            List.of(new TopKFrequentElementsSorted(), new TopKFrequentElementsBucket());

    @Test
    void exampleOne() {
        // frequencies: 1->3, 2->2, 3->1 — distinct, so order is deterministic
        assertAllImplementations(new int[]{1, 2}, new int[]{1, 1, 1, 2, 2, 3}, 2);
    }

    @Test
    void singleElement() {
        assertAllImplementations(new int[]{1}, new int[]{1}, 1);
    }

    @Test
    void kSmallerThanDistinctCount() {
        assertAllImplementations(new int[]{4, 5}, new int[]{4, 4, 4, 5, 5, 6}, 2);
    }

    @Test
    void tiedFrequenciesReturnAllInAnyOrder() {
        // every number appears once, so k=3 must return all three, but the
        // problem doesn't guarantee an order among equal frequencies
        for (TopKFrequentCalculator implementation : implementations) {
            Set<Integer> actual = Arrays.stream(implementation.topKFrequent(new int[]{1, 2, 3}.clone(), 3))
                    .boxed().collect(Collectors.toSet());
            assertEquals(Set.of(1, 2, 3), actual, implementation.getClass().getSimpleName() + " disagreed");
        }
    }

    @Test
    void largerInput() {
        int[] nums = {7, 7, 7, 7, 8, 8, 8, 9, 9, 10};
        assertAllImplementations(new int[]{7, 8, 9}, nums, 3);
    }

    private void assertAllImplementations(int[] expected, int[] nums, int k) {
        for (TopKFrequentCalculator implementation : implementations) {
            // Clone per call: TopKFrequentElementsBucket sorts its input in
            // place, so implementations sharing the same array reference
            // would otherwise see a mutated array on their turn.
            int[] input = nums.clone();
            assertArrayEquals(expected, implementation.topKFrequent(input, k),
                    implementation.getClass().getSimpleName() + " disagreed for input " + Arrays.toString(nums));
        }
    }
}
