package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterOptimized {

    public int characterReplacement(String s, int k) {

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;
        int maxFreq = getMaxFreq(s, frequencyMap, right, 0);

        while (right < s.length()) {
            if (isWindowValid(left, right, maxFreq, k)) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                if (right < s.length()) {
                    maxFreq = getMaxFreq(s, frequencyMap, right, maxFreq);
                }
            } else {
                frequencyMap.compute(s.charAt(left), (key, val) -> val = val - 1);
                left++;
            }
        }

        return maxLength;
    }

    /*
     *  keep track of the most frequent character count - assume that max frequency is either current value or the most recently added character (from the right side)
     *  don't worry about decrementing max frequency as the window shrinks - math will still work out
     */
    private int getMaxFreq(String s, Map<Character, Integer> frequencyMap, int right, int maxFreq) {
        frequencyMap.putIfAbsent(s.charAt(right), 0);
        frequencyMap.compute(s.charAt(right), (key, val) -> val = val + 1);
        maxFreq = Math.max(maxFreq, frequencyMap.get(s.charAt(right)));
        return maxFreq;
    }

    private boolean isWindowValid(int left, int right, int maxFreq, int k) {
        return right - left - maxFreq + 1 <= k;
    }

}
