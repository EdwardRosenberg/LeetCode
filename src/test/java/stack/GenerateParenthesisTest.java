package stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateParenthesisTest {

    @Test
    void generateParenthesis() {
        List<String> expected = Arrays.asList(new String[]{"((()))", "(()())", "(())()", "()(())", "()()()"});
        assertEquals(expected, new GenerateParenthesis().generateParenthesis(3));
    }
}