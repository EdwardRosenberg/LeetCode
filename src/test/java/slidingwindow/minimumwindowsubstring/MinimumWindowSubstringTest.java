package slidingwindow.minimumwindowsubstring;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumWindowSubstringTest {

    private final MinimumWindowSubstring solution = new MinimumWindowSubstring();

    @Test
    void exampleOne() {
        assertEquals("BANC", solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    @Test
    void identicalStrings() {
        assertEquals("a", solution.minWindow("a", "a"));
    }

    @Test
    void patternIsSingleCharPrefix() {
        assertEquals("a", solution.minWindow("ab", "a"));
    }

    @Test
    void noValidWindowExists() {
        assertEquals("", solution.minWindow("a", "b"));
    }

    @Test
    void emptyInputs() {
        assertEquals("", solution.minWindow("", ""));
    }

    @Test
    void wholeTextIsTheAnswer() {
        assertEquals("ab", solution.minWindow("abc", "ab"));
    }

    @Test
    void patternNeedsMoreOfACharThanTextHas() {
        // "baba" needs two 'a's and two 'b's; "babb" only has one 'a'
        assertEquals("", solution.minWindow("babb", "baba"));
    }

    @Test
    void patternHasACharNotInText() {
        assertEquals("", solution.minWindow("abcd", "abcde"));
    }

    @Test
    void duplicateRequiredCharacter() {
        // pattern requires two 'a's; the shortest stretch of text containing
        // two a's (skipping the long run of unrelated filler chars) is "aa"
        assertEquals("aa", solution.minWindow("aaflslflsldkalskaaa", "aa"));
    }
}
