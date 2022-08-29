package slidingwindow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuySellStockTest {

    @Test
    void maxProfit() {
        int[] prices = {7,1,5,3,6,4};
        assertEquals(5, new BuySellStock().maxProfit(prices));

        int[] prices2 = {7,6,4,3,1};
        assertEquals(0, new BuySellStock().maxProfit(prices2));

        int[] prices3 = {7};
        assertEquals(0, new BuySellStock().maxProfit(prices3));
    }
}