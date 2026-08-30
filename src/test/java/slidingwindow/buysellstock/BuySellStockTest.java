package slidingwindow.buysellstock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BuySellStockTest {

    private final BuySellStock solution = new BuySellStock();

    @Test
    void exampleOne() {
        assertEquals(5, solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    @Test
    void strictlyDecreasingPricesYieldNoProfit() {
        assertEquals(0, solution.maxProfit(new int[]{7, 6, 4, 3, 1}));
    }

    @Test
    void emptyInput() {
        assertEquals(0, solution.maxProfit(new int[]{}));
    }

    @Test
    void singleDay() {
        // no second day to sell on, so no transaction is possible
        assertEquals(0, solution.maxProfit(new int[]{7}));
    }

    @Test
    void allSamePrice() {
        assertEquals(0, solution.maxProfit(new int[]{3, 3, 3, 3}));
    }

    @Test
    void twoDaysProfitable() {
        assertEquals(1, solution.maxProfit(new int[]{1, 2}));
    }

    @Test
    void largerInput() {
        // best trade is buy at index 2 (price 1), sell at index 5 (price 9);
        // buyIndex later jumps again to index 6 (price 0), but nothing after
        // it beats the profit already found, so the answer doesn't change
        assertEquals(8, solution.maxProfit(new int[]{2, 4, 1, 7, 3, 9, 0, 5}));
    }
}
