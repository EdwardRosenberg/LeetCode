package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringSliding {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();

        int maxLength = 0;
        Set<Character> chars = new HashSet<>();

        int left = 0;

        for (int right = 0; right < s.length(); right++) {

            while (chars.contains(s.charAt(right))) {
                chars.remove(s.charAt(left));
                left++;
            }
            chars.add(s.charAt(right));
            maxLength = Math.max(maxLength, chars.size());
        }

        return maxLength;
    }
}
