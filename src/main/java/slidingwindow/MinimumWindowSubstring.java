package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        String ans = new String();

        if (t.length() > s.length() || t.length() == 0 || s.length() == 0) return ans;

        Map<Character, Integer> inputCharMap = new HashMap<>();

        for (char c : t.toCharArray()) {
            int val = inputCharMap.getOrDefault(c, 0);
            inputCharMap.put(c, val + 1);
        }

        int left = 0;

        Map<Character, Integer> charMap = new HashMap<>();

        while (!inputCharMap.containsKey(s.charAt(left))) {
            if (left + 1 < s.length()) {
                left++;
            } else {
                return ans;
            }
        }

        int right = left;

        // don't keep track of chars not in the input string
        if (inputCharMap.containsKey(s.charAt(right))) {
            int val = charMap.getOrDefault(s.charAt(right), 0);
            charMap.put(s.charAt(right), val + 1);
        }

        while (!isValidWindow(inputCharMap, charMap)) {
            if (right + 1 < s.length()) {
                right++;
            } else {
                break;
            }

            if (inputCharMap.containsKey(s.charAt(right))) {
                int val = charMap.getOrDefault(s.charAt(right), 0);
                charMap.put(s.charAt(right), val + 1);
            }
        }

        if (!isValidWindow(inputCharMap, charMap)) return ans;

        
        boolean isValid = true;
        char missingChar = 0;
        ans = s.substring(left, right + 1);

        while (right < s.length()) {
            if (charMap.get(s.charAt(left)) > inputCharMap.get(s.charAt(left))) {
                do {
                    if (charMap.containsKey(s.charAt(left))) {
                        if (charMap.get(s.charAt(left)) > 1) {
                            charMap.put(s.charAt(left), charMap.get(s.charAt(left)) - 1);
                            if(charMap.get(s.charAt(left)) < inputCharMap.get(s.charAt(left))){
                                isValid = false;
                                missingChar = s.charAt(left);
                            }
                        } else {
                            charMap.remove(s.charAt(left));
                            isValid = false;
                            missingChar = s.charAt(left);
                        }
                    }

                    left++;
                } while (!inputCharMap.containsKey(s.charAt(left)));
            } else if (right + 1 < s.length()) {
                do {
                    right++;
                    if (inputCharMap.containsKey(s.charAt(right))) {
                        int val = charMap.getOrDefault(s.charAt(right), 0);
                        charMap.put(s.charAt(right), val + 1);
                        
                        if(!isValid && missingChar == s.charAt(right)) isValid = true;
                        
                    }
                } while (s.charAt(right) != s.charAt(left) && right + 1 < s.length());
            } else {
                break;
            }

            if (isValid && right - left < ans.length()) {
                ans = s.substring(left, right + 1);
            }
        }

        return ans;
    }

    private boolean isValidWindow(Map<Character, Integer> inputCharMap, Map<Character, Integer> charMap) {
        if (charMap.size() < inputCharMap.size()) return false;

        for (char c : inputCharMap.keySet()) {
            if (charMap.get(c) < inputCharMap.get(c)) {
                return false;
            }
        }
        return true;
    }
}
