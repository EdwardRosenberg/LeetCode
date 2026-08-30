package stack.carfleet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LeetCode 853 — Car Fleet (Medium)
 * https://leetcode.com/problems/car-fleet/
 *
 * Pattern: Stack
 * Cue: "cars merge into a fleet when a slower car ahead blocks faster cars behind"
 *
 * Approach: Compute each car's own time-to-target as if it were alone on
 * the road. Process cars from closest-to-target to farthest. A car whose
 * solo time is <= the time of the fleet immediately ahead will catch up to
 * (or arrive alongside) that fleet before reaching the target, so it merges
 * — its time is discarded. A car whose solo time is strictly greater never
 * catches the fleet ahead in time, so it forms a new fleet of its own.
 *
 * Time: O(n log n) — dominated by sorting positions.
 * Space: O(n) for the position→speed map and the stack.
 */
public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length < 2) return position.length;

        Map<Integer, Integer> positionToSpeed = new HashMap<>(position.length);
        for (int i = 0; i < position.length; i++) {
            positionToSpeed.put(position[i], speed[i]);
        }

        Arrays.sort(position);
        Stack<Double> fleetArrivalTimes = new Stack<>();

        for (int car = position.length - 1; car >= 0; car--) {
            int carSpeed = positionToSpeed.get(position[car]);
            // time data type is double, so we can capture decimals and accurately compare values
            double soloTime = (double) (target - position[car]) / carSpeed;
            if (fleetArrivalTimes.isEmpty() || soloTime > fleetArrivalTimes.peek()) {
                fleetArrivalTimes.push(soloTime);
            }
        }

        return fleetArrivalTimes.size();
    }
}
