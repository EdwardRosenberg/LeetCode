package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() > s2.length()) return false;

        int left = 0;
        int right = s1.length() - 1;

        Map<Character, Integer> inputCharMap = new HashMap<>();
        Map<Character, Integer> charMap = new HashMap<>();


        for (int i = 0; i < s1.length(); i++) {
            inputCharMap.putIfAbsent(s1.charAt(i), 0);
            inputCharMap.compute(s1.charAt(i), (k, v) -> v = v + 1);
        }

        for (int i = 0; i < s1.length(); i++) {
            charMap.putIfAbsent(s2.charAt(i), 0);
            charMap.compute(s2.charAt(i), (k, v) -> v = v + 1);
        }

        while (right < s2.length()) {
            if (inputCharMap.equals(charMap)) {
                return true;
            }

            if (right + 1 < s2.length()) {
                // move window
                left++;
                right++;

                // update character map
                if (charMap.get(s2.charAt(left - 1)) > 1) {
                    charMap.compute(s2.charAt(left - 1), (k, v) -> v = v - 1);
                } else {
                    charMap.remove(s2.charAt(left - 1));
                }

                charMap.putIfAbsent(s2.charAt(right), 0);
                charMap.compute(s2.charAt(right), (k, v) -> v = v + 1);
            } else {
                break;
            }
        }

        return false;
    }

}
