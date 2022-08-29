package slidingwindow;


/**
 * HashArray will keep track of character count of first string and compare it with chars from second string.
 * Chars from first string are counted as positives (+1) and chars from second string are counted as negatives (-1)
 * If there's a match the array should balance out and only contain 0s
 */
public class PermutationInStringHashArray {

    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;

        int[] arr = initHashArray(s1);

        int left = 0;
        int right = initRight(s1, s2, arr);

        if (isEmpty(arr)) return true;

        while (right < s2.length()) {
            arr[getCharIndex(s2.charAt(left))]++;

            left++;
            right++;

            if (right < s2.length()) arr[getCharIndex(s2.charAt(right))]--;
            if (isEmpty(arr)) return true;
        }
        return isEmpty(arr);
    }

    /*
     * get right pointer position and start second string char count
     */
    private int initRight(String s1, String s2, int[] arr) {
        int right = 0;

        while (right < s1.length()) {
            arr[getCharIndex(s2.charAt(right))]--;
            right++;
        }

        // adjust index offset
        right--;
        return right;
    }

    // get character alphabet index i.e. a = 0; z = 25
    private int getCharIndex(char c) {
        return c - 'a';
    }

    private int[] initHashArray(String s1) {
        int[] arr = new int[26];

        //add the values to the hash array
        for (int i = 0; i < s1.length(); i++) {
            arr[getCharIndex(s1.charAt(i))]++;
        }

        return arr;
    }

    /*
     * Check if array has any non 0 elements
     */
    public boolean isEmpty(int[] arr) {
        for (int i : arr) {
            if (i != 0) return false;
        }
        return true;
    }

}
