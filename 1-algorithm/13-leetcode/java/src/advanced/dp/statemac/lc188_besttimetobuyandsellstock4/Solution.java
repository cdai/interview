package advanced.dp.statemac.lc188_besttimetobuyandsellstock4;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
public class Solution {

    // My 2AC: O(N^2)
    public int maxProfit(int k, int[] prices) {
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
