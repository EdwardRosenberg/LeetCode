package twopointers.validpalindrome;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidPalindromeTest {

    private final List<PalindromeChecker> implementations =
            List.of(new ValidPalindrome(), new ValidPalindromeSinglePass());

    @Test
    void exampleOne() {
        assertAllImplementations(true, "A man, a plan, a canal: Panama");
    }

    @Test
    void exampleTwo() {
        assertAllImplementations(false, "race a car");
    }

    @Test
    void emptyStringAfterFiltering() {
        assertAllImplementations(true, " ");
    }

    @Test
    void emptyInput() {
        assertAllImplementations(true, "");
    }

    @Test
    void singleCharacter() {
        assertAllImplementations(true, "a");
    }

    @Test
    void digitsAndLettersMixed() {
        // regression case: digit-only palindrome check must not fall
        // through the alphanumeric filter
        assertAllImplementations(false, "0P");
    }

    @Test
    void allNonAlphanumericIsPalindrome() {
        assertAllImplementations(true, ",.!?;:");
    }

    private void assertAllImplementations(boolean expected, String input) {
        for (PalindromeChecker implementation : implementations) {
            String message = implementation.getClass().getSimpleName() + " disagreed";
            if (expected) {
                assertTrue(implementation.isPalindrome(input), message);
            } else {
                assertFalse(implementation.isPalindrome(input), message);
            }
        }
    }
}
