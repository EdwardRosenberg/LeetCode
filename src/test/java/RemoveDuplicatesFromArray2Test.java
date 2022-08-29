import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveDuplicatesFromArray2Test {

    @Test
    void removeDuplicates() {
        int[] nums = {1,1,1,2,2,3};
        assertEquals(5, new RemoveDuplicatesFromArray2().removeDuplicates(nums));

        int nums2[] = {0,0,1,1,1,1,2,3,3};
        assertEquals(7, new RemoveDuplicatesFromArray2().removeDuplicates(nums2));
    }
}