package stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CarFleet {

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length < 2) return position.length;

        Map<Integer, Integer> carPositionSpeed = new HashMap<>(position.length);

        for (int i = 0; i < position.length; i++) {
            carPositionSpeed.put(position[i], speed[i]);
        }

        Arrays.sort(position);
        Stack<Double> carTimes = new Stack<>();

        for (int car = position.length - 1; car >= 0; car--) {
            int carSpeed = carPositionSpeed.get(position[car]);
            // time data type is double, so we can capture decimals and accurately compare values
            double time = (double) (target - position[car]) / carSpeed;
            if (carTimes.isEmpty() || time > carTimes.peek()) {
                carTimes.push(time);
            }
        }

        return carTimes.size();
    }
}
