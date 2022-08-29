package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinimumWindowSubstringTest {

    @Test
    void minWindow() {
        assertEquals("BANC", new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC") );
        assertEquals("a", new MinimumWindowSubstring().minWindow("a", "a") );
        assertEquals("a", new MinimumWindowSubstring().minWindow("ab", "a") );
        assertEquals("", new MinimumWindowSubstring().minWindow("a", "b") );
        assertEquals("", new MinimumWindowSubstring().minWindow("", "") );
        assertEquals("ab", new MinimumWindowSubstring().minWindow("abc", "ab") );
        assertEquals("", new MinimumWindowSubstring().minWindow("babb", "baba"));
    }
}