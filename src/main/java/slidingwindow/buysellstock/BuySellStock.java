package slidingwindow.buysellstock;

/**
 * LeetCode 121 — Best Time to Buy and Sell Stock (Easy)
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *
 * Pattern: Sliding Window
 * Cue: "one buy + one sell, maximize profit — track the minimum price seen so far"
 *
 * Approach: Walk the array with two pointers. buyIndex marks the lowest
 * price seen so far; sellIndex scans forward pricing a sale against it.
 * Whenever today's price wouldn't beat buying at buyIndex (price at
 * sellIndex <= price at buyIndex), jump buyIndex up to sellIndex instead —
 * today becomes the new best (or equally good) buy point going forward.
 *
 * Time: O(n) — a single pass; each index is visited once by sellIndex.
 * Space: O(1)
 */
public class BuySellStock {

    public int maxProfit(int[] prices) {
        int buyIndex = 0;
        int sellIndex = 1;
        int bestProfit = 0;

        while (buyIndex < prices.length && sellIndex < prices.length) {
            if (prices[buyIndex] < prices[sellIndex]) {
                int profit = prices[sellIndex] - prices[buyIndex];
                bestProfit = Math.max(bestProfit, profit);
                sellIndex++;
            } else {
                buyIndex = sellIndex;
                sellIndex++;
            }
        }

        return bestProfit;
    }
}
