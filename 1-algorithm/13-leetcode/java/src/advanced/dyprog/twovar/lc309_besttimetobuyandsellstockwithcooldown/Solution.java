package advanced.dyprog.twovar.lc309_besttimetobuyandsellstockwithcooldown;

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

    public int maxProfit(int[] prices) {
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
