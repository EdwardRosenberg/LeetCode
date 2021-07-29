package reverseinteger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseIntegerTest {

    @Test
    void should_reverse_integer() {
        // arrange
        int input = 1234;
        int expectedReversed = 4321;

        // act
        int actualReversed = new ReverseInteger().reverse(input);

        // assert
        assertEquals(expectedReversed, actualReversed);
    }

    @Test
    void should_return_0_if_reversed_number_overflows_integer() {
        // act and assert
        assertEquals(0, new ReverseInteger().reverse(Integer.MAX_VALUE));
    }
}