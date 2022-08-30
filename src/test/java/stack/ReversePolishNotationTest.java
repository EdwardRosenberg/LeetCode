package stack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReversePolishNotationTest {

    @Test
    void evalRPN() {
        assertEquals(9, new ReversePolishNotation().evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        assertEquals(22, new ReversePolishNotation().evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}