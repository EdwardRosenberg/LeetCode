package stack.dailytemperatures;

import java.util.Stack;

/**
 * LeetCode 739 — Daily Temperatures (Medium)
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Pattern: Stack
 * Cue: "next greater element to the right — how many steps until a bigger value shows up"
 *
 * Approach: Keep a stack of day-indices whose "days until warmer" answer is
 * still unresolved, from bottom to top in decreasing order of temperature.
 * On each new day, pop every stacked day whose temperature is beaten by
 * today's — each pop resolves that day's answer as the distance to today.
 * Push today's index (it becomes the newest unresolved day).
 *
 * Time: O(n) — each index is pushed once and popped at most once.
 * Space: O(n) for the stack.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] daysUntilWarmer = new int[temperatures.length];
        Stack<Integer> unresolvedDays = new Stack<>();

        for (int currentDay = 0; currentDay < temperatures.length; currentDay++) {
            while (!unresolvedDays.isEmpty() && temperatures[currentDay] > temperatures[unresolvedDays.peek()]) {
                int previousDay = unresolvedDays.pop();
                daysUntilWarmer[previousDay] = currentDay - previousDay;
            }
            unresolvedDays.push(currentDay);
        }
        return daysUntilWarmer;
    }
}
