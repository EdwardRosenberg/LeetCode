package twopointers.validpalindrome;

import java.util.LinkedList;
import java.util.Objects;

/**
 * LeetCode 125 — Valid Palindrome (Easy)
 * https://leetcode.com/problems/valid-palindrome/
 *
 * Pattern: Two Pointers
 * Cue: "ignore non-alphanumeric characters, compare case-insensitively front-to-back"
 *
 * Approach: Single pass over the string. Each accepted (alphanumeric,
 * lowercased) character is pushed to the front of one list and the back of
 * another, so one list ends up reversed relative to the other. Equal lists
 * means the filtered string reads the same forwards and backwards.
 *
 * Time: O(n) — one pass to build both lists, one comparison.
 * Space: O(n) for the two lists.
 *
 * See ValidPalindrome.java for the canonical (two-pass) version.
 */
public class ValidPalindromeSinglePass implements PalindromeChecker {

    @Override
    public boolean isPalindrome(String s) {
        LinkedList<Character> reversed = new LinkedList<>();
        LinkedList<Character> forward = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                if (Character.isUpperCase(c)) {
                    c = Character.toLowerCase(c);
                }

                reversed.addFirst(c);
                forward.addLast(c);
            }
        }

        return Objects.equals(reversed, forward);
    }
}
