package containsduplicate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContainsDuplicateTest {

    private ContainsDuplicate containsDuplicate = new ContainsDuplicate();

    @Test
    void containsDuplicate() {
        int[] input = {1,2,3,1};

        assertTrue(containsDuplicate.containsDuplicate(input));
    }
}