package slidingwindow;

public class BuySellStock {

    public int maxProfit(int[] prices) {
        int left = 0;
        int right = 1;
        int maxProfit = 0;

        while(left < prices.length && right < prices.length){
            if(prices[left] < prices[right]){
                int profit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, profit);
                right++;
            } else {
                left = right;
                right++;
            }
        }
        return maxProfit;
    }

}
