package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermutationInStringOptimizedTest {

    @Test
    void checkInclusion() {
        assertTrue(new PermutationInStringOptimized().checkInclusion("ab", "eidbaooo"));
        assertFalse(new PermutationInStringOptimized().checkInclusion("ab", "eidboaoo"));
        assertTrue(new PermutationInStringOptimized().checkInclusion("", ""));
        assertTrue(new PermutationInStringOptimized().checkInclusion("adc", "dcda"));
        assertFalse(new PermutationInStringOptimized().checkInclusion("ab", "a"));
        assertTrue(new PermutationInStringOptimized().checkInclusion("abc", "bbbca"));
    }
}