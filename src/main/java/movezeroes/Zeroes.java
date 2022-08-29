package movezeroes;

import java.util.LinkedList;
import java.util.Queue;

public class Zeroes {

    public void moveZeroes(int[] input) {

        Queue<Integer> indexQueue = new LinkedList<>();
        int inputLength = input.length;

        for (int i = 0; i < inputLength; i++) {
            if (input[i] == 0) {
                indexQueue.add(i);
            } else if (input[i] > 0 && indexQueue.size() > 0) {
                input[indexQueue.remove()] = input[i];
                indexQueue.add(i);
            }
        }

        for (int i = inputLength - 1; i >= inputLength - indexQueue.size(); i--) {
            input[i] = 0;
        }
    }
}
