package advanced.scan.trend.lc309_besttimetobuyandsellstockwithcooldown;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 * Example: prices = [1, 2, 3, 0, 2]. maxProfit = 3. transactions = [buy, sell, cooldown, buy, sell].
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 3, 10, 2, 4}));
    }

    // O(N) time. Replace N space with variable because of limited states
    // dependency: sell[i-1] => buy[i-1] => rest[i-1] => sell[i-1] (use prev break cycle)
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int rest = 0, buy = -prices[0], sell = 0; // Both 0 and MIN correct, but 0 is more natural for me
        for (int i = 1; i < prices.length; i++) {
            int prev = sell;
            sell = buy + prices[i];
            buy = Math.max(rest - prices[i], buy);
            rest = Math.max(rest, prev);
        }
        return Math.max(rest, sell);
    }

    // Brilliant understanding from state machine views
    // We run 3 instances (initial action: rest, buy, sell) on 1 state machine
    // with 1 input tape (prices) at the same time.
    // Meanwhile, they have data interaction during this process!
    public int maxProfit_statemachine(int[] prices) {
        if (prices.length == 0) return 0;
        int n = prices.length;
        int[] rest = new int[n];
        int[] buy = new int[n];
        int[] sell = new int[n];

        rest[0] = 0;
        buy[0] = -prices[0];
        sell[0] = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            rest[i] = Math.max(rest[i - 1], sell[i - 1]);
            buy[i] = Math.max(rest[i - 1] - prices[i], buy[i - 1]);
            sell[i] = buy[i - 1] + prices[i];
        }
        return Math.max(rest[n - 1], sell[n - 1]); // Max profit is impossible be at Buy state
    }

    public int maxProfit1(int[] prices) {
        // Max profit before i ending with buy or sell
        int[] profitEndWithBuy = new int[prices.length + 2];
        int[] profitEndWithSell = new int[prices.length + 2];

        // Prevent sell without buy first
        profitEndWithBuy[1] = Integer.MIN_VALUE;

        for (int i = 2, j = 0; j < prices.length; i++, j++) {
            // Buy at i or not
            profitEndWithBuy[i] = Math.max(profitEndWithBuy[i - 1], profitEndWithSell[i - 2] - prices[j]);
            // Sell at i or not
            profitEndWithSell[i] = Math.max(profitEndWithSell[i - 1], profitEndWithBuy[i - 1] + prices[j]);
        }
        return profitEndWithSell[profitEndWithSell.length - 1];
    }
}
