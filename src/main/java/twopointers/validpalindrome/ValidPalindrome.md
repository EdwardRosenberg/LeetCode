# Valid Palindrome

- **LeetCode:** [125 — Valid Palindrome](https://leetcode.com/problems/valid-palindrome/) (Easy)
- **Pattern:** Two Pointers
- **Cue:** "ignore non-alphanumeric characters, compare case-insensitively front-to-back"

Variants: [ValidPalindromeSinglePass](ValidPalindromeSinglePass.java) (single pass, builds two LinkedLists instead of two strings)

## Approach

Build the filtered (letters/digits only), lowercased string twice — once reading forward, once reading backward — and compare the two.

> **Alternative:** the classic O(1)-extra-space version walks two pointers inward from both ends of the original string, skipping non-alphanumeric characters on each side and comparing characters directly, with no auxiliary string built at all. Neither variant on disk uses that approach — both build full auxiliary structures — so if the goal is to drill the "true" two-pointer version of this problem, that's still worth practicing separately.

- **Time:** O(n) — two passes to build the strings, one comparison.
- **Space:** O(n) for the two filtered strings.

## Why it works

A string is a palindrome exactly when it reads the same forwards and backwards. Building `filteredForward` by scanning left-to-right and `filteredBackward` by scanning right-to-left, both applying the same filter (alphanumeric only) and the same case normalization, produces two strings that are equal if and only if the filtered sequence is a palindrome — comparing them directly sidesteps ever reversing a string explicitly.

## Watch out

Skipping punctuation/spaces has to happen *before* the comparison, not just before lowercasing — filtering only the forward pass (or filtering inconsistently between the two passes) would silently break on inputs like `"0P"`, where every character matters and there's no punctuation to hide the bug.

## Code

```java
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
```
