package arraysandhashing;

import java.util.*;

public class TopKFrequentElements2 {

    public int[] topKFrequent(int[] nums, int k) {

        int[] response = new int[k];
        // frequency is key and value is a list to handle nums with same frequency
        Map<Integer, List<Integer>> frequencyMap = new HashMap<>();
        Arrays.sort(nums);

        int prev = nums[0];
        int freq = 0;

        for(int i : nums){
            if (i == prev){
                freq++;
            } else {
                frequencyMap.computeIfAbsent(freq, s -> new Stack<>());
                frequencyMap.get(freq).add(prev);
                prev = i;
                freq = 1;
            }
        }
        frequencyMap.computeIfAbsent(freq, s -> new Stack<>());
        frequencyMap.get(freq).add(prev);

        int responseIndex = 0;

        for(int i = nums.length; i>0; i--){
            if (frequencyMap.containsKey(i)){
                Iterator<Integer> iterator = frequencyMap.get(i).iterator();
                while(iterator.hasNext()){
                    response[responseIndex] = iterator.next();
                    responseIndex++;
                    if(responseIndex + 1 > k) return response;
                }
            }
        }

        return response;
    }
}
