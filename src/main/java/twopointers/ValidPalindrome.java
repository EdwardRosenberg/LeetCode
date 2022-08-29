package twopointers;

import java.util.LinkedList;
import java.util.List;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {

        StringBuilder sbLeft = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                sbLeft.append(c);
            }
        }
        String strLeft = sbLeft.toString().toLowerCase();

        StringBuilder sbRight = new StringBuilder();
        for (int l = s.length() - 1; l >= 0; l--) {
            char c = s.charAt(l);
            if (Character.isLetter(c) || Character.isDigit(c)) {
                sbRight.append(c);
            }
        }

        String strRight = sbRight.toString().toLowerCase();

        return strLeft.equals(strRight);
    }


}
