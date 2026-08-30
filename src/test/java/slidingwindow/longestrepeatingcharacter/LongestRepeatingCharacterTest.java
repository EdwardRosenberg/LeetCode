package slidingwindow.longestrepeatingcharacter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestRepeatingCharacterTest {

    // Both implementations must agree on every scenario — looping over them
    // here keeps LongestRepeatingCharacter (canonical, rescans for the max
    // frequency) and LongestRepeatingCharacterOptimized (tracks it
    // incrementally) in sync without duplicating the whole test file per
    // variant.
    private final List<CharacterReplacer> implementations =
            List.of(new LongestRepeatingCharacter(), new LongestRepeatingCharacterOptimized());

    @Test
    void exampleOne() {
        assertAllImplementations(4, "ABAB", 2);
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(4, "AABABBA", 1);
    }

    @Test
    void singleCharacter() {
        assertAllImplementations(1, "A", 0);
    }

    @Test
    void zeroReplacementsKeepsExistingRun() {
        assertAllImplementations(4, "AAAA", 0);
    }

    @Test
    void allDistinctCharactersCapsWindowAtReplacementsPlusOne() {
        // every character is unique, so with 1 replacement the best you can
        // ever do is 1 original character + 1 replaced neighbor
        assertAllImplementations(2, "ABCDE", 1);
    }

    @Test
    void largerInput() {
        String input = "AAABBBCCC".repeat(5);
        assertAllImplementations(6, input, 3);
    }

    private void assertAllImplementations(int expected, String input, int maxReplacements) {
        for (CharacterReplacer implementation : implementations) {
            assertEquals(expected, implementation.characterReplacement(input, maxReplacements),
                    implementation.getClass().getSimpleName() + " disagreed for input \"" + input + "\", k=" + maxReplacements);
        }
    }
}
