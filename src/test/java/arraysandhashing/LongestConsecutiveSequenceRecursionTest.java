package arraysandhashing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestConsecutiveSequenceRecursionTest {

    @Test
    void longestConsecutive() {
        LongestConsecutiveSequenceRecursion longestConsecutiveSequence = new LongestConsecutiveSequenceRecursion();

        int[] nums = {100,4,200,1,3,2};
        int result = longestConsecutiveSequence.longestConsecutive(nums);
        assertEquals(4, result);
    }

    @Test
    void longestConsecutiveEmptyArray() {
        LongestConsecutiveSequenceRecursion longestConsecutiveSequence = new LongestConsecutiveSequenceRecursion();

        int[] nums = {};
        int result = longestConsecutiveSequence.longestConsecutive(nums);
        assertEquals(0, result);
    }

    @Test
    void longestConsecutiveDuplicateNumbers() {
        LongestConsecutiveSequenceRecursion longestConsecutiveSequence = new LongestConsecutiveSequenceRecursion();

        int[] nums = {100,4,200,1,3,3,2};
        int result = longestConsecutiveSequence.longestConsecutive(nums);
        assertEquals(4, result);
    }
}