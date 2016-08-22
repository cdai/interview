package advanced.dp.twodim.lc123_besttimetobuyandsellstock3;

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

    // My 2AC: O(N) time, two passes
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) {
            return 0;
        }

        // Max profit if buy and sell only once (Problem-I)
        int[] max1 = new int[n];
        int low = prices[0];
        for (int i = 1; i < n; i++) {
            max1[i] = Math.max(max1[i - 1], prices[i] - low);
            low = Math.min(low, prices[i]);
        }

        int[] max2 = new int[n];
        int high = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            max2[i] = Math.max(max2[i + 1], high - prices[i]);
            high = Math.max(high, prices[i]);
        }

        int result = max2[0];
        for (int i = 1; i < n; i++) {
            result = Math.max(result, max1[i - 1] + max2[i]);
        }
        return result;
    }

    // Wrong! Eg.[1,2,4,2,5,7,2,4,9,0] -> [1,7],[2,9] not [2,7],[2,9]
    public int maxProfit2(int[] prices) {
        int profit1 = 0, profit2 = 0;
        int low = Integer.MAX_VALUE, high = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < high) {
                int profit = high - low;
                if (profit >= profit1) {    // error1: must be >=
                    profit2 = profit1;      // error2: save to profit2
                    profit1 = profit;
                } else if (profit > profit2) {
                    profit2 = profit;
                } /* profit <= profit2 <= profit1 */
                high = low = price;
            } else {
                high = price;
            }
        }

        int profit = high - low;
        if (profit >= profit1) {
            profit2 = profit1;
            profit1 = profit;
        } else if (profit > profit2) {
            profit2 = profit;
        }
        return profit1 + profit2;
    }

    // eg.   [1, 2, 5, 0, 3, 1, 10]
    // left : 0  1  4  0  3  0  10   (Wrong!!!)
    // right: 9  8  5 10  7  9   0
    // left : 0  1  4  4  4  4  10   (Correct DP!)
    // right:10 10 10 10  9  9   0
    public int maxProfit1(int[] prices) {
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
    public int maxProfit1_2(int[] prices) {
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

