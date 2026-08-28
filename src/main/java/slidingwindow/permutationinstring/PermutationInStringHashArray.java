package slidingwindow.permutationinstring;

/**
 * LeetCode 567 — Permutation in String (Medium)
 * https://leetcode.com/problems/permutation-in-string/
 *
 * Pattern: Sliding Window
 * Cue: "does any substring of text contain the exact same character counts as pattern"
 *
 * Approach: A single int[26] array tracks the *net* difference between
 * pattern's letter counts and the current window's — pattern's letters
 * count as +1 each, the window's letters count as -1 each. The window
 * matches exactly when every slot in the array nets out to 0.
 *
 * Time: O(n * alphabet) — checking "is everything balanced" costs
 * O(alphabet), and that check runs once per window position. Same
 * complexity class as the canonical two-map version, just a leaner
 * (unboxed, fixed-size) data structure.
 * Space: O(alphabet) for the array.
 *
 * See PermutationInString.java for the canonical version, and
 * PermutationInStringOptimized.java for a genuinely O(n) version.
 */
public class PermutationInStringHashArray implements PermutationChecker {

    @Override
    public boolean checkInclusion(String pattern, String text) {
        if (text.length() < pattern.length()) return false;

        int[] netCharCounts = buildPatternCounts(pattern);

        int left = 0;
        int right = consumeInitialWindow(pattern, text, netCharCounts);

        if (isBalanced(netCharCounts)) return true;

        while (right < text.length()) {
            netCharCounts[charIndex(text.charAt(left))]++;

            left++;
            right++;

            if (right < text.length()) netCharCounts[charIndex(text.charAt(right))]--;
            if (isBalanced(netCharCounts)) return true;
        }
        return isBalanced(netCharCounts);
    }

    // Consumes the first window's worth of text characters, decrementing
    // their counts, and returns the resulting right-pointer position.
    private int consumeInitialWindow(String pattern, String text, int[] netCharCounts) {
        int right = 0;

        while (right < pattern.length()) {
            netCharCounts[charIndex(text.charAt(right))]--;
            right++;
        }

        right--;
        return right;
    }

    // a=0, z=25
    private int charIndex(char c) {
        return c - 'a';
    }

    private int[] buildPatternCounts(String pattern) {
        int[] counts = new int[26];
        for (int i = 0; i < pattern.length(); i++) {
            counts[charIndex(pattern.charAt(i))]++;
        }
        return counts;
    }

    private boolean isBalanced(int[] netCharCounts) {
        for (int count : netCharCounts) {
            if (count != 0) return false;
        }
        return true;
    }
}
