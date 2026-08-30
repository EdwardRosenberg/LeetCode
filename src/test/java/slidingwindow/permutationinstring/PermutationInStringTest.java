package slidingwindow.permutationinstring;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PermutationInStringTest {

    // All implementations must agree on every scenario — looping over them
    // here keeps PermutationInString (canonical, two-map equals),
    // PermutationInStringHashArray (net-count array), and
    // PermutationInStringOptimized (running match count) in sync without
    // duplicating the whole test file per variant.
    private final List<PermutationChecker> implementations = List.of(
            new PermutationInString(),
            new PermutationInStringHashArray(),
            new PermutationInStringOptimized());

    @Test
    void exampleOne() {
        assertAllImplementations(true, "ab", "eidbaooo");
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(false, "ab", "eidboaoo");
    }

    @Test
    void patternLongerThanText() {
        assertAllImplementations(false, "abcd", "ab");
    }

    @Test
    void identicalStrings() {
        assertAllImplementations(true, "abc", "abc");
    }

    @Test
    void singleCharacterMatch() {
        assertAllImplementations(true, "a", "a");
    }

    @Test
    void singleCharacterNoMatch() {
        assertAllImplementations(false, "a", "b");
    }

    @Test
    void permutationAtVeryEndOfText() {
        // the matching window is the last possible one — checks the loop's
        // boundary condition doesn't stop one slide short of the end
        assertAllImplementations(true, "ab", "xxxxba");
    }

    @Test
    void largerInput() {
        // a shuffled permutation of a 10-char pattern embedded between
        // 30-character blocks of an unrelated filler character
        String pattern = "abcdefghij";
        String text = "z".repeat(30) + "bfgajehcid" + "z".repeat(30);
        assertAllImplementations(true, pattern, text);
    }

    private void assertAllImplementations(boolean expected, String pattern, String text) {
        for (PermutationChecker implementation : implementations) {
            assertEquals(expected, implementation.checkInclusion(pattern, text),
                    implementation.getClass().getSimpleName() + " disagreed for pattern=\"" + pattern + "\", text=\"" + text + "\"");
        }
    }
}
