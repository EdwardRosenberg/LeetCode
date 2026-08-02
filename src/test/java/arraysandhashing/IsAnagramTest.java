package arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IsAnagramTest {

    // All implementations must agree on every scenario — looping over them
    // here keeps IsAnagram (HashMap counting), IsAnagramSorted, and
    // IsAnagramCounting in sync without duplicating the whole test file per
    // variant.
    private final List<AnagramChecker> implementations =
            List.of(new IsAnagram(), new IsAnagramSorted(), new IsAnagramCounting());

    @Test
    void exampleOne() {
        assertAllImplementations(true, "anagram", "nagaram");
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(false, "rat", "car");
    }

    @Test
    void emptyStrings() {
        assertAllImplementations(true, "", "");
    }

    @Test
    void differentLengths() {
        assertAllImplementations(false, "ab", "abc");
    }

    @Test
    void sameLengthDifferentLetters() {
        assertAllImplementations(false, "aabb", "aabc");
    }

    @Test
    void repeatedLettersMatterNotJustSet() {
        // "aacc" has the same *set* of letters as "abcc" but different counts —
        // a HashSet-of-characters check would wrongly say these are anagrams.
        assertAllImplementations(false, "aacc", "abcc");
    }

    @Test
    void largerInput() {
        assertAllImplementations(true, "thisisaverylongstring", "nsgirnsiaoryhlitvetsg");
    }

    private void assertAllImplementations(boolean expected, String word, String compareWord) {
        for (AnagramChecker checker : implementations) {
            assertEquals(expected, checker.isAnagram(word, compareWord),
                    checker.getClass().getSimpleName() + " disagreed on (\"" + word + "\", \"" + compareWord + "\")");
        }
    }
}
