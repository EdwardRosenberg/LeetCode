package stack;

import java.util.Stack;

public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {

        int maxArea = 0;
        Stack<Integer> topHeight = new Stack<>();
        Stack<Integer> startingPositions = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            if (topHeight.isEmpty() || topHeight.peek() < heights[i]) {
                topHeight.push(heights[i]);
                startingPositions.push(i);
            } else {
                int startingPosition = startingPositions.peek();
                while (!topHeight.isEmpty() && topHeight.peek() > heights[i]) {
                    startingPosition = startingPositions.pop();
                    int area = topHeight.pop() * (i - startingPosition);
                    maxArea = Math.max(maxArea, area);
                }

                if ((topHeight.isEmpty() || topHeight.peek() < heights[i])) {
                    topHeight.push(heights[i]);
                    startingPositions.push(startingPosition);
                }
            }
        }

        while (!topHeight.isEmpty() && !startingPositions.isEmpty()) {
            int area = topHeight.pop() * (heights.length - (startingPositions.pop()));
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}
