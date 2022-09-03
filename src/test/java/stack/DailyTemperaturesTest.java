package stack;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DailyTemperaturesTest {

    @Test
    void dailyTemperatures() {
        assertTrue(Arrays.equals(new int[]{1,1,4,2,1,1,0,0}, new DailyTemperatures().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
    }
}