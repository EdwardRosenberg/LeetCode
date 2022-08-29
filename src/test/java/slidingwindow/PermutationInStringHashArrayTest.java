package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermutationInStringHashArrayTest {

    @Test
    void checkInclusion() {
        assertTrue(new PermutationInStringHashArray().checkInclusion("ab", "eidbaooo"));
        assertFalse(new PermutationInStringHashArray().checkInclusion("ab", "eidboaoo"));
        assertTrue(new PermutationInStringHashArray().checkInclusion("", ""));
        assertTrue(new PermutationInStringHashArray().checkInclusion("adc", "dcda"));
        assertFalse(new PermutationInStringHashArray().checkInclusion("ab", "a"));
    }
}