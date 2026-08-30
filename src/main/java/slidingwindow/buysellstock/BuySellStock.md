# Best Time to Buy and Sell Stock

- **LeetCode:** [121 — Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) (Easy)
- **Pattern:** Sliding Window
- **Cue:** "one buy + one sell, maximize profit — track the minimum price seen so far"

## Approach

Walk the array with two pointers. `buyIndex` marks the lowest price seen so far; `sellIndex` scans forward pricing a sale against it. Whenever today's price wouldn't beat buying at `buyIndex` (price at `sellIndex` `<=` price at `buyIndex`), jump `buyIndex` up to `sellIndex` instead — today becomes the new best (or equally good) buy point going forward.

- **Time:** O(n) — a single pass; each index is visited once by `sellIndex`.
- **Space:** O(1)

**Why it works:** jumping `buyIndex` forward whenever a lower-or-equal price shows up never throws away a better opportunity. Any profit computed against the *old* `buyIndex` from this point on would be `<=` the profit computed against the *new*, lower-or-equal one — so the new buy point is always at least as good for every future sell day. That's the standard "track the running minimum" argument for this problem, just expressed as a pointer jump instead of a single `minPrice` variable. Since `sellIndex` visits every day exactly once and `bestProfit` is updated against the best-known buy point at each of those visits, the true maximum profit can't be skipped.

**Watch out:** `left`/`right` aren't a shrinking-and-growing *window* the way most sliding-window problems use them — `buyIndex` only ever jumps forward to meet `sellIndex`, it never trails behind tracking a range of valid elements. This is really "track a running minimum," dressed up in two-pointer clothing because that's how it's most commonly taught.

## Visualizing it

```
prices = [7, 1, 5, 3, 6, 4]

buyIndex=0(7) sellIndex=1(1): 7 is not < 1 -> jump buyIndex to 1

buyIndex=1(1) sellIndex=2(5): profit=5-1=4   bestProfit=4
buyIndex=1(1) sellIndex=3(3): profit=3-1=2   bestProfit stays 4
buyIndex=1(1) sellIndex=4(6): profit=6-1=5   bestProfit=5
buyIndex=1(1) sellIndex=5(4): profit=4-1=3   bestProfit stays 5

result: bestProfit = 5   (buy at index 1, price 1; sell at index 4, price 6)
```

## Code

```java
package slidingwindow.buysellstock;

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
```
