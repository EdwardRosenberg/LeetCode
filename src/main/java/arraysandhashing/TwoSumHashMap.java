package arraysandhashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSumHashMap {

    public int[] twoSum(int[] inputNumbers, int target) {
        Map<Integer, Integer> arrayMap = new HashMap<>();

        for(int i=0; i< inputNumbers.length; i++){
            int diff = target - inputNumbers[i];
            if(arrayMap.containsKey(diff)){
                return new int[]{arrayMap.get(diff), i};
            } else {
                arrayMap.put(inputNumbers[i], i);
            }
        }

        return new int[]{};
    }
}
