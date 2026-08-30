package stack.reversepolishnotation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePolishNotationTest {

    private final ReversePolishNotation reversePolishNotation = new ReversePolishNotation();

    @Test
    void exampleOne() {
        assertEquals(9, reversePolishNotation.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    @Test
    void exampleTwo() {
        assertEquals(6, reversePolishNotation.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }

    @Test
    void singleNumberNoOperators() {
        assertEquals(42, reversePolishNotation.evalRPN(new String[]{"42"}));
    }

    @Test
    void negativeOperandsParsedCorrectly() {
        // "-11" must be read as a single negative-number token, not as a
        // minus operator followed by "11"
        assertEquals(-11, reversePolishNotation.evalRPN(new String[]{"-11"}));
    }

    @Test
    void divisionTruncatesTowardZero() {
        // 6/-132 = -0.0454... which truncates toward zero to 0, not -1
        // (Java's integer division already truncates toward zero, matching
        // the problem's required behavior for negative results)
        assertEquals(0, reversePolishNotation.evalRPN(new String[]{"6", "-132", "/"}));
    }

    @Test
    void subtractionOrderMatchesOperandPushOrder() {
        // "4 2 -" means 4 - 2, not 2 - 4 — the first-pushed operand must
        // stay the left-hand side of a non-commutative operator
        assertEquals(2, reversePolishNotation.evalRPN(new String[]{"4", "2", "-"}));
    }

    @Test
    void largerExpression() {
        assertEquals(22, reversePolishNotation.evalRPN(
                new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
