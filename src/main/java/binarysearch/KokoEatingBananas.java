package binarysearch;

import java.util.Arrays;

public class KokoEatingBananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        int minEatingSpeed = right;

        while (left <= right) {
            int middle = (left + right) / 2;

            if (timeToEatAllBananas(piles, middle, h) > h) {
                left = middle + 1;
            } else {
                minEatingSpeed = Math.min(minEatingSpeed, middle);
                right = middle - 1;
            }
        }

        return minEatingSpeed;
    }

    private int timeToEatAllBananas(int[] piles, int k, int h) {
        int time = 0;

        for (int pile : piles) {
            time += Math.ceil((double) pile / k);
            if(time > h) break;
        }

        return time;
    }
}
