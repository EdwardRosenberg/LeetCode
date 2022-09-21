package arraysandhashing;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        int[] response = new int[k];

        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }

        Stream<Map.Entry<Integer, Integer>> sorted =
                map.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<Integer> list = sorted.limit(k).map(entry -> entry.getKey()).collect(Collectors.toList());

        for (int i = 0; i < list.size(); i++) {
            response[i] = list.get(i);
        }

        return response;
    }
}
