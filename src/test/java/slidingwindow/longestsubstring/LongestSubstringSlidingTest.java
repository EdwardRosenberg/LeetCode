package slidingwindow.longestsubstring;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSubstringSlidingTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps LongestSubstringSliding (canonical, two-pointer window) and
    // LongestSubstringBacktrack (rebuild-on-duplicate) in sync without
    // duplicating the whole test file per variant.
    private final List<LongestSubstringFinder> implementations =
            List.of(new LongestSubstringSliding(), new LongestSubstringBacktrack());

    @Test
    void exampleOne() {
        assertAllImplementations(3, "abcabcbb");
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(1, "bbbbb");
    }

    @Test
    void exampleThree() {
        assertAllImplementations(3, "pwwkew");
    }

    @Test
    void emptyInput() {
        assertAllImplementations(0, "");
    }

    @Test
    void singleCharacter() {
        assertAllImplementations(1, " ");
    }

    @Test
    void allDuplicates() {
        assertAllImplementations(1, "aa");
    }

    @Test
    void duplicateNotAtWindowStart() {
        // "dvdf": the repeated 'd' is one step into the window, not at its
        // very start — left has to jump from 0 to 1, not skip the whole window.
        assertAllImplementations(3, "dvdf");
    }

    @Test
    void mixedCharacterTypes() {
        assertAllImplementations(3, " _3");
    }

    @Test
    void largerInputNoRepeatsUntilItLoops() {
        // Alphabet twice back-to-back: window grows to the full 26 letters,
        // then the repeat at index 26 caps it there for the rest of the string.
        String alphabetTwice = "abcdefghijklmnopqrstuvwxyz".repeat(2);
        assertAllImplementations(26, alphabetTwice);
    }

    private void assertAllImplementations(int expected, String input) {
        for (LongestSubstringFinder implementation : implementations) {
            assertEquals(expected, implementation.lengthOfLongestSubstring(input),
                    implementation.getClass().getSimpleName() + " disagreed for input \"" + input + "\"");
        }
    }
}
