package arraysandhashing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TopKFrequentElementsTest {

    TopKFrequentElements topKFrequentElements = new TopKFrequentElements();

    @Test
    public void test(){
//        int[] nums = {1,1,1,2,2,3};
//
//        int[] response = new TopKFrequentElements2().topKFrequent(nums, 2);
//        assertTrue(Arrays.equals(new int[]{1, 2}, response));

        int[] nums2 = {-1, -1};

        int[] response2 = new TopKFrequentElements2().topKFrequent(nums2, 1);
        assertTrue(Arrays.equals(new int[]{-1}, response2));
    }

    @Test
    public void topK(){
        int[] input = {-1, -1};

        topKFrequentElements.topKFrequent(input, 1);
    }

}