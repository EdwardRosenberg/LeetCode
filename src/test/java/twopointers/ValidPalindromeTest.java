package twopointers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidPalindromeTest {

    ValidPalindromeSinglePass validPalindrome = new ValidPalindromeSinglePass();

    @Test
    void isPalindrome() {
        assertTrue(validPalindrome.isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(validPalindrome.isPalindrome("0P"));
    }
}