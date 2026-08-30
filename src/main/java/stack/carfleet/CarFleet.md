# Car Fleet

- **LeetCode:** [853 — Car Fleet](https://leetcode.com/problems/car-fleet/) (Medium)
- **Pattern:** Stack
- **Cue:** "cars merge into a fleet when a slower car ahead blocks faster cars behind"

## Approach

Compute each car's own time-to-target as if it were alone on the road. Process cars from closest-to-target to farthest. A car whose solo time is `<=` the time of the fleet immediately ahead will catch up to (or arrive alongside) that fleet before reaching the target, so it merges — its time is discarded. A car whose solo time is strictly greater never catches the fleet ahead in time, so it forms a new fleet of its own.

- **Time:** O(n log n) — dominated by sorting positions.
- **Space:** O(n) for the position→speed map and the stack.

## Why it works

A car can only ever be slowed down by whatever is directly ahead of it, never by anything behind — so scanning from the car nearest the target backward means every comparison is against the correct, already-resolved fleet. `fleetArrivalTimes.peek()` always holds the arrival time of the most recently formed fleet, which is also the *slowest-to-arrive* fleet seen so far in the scan (each push only happens when the new time is strictly greater than the previous top). A car merges exactly when it would otherwise arrive no later than that fleet — meaning it's physically forced to slow down and join it — which is precisely the `soloTime > peek()` push condition, inverted.

## Visualizing it

```
target = 12
position = [10, 8, 0, 5, 3]   speed = [2, 4, 1, 1, 3]

sorted positions (ascending): [0, 3, 5, 8, 10]
scan from closest-to-target (pos=10) down to farthest (pos=0):

pos=10 speed=2  soloTime=(12-10)/2=1.0   stack empty          -> push        stack=[1.0]
pos=8  speed=4  soloTime=(12-8)/4=1.0    1.0 > 1.0? no         -> merge       stack=[1.0]
pos=5  speed=1  soloTime=(12-5)/1=7.0    7.0 > 1.0? yes         -> push        stack=[1.0, 7.0]
pos=3  speed=3  soloTime=(12-3)/3=3.0    3.0 > 7.0? no          -> merge       stack=[1.0, 7.0]
pos=0  speed=1  soloTime=(12-0)/1=12.0   12.0 > 7.0? yes        -> push        stack=[1.0, 7.0, 12.0]

3 pushes -> 3 fleets: {pos 10, pos 8}, {pos 5, pos 3}, {pos 0}
```

Car at `pos=8` (speed 4) is faster than car at `pos=10` (speed 2) but starts behind it and reaches the target no later — so instead of passing it, it's forced to slow down and merge into that fleet, exactly as `soloTime > peek()` being false predicts.

## Watch out

The merge condition is `soloTime > peek()`, not `>=` — a car whose solo arrival time exactly *ties* the fleet ahead of it still merges (it catches up to, or arrives alongside, that fleet rather than slipping past). Using a strict `>=` instead of `>` for the *push* condition would wrongly split an exact tie into two separate fleets.

## Code

```java
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
```
