package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PermutationInStringTest {

    @Test
    void checkInclusion() {
        assertTrue(new PermutationInString().checkInclusion("ab", "eidbaooo"));
        assertFalse(new PermutationInString().checkInclusion("ab", "eidboaoo"));
        assertTrue(new PermutationInString().checkInclusion("", ""));
        assertTrue(new PermutationInString().checkInclusion("adc", "dcda"));
        assertFalse(new PermutationInString().checkInclusion("ab", "a"));
    }
}