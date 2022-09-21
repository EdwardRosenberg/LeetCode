package arraysandhashing;

import java.util.HashMap;
import java.util.Map;

public class IsAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) return true;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> letterCount = new HashMap<>();
        char[] lettersS = s.toCharArray();
        char[] lettersT = t.toCharArray();

        for (int i = 0; i < lettersS.length; i++) {
            if (letterCount.containsKey(lettersS[i])) {
                Integer count = letterCount.get(lettersS[i]);
                letterCount.put(lettersS[i], count + 1);
            } else {
                letterCount.put(lettersS[i], 1);
            }
        }

        for (int i = 0; i < lettersT.length; i++) {
            if (!letterCount.containsKey(lettersT[i])) return false;

            Integer count = letterCount.get(lettersT[i]);
            count = count - 1;
            if (count == 0) {
                letterCount.remove(lettersT[i]);
            } else {
                letterCount.put(lettersT[i], count);
            }
        }

        return true;

    }
}
