package twopointers;

public class TrappingRainwaterTwoPointers {

    public int trap(int[] height) {

        int maxRain = 0;
        int maxLeft = 0;
        int maxRight = 0;

        int left = 0;
        int right = height.length - 1;

        while(left < right){
            int rain = Math.min(maxLeft, maxRight) - height[left];
            if (rain > 0) maxRain += rain;

            rain = Math.min(maxLeft, maxRight) - height[right];
            if (rain > 0) maxRain += rain;

            if(maxLeft < height[left]) maxLeft = height[left];
            if(maxRight < height[right]) maxRight = height[right];

            if(height[left] <= height[right]){
                left++;
            } else {
                right--;
            }
        }

        return maxRain;
    }

}
