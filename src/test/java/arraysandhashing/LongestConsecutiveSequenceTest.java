package arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestConsecutiveSequenceTest {

    // All implementations must agree on every scenario — looping over them
    // here keeps LongestConsecutiveSequenceRecursion (canonical, O(n)),
    // LongestConsecutiveSequenceHashSet (O(n + range)), and
    // LongestConsecutiveSequenceSorted (O(n log n)) in sync without
    // duplicating the whole test file per variant.
    private final List<LongestConsecutiveSequence> implementations = List.of(
            new LongestConsecutiveSequenceRecursion(),
            new LongestConsecutiveSequenceHashSet(),
            new LongestConsecutiveSequenceSorted());

    @Test
    void exampleOne() {
        assertAllImplementations(4, new int[]{100, 4, 200, 1, 3, 2});
    }

    @Test
    void emptyInput() {
        assertAllImplementations(0, new int[]{});
    }

    @Test
    void singleElement() {
        assertAllImplementations(1, new int[]{7});
    }

    @Test
    void duplicateNumbersDontInflateTheStreak() {
        // the extra '3' shouldn't count as two separate members of the streak
        assertAllImplementations(4, new int[]{100, 4, 200, 1, 3, 3, 2});
    }

    @Test
    void noConsecutiveNumbers() {
        assertAllImplementations(1, new int[]{10, 50, 100});
    }

    @Test
    void largerInput() {
        int[] nums = {9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 2, 6};
        assertAllImplementations(11, nums);
    }

    private void assertAllImplementations(int expected, int[] nums) {
        for (LongestConsecutiveSequence implementation : implementations) {
            // Clone per call: LongestConsecutiveSequenceSorted sorts its
            // input in place, so implementations sharing the same array
            // reference would otherwise see a mutated array on their turn.
            int[] input = nums.clone();
            assertEquals(expected, implementation.longestConsecutive(input),
                    implementation.getClass().getSimpleName() + " disagreed for input " + java.util.Arrays.toString(nums));
        }
    }
}
