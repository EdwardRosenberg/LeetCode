package stack.generateparenthesis;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GenerateParenthesisTest {

    private final GenerateParenthesis generateParenthesis = new GenerateParenthesis();

    @Test
    void exampleOne() {
        List<String> expected = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        assertEquals(expected, generateParenthesis.generateParenthesis(3));
    }

    @Test
    void singlePair() {
        assertEquals(List.of("()"), generateParenthesis.generateParenthesis(1));
    }

    @Test
    void twoPairs() {
        assertEquals(Arrays.asList("(())", "()()"), generateParenthesis.generateParenthesis(2));
    }

    @Test
    void zeroPairs() {
        assertEquals(List.of(""), generateParenthesis.generateParenthesis(0));
    }

    @Test
    void countMatchesCatalanNumberForLargerN() {
        // n=6 has 132 valid combinations (6th Catalan number) — a cheap way
        // to sanity-check a larger input without hardcoding the full list
        List<String> result = generateParenthesis.generateParenthesis(6);
        assertEquals(132, result.size());
        assertTrue(result.stream().allMatch(GenerateParenthesisTest::isBalanced));
    }

    private static boolean isBalanced(String combination) {
        int openCount = 0;
        for (char c : combination.toCharArray()) {
            openCount += c == '(' ? 1 : -1;
            if (openCount < 0) return false;
        }
        return openCount == 0;
    }
}
