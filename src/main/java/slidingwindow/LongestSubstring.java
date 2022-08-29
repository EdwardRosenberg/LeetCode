package slidingwindow;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if(s.length() <= 1) return s.length();

        int maxLength = 0;
        Set<Character> chars = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!chars.contains(c)) {
                chars.add(c);
            } else {
                // start new set with current char
                chars = new HashSet<>();
                // backtrack to the last repeating character
                int ii = i;
                while(chars.add(s.charAt(ii))){
                    ii--;
                }
            }

            int length = chars.size();
            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}
