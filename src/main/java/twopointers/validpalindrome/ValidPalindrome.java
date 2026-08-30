package twopointers.validpalindrome;

/**
 * LeetCode 125 — Valid Palindrome (Easy)
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Pattern: Two Pointers
 * Cue: "ignore non-alphanumeric characters, compare case-insensitively front-to-back"
 *
 * Approach: Build the filtered (letters/digits only), lowercased string twice
 * — once reading forward, once reading backward — and compare the two.
 *
 * Alternative: the classic O(1)-extra-space version walks two pointers
 * inward from both ends of the original string, skipping non-alphanumeric
 * characters on each side and comparing characters directly, with no
 * auxiliary string built at all.
 *
 * Time: O(n) — two passes to build the strings, one comparison.
 * Space: O(n) for the two filtered strings.
 *
 * Variants: ValidPalindromeSinglePass.java (single pass, builds two
 * LinkedLists instead of two strings)
 */
public class ValidPalindrome implements PalindromeChecker {

    @Override
    public boolean isPalindrome(String s) {

        StringBuilder forward = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                forward.append(c);
            }
        }
        String filteredForward = forward.toString().toLowerCase();

        StringBuilder backward = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                backward.append(c);
            }
        }
        String filteredBackward = backward.toString().toLowerCase();

        return filteredForward.equals(filteredBackward);
    }
}
