package reverseinteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseIntegerTest {

    @Test
    void should_reverse_integer() {
        int input = 1234;
        int expectedReversed = 4321;

        int actualReversed = new ReverseInteger().reverse(input);

        assertEquals(expectedReversed, actualReversed);
    }

    @Test
    void should_return_0_if_reversed_number_overflows_integer(){
        int input = Integer.MAX_VALUE;

        assertEquals(0, new ReverseInteger().reverse(input));
    }
}