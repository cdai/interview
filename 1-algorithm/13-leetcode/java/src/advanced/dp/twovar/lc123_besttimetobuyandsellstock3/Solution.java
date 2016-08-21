package advanced.dp.twovar.lc123_besttimetobuyandsellstock3;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * Note: You may not engage in multiple transactions at the same time
 * (ie, you must sell the stock before you buy again).
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().maxProfit(new int[]{1, 2, 5, 0, 3, 1, 10}));
    }

    // eg.   [1, 2, 5, 0, 3, 1, 10]
    // left : 0  1  4  0  3  0  10   (Wrong!!!)
    // right: 9  8  5 10  7  9   0
    // left : 0  1  4  4  4  4  10   (Correct DP!)
    // right:10 10 10 10  9  9   0
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0;
        }

        // highest profit in 0 ... i
        int[] left = new int[n];
        int[] right = new int[n];

        for (int i = 1, min = prices[0]; i < n; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);   // Wrong! "left[i] = prices[i] - min" should be globally optimal!
        }
        for (int i = n - 2, max = prices[n - 1]; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, left[i] + right[i]);
        }
        return max;
    }

    // eg.   [ 1, 2, 5, 0, 3, 1, 10 ]
    // buy1 : -1 -1 -1  0  0  0   0
    // sell1:  0  1  4  4  4  4  10
    // buy2 : -1 -1 -1  4  4  4   4
    // sell2:  0  1  4  4  7  7  14
    public int maxProfit2(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0, buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int i = 0; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }

}

