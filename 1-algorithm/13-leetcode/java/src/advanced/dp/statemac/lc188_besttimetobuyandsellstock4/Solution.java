package advanced.dp.statemac.lc188_besttimetobuyandsellstock4;

import java.util.Arrays;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
public class Solution {

    public int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) { // Degrade to II, make transactions as many as possible
            int profit = 0, min = Integer.MAX_VALUE;
            for (int price : prices) {
                if (min < price) profit += price - min;
                min = price;
            }
            return profit;
        }

        int[] buy = new int[k + 1], sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

    // My 3AC. O(NK) time. Reuse Problem III idea.
    // Represent K*2 states (Each transaction has two states: buy[i] and sell[i]).
    public int maxProfit3(int k, int[] prices) {
        if (k >= prices.length / 2) { // if k >= n/2, then you can make maximum number of transactions
            int profit = 0;
            for (int i = 1; i < prices.length; i++)
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }

        int[] buy = new int[k + 1], sell = new int[k + 1];
        Arrays.fill(buy, Integer.MIN_VALUE);
        for (int price : prices) {
            for (int i = 1; i <= k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], buy[i] + price);
            }
        }
        return sell[k];
    }

    // My 2AC: O(N^2)
    public int maxProfit2(int k, int[] prices) {
        if (prices.length < 2) return 0;
        int n = prices.length;
        if (k >= n / 2) { // if k >= n/2, then you can make maximum number of transactions
            int pro = 0;
            for (int i = 1; i < n; i++)
                if (prices[i] > prices[i - 1])
                    pro += prices[i] - prices[i - 1];
            return pro;
        }

        int[][] dp = new int[k + 1][n]; // sell states of K transactions
        for (int i = 1; i <= k; i++) {
            int buy = -prices[0];       // buy state
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], buy + prices[j]);
                buy = Math.max(buy, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

}
