package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacter {

    public int characterReplacement(String s, int k) {

        Map<Character, Integer> frequencyMap = new HashMap<>();
        int maxLength = 0;
        int left = 0;
        int right = 0;

        frequencyMap.computeIfAbsent(s.charAt(right), v -> 0);
        frequencyMap.compute(s.charAt(right), (key, val) -> val = val + 1);

        while (right < s.length()) {
            if (isWindowValid(left, right, s, k, frequencyMap)) {
                maxLength = Math.max(maxLength, right - left + 1);
                right++;
                if (right < s.length()) {
                    frequencyMap.computeIfAbsent(s.charAt(right), v -> 0);
                    frequencyMap.compute(s.charAt(right), (key, val) -> val = val + 1);
                }
            } else {
                frequencyMap.compute(s.charAt(left), (key, val) -> val = val - 1);
                left++;
            }
        }

        return maxLength;
    }

    private boolean isWindowValid(int left, int right, String s, int k, Map<Character, Integer> frequencyMap) {

        int maxFreq = 0;

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            maxFreq = Math.max(maxFreq, entry.getValue());
        }

        return right - left - maxFreq + 1 <= k;
    }

}
