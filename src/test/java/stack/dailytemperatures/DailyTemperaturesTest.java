package stack.dailytemperatures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class DailyTemperaturesTest {

    private final DailyTemperatures dailyTemperatures = new DailyTemperatures();

    @Test
    void exampleOne() {
        assertArrayEquals(new int[]{1, 1, 4, 2, 1, 1, 0, 0},
                dailyTemperatures.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73}));
    }

    @Test
    void emptyInput() {
        assertArrayEquals(new int[]{}, dailyTemperatures.dailyTemperatures(new int[]{}));
    }

    @Test
    void singleDay() {
        assertArrayEquals(new int[]{0}, dailyTemperatures.dailyTemperatures(new int[]{50}));
    }

    @Test
    void strictlyDecreasingNeverWarmsUp() {
        assertArrayEquals(new int[]{0, 0, 0, 0}, dailyTemperatures.dailyTemperatures(new int[]{80, 70, 60, 50}));
    }

    @Test
    void strictlyIncreasingEachDayWarmsUpTomorrow() {
        assertArrayEquals(new int[]{1, 1, 1, 0}, dailyTemperatures.dailyTemperatures(new int[]{50, 60, 70, 80}));
    }

    @Test
    void plateauDoesNotCountAsWarmer() {
        // equal temperatures must NOT resolve the earlier day — strictly
        // greater is required, so both 70s wait for the 75
        assertArrayEquals(new int[]{2, 1, 0}, dailyTemperatures.dailyTemperatures(new int[]{70, 70, 75}));
    }

    @Test
    void largerInputWithSingleFinalSpike() {
        // 200 strictly decreasing days followed by one spike at the end —
        // every earlier day's answer collapses to "wait for the spike"
        int[] temperatures = new int[201];
        for (int day = 0; day < 200; day++) {
            temperatures[day] = 200 - day;
        }
        temperatures[200] = 1000;

        int[] expected = new int[201];
        for (int day = 0; day < 200; day++) {
            expected[day] = 200 - day;
        }
        expected[200] = 0;

        assertArrayEquals(expected, dailyTemperatures.dailyTemperatures(temperatures));
    }
}
