package twopointers;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right){
            int minHeight = Math.min(height[left], height[right]);
            int length = right - left;
            int area = minHeight * length;
            if(area > maxArea) maxArea = area;

            if(height[left] < height[right]){
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
