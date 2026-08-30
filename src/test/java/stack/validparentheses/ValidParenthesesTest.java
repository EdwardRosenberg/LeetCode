package stack.validparentheses;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidParenthesesTest {

    private final ValidParentheses validParentheses = new ValidParentheses();

    @Test
    void exampleOne() {
        assertTrue(validParentheses.isValid("()"));
    }

    @Test
    void exampleTwo() {
        assertTrue(validParentheses.isValid("()[]{}"));
    }

    @Test
    void nestedBrackets() {
        assertTrue(validParentheses.isValid("({[]})"));
    }

    @Test
    void emptyInput() {
        assertTrue(validParentheses.isValid(""));
    }

    @Test
    void singleUnclosedOpener() {
        assertFalse(validParentheses.isValid("("));
    }

    @Test
    void mismatchedBracketTypes() {
        assertFalse(validParentheses.isValid("(]"));
    }

    @Test
    void interleavedBracketsWrongOrder() {
        // an inner bracket closes before the outer one it's nested inside —
        // "{" opens, "[" opens, but "}" tries to close before "]" does
        assertFalse(validParentheses.isValid("({[}])"));
    }

    @Test
    void closerWithNothingOpen() {
        assertFalse(validParentheses.isValid(")"));
    }

    @Test
    void largerValidInput() {
        assertTrue(validParentheses.isValid("(([]){}[({})])".repeat(50)));
    }
}
