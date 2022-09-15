package arraysandhashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IsAnagram2 {

    public boolean isAnagram(String s, String t) {
        if (s.equals(t)) return true;
        if (s.length() != t.length()) return false;

        char[] lettersS = new char[s.length()];
        char[] lettersT = new char[t.length()];

        for(int i=0; i<s.length(); i++){
            lettersS[i] = s.charAt(i);
            lettersT[i] = t.charAt(i);
        }

        Arrays.sort(lettersS);
        Arrays.sort(lettersT);

        return Arrays.equals(lettersS, lettersT);
    }
}
