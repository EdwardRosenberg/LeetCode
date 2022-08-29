package twopointers;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreeSumTest {

    @Test
    void threeSum() {
        //int[] nums = new int[] {1,2,-2,-1}; // 0
        //int[] nums = new int[] {-2,0,1,1,2}; // 1
        //int[] nums = new int[] {3,0,-2,-1,1,2}; // 3
        int[] nums = new int[] {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0}; // 6
        List<List<Integer>> result = new ThreeSum().threeSum(nums);
        assertEquals(6, result.size());
    }
}