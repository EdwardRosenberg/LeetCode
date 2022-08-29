package twopointers;

import java.util.LinkedList;
import java.util.Objects;

public class ValidPalindromeSinglePass {

    public boolean isPalindrome(String s) {
        LinkedList<Character> listLeft = new LinkedList<>();
        LinkedList<Character> listRight = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (Character.isLetter(c) || Character.isDigit(c)) {
                if (Character.isUpperCase(c)) {
                   c = Character.toLowerCase(c);
                }

                listLeft.addFirst(c);
                listRight.addLast(c);
            }
        }

        return Objects.equals(listLeft, listRight);
    }
}
