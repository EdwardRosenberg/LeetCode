package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringSlidingTest {

    @Test
    void lengthOfLongestSubstring() {
        assertEquals(3, new LongestSubstringSliding().lengthOfLongestSubstring("abcabcbb"));

        assertEquals(1, new LongestSubstringSliding().lengthOfLongestSubstring("bbbbb"));

        assertEquals(3, new LongestSubstringSliding().lengthOfLongestSubstring("pwwkew"));

        assertEquals(1, new LongestSubstringSliding().lengthOfLongestSubstring(" "));

        assertEquals(3, new LongestSubstringSliding().lengthOfLongestSubstring(" _3"));

        assertEquals(3, new LongestSubstringSliding().lengthOfLongestSubstring("dvdf"));

        assertEquals(1, new LongestSubstringSliding().lengthOfLongestSubstring("aa"));
    }
}