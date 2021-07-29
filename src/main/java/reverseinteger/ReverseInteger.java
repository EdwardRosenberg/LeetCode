package reverseinteger;

public class ReverseInteger {

    /**
     *  Key Concepts:
     *  - Modding (%) the input int by 10 will extract the rightmost digit: 1234 % 10 = 4
     *  - Multiplying an integer by 10 will "push it left" exposing a zero to the right of that number: 5 * 10 = 50
     *  - Dividing an integer by 10 will remove the rightmost digit: 75 / 10 = 7
     *
     * @param numberToReverse - input number
     * @return reversed number as int
     */
    public int reverse(int numberToReverse) {
            long reversedNumber = 0;

            while (numberToReverse != 0) {
                // extract the rightmost digit of your input number.
                long lastDigit = numberToReverse % 10;
                // multiply reversed number by 10, exposing a zero at the end and add the last digit to it
                reversedNumber = reversedNumber * 10 + lastDigit;
                // cut off the last digit off the input number, so we can move on to the next one
                numberToReverse = numberToReverse / 10;
            }

            // check if the reversed number is within integer range
            if (reversedNumber > Integer.MAX_VALUE || reversedNumber < Integer.MIN_VALUE) {
                return 0;
            }
            return (int) reversedNumber;
    }
}
