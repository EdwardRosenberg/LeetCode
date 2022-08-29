package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringTest {

    @Test
    void lengthOfLongestSubstring() {
        assertEquals(3, new LongestSubstring().lengthOfLongestSubstring("abcabcbb"));

        assertEquals(1, new LongestSubstring().lengthOfLongestSubstring("bbbbb"));

        assertEquals(3, new LongestSubstring().lengthOfLongestSubstring("pwwkew"));

        assertEquals(1, new LongestSubstring().lengthOfLongestSubstring(" "));

        assertEquals(3, new LongestSubstring().lengthOfLongestSubstring(" _3"));

        assertEquals(3, new LongestSubstring().lengthOfLongestSubstring("dvdf"));
    }
}