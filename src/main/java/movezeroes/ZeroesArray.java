package movezeroes;

public class ZeroesArray {

    public void moveZeroes(int[] input) {

        int offset = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                offset++;
            } else {
                input[i - offset] = input[i];
            }
        }

        for (int i = offset; i > 0; i--) {
            input[input.length - i] = 0;
        }
    }
}
