package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class PermutationInStringOptimized {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int left = 0;
        int right = s1.length() - 1;

        Map<Character, Integer> inputCharMap = initCharacterMap();
        Map<Character, Integer> charMap = initCharacterMap();

        for (int i = 0; i < s1.length(); i++) {
            inputCharMap.compute(s1.charAt(i), (k, v) -> v = v + 1);
        }

        for (int i = 0; i < s1.length(); i++) {
            charMap.compute(s2.charAt(i), (k, v) -> v = v + 1);
        }

        int matches = getInitialMatches(inputCharMap, charMap);

        while (right < s2.length()) {

            if (matches == 26) {
                return true;
            }

            if (right + 1 < s2.length()) {
                // move window
                left++;
                right++;

                // update character map
                charMap.compute(s2.charAt(left - 1), (k, v) -> v = v - 1);
                matches = updateMatchesOnDecrease(inputCharMap, charMap, s2.charAt(left - 1), matches);

                charMap.compute(s2.charAt(right), (k, v) -> v = v + 1);
                matches = updateMatchesOnIncrease(inputCharMap, charMap, s2.charAt(right), matches);
            } else {
                break;
            }
        }

        return false;
    }

    private Map<Character, Integer> initCharacterMap() {
        Map<Character, Integer> map = new HashMap<>(26);
        for (char c : ALPHABET.toCharArray()) {
            map.put(c, 0);
        }
        return map;
    }

    private int getInitialMatches(Map<Character, Integer> inputCharMap, Map<Character, Integer> charMap) {
        int matches = 0;

        for (Map.Entry<Character, Integer> entry : inputCharMap.entrySet()) {
            if (entry.getValue().equals(charMap.get(entry.getKey()))) {
                matches++;
            }
        }

        return matches;
    }

    private int updateMatchesOnDecrease(Map<Character, Integer> inputCharMap, Map<Character, Integer> charMap, char currentChar, int matches) {
        boolean currentMatch = charMap.get(currentChar).equals(inputCharMap.get(currentChar));
        boolean previousMatch = inputCharMap.get(currentChar).equals(charMap.get(currentChar) + 1);

        if (!previousMatch && currentMatch) {
            matches++;
        } else if (previousMatch && !currentMatch) {
            matches--;
        }
        return matches;
    }

    private int updateMatchesOnIncrease(Map<Character, Integer> inputCharMap, Map<Character, Integer> charMap, char currentChar, int matches) {
        boolean currentMatch = charMap.get(currentChar).equals(inputCharMap.get(currentChar));
        boolean previousMatch = inputCharMap.get(currentChar).equals(charMap.get(currentChar) - 1);

        if (!previousMatch && currentMatch) {
            matches++;
        } else if (previousMatch && !currentMatch) {
            matches--;
        }
        return matches;
    }

}
