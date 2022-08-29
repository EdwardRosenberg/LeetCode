package twopointers;

public class TrappingRainwater {

    public int trap(int[] height) {
        int[] maxHeightLeft = new int[height.length];
        int[] maxHeightRight = new int[height.length];

        int totalRain = 0;
        int maxHeight = 0;

        for (int i = 0; i < height.length; i++) {
            maxHeightLeft[i] = maxHeight;

            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }

        maxHeight = 0;

        for (int i = height.length - 1; i >= 0; i--) {
            maxHeightRight[i] = maxHeight;

            if (height[i] > maxHeight) {
                maxHeight = height[i];
            }
        }

        for (int i = 0; i < height.length; i++) {
            int rain = Math.min(maxHeightLeft[i], maxHeightRight[i]) - height[i];
            if (rain > 0) {
                totalRain += rain;
            }
        }
        return totalRain;
    }

}
