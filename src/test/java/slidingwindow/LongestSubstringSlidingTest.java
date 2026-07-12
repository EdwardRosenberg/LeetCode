package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringSlidingTest {

    private final LongestSubstringSliding solution = new LongestSubstringSliding();

    @Test
    void exampleOne() {
        assertEquals(3, solution.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    void exampleTwo() {
        assertEquals(1, solution.lengthOfLongestSubstring("bbbbb"));
    }

    @Test
    void exampleThree() {
        assertEquals(3, solution.lengthOfLongestSubstring("pwwkew"));
    }

    @Test
    void emptyInput() {
        assertEquals(0, solution.lengthOfLongestSubstring(""));
    }

    @Test
    void singleCharacter() {
        assertEquals(1, solution.lengthOfLongestSubstring(" "));
    }

    @Test
    void allDuplicates() {
        assertEquals(1, solution.lengthOfLongestSubstring("aa"));
    }

    @Test
    void duplicateNotAtWindowStart() {
        // "dvdf": the repeated 'd' is one step into the window, not at its
        // very start — left has to jump from 0 to 1, not skip the whole window.
        assertEquals(3, solution.lengthOfLongestSubstring("dvdf"));
    }

    @Test
    void mixedCharacterTypes() {
        assertEquals(3, solution.lengthOfLongestSubstring(" _3"));
    }

    @Test
    void largerInputNoRepeatsUntilItLoops() {
        // Alphabet twice back-to-back: window grows to the full 26 letters,
        // then the repeat at index 26 caps it there for the rest of the string.
        String alphabetTwice = "abcdefghijklmnopqrstuvwxyz".repeat(2);
        assertEquals(26, solution.lengthOfLongestSubstring(alphabetTwice));
    }
}
