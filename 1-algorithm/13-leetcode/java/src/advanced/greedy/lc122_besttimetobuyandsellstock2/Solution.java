package advanced.greedy.lc122_besttimetobuyandsellstock2;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.maxProfit(new int[]{3, 2, 6, 5, 0, 3}));
    }

    // 4AC. Not greedy strategy. Not good.
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int max = 0, buy = prices[0], n = prices.length;
        for (int i = 1; i < n; i++) {
            if (prices[i] < prices[i - 1]) { // increasing sequence
                max += prices[i - 1] - buy;
                buy = prices[i];
            }
        }
        max += prices[n - 1] - buy;
        return max;
    }

    // My 3AC: accumulate if cur > prev (low)
    public int maxProfit3(int[] prices) {
        int profit = 0, low = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price > low) profit += price - low;
            low = price;
        }
        return profit;
    }

    // My 2AC: form [low,high] a increasing window
    // Eg-1: 1 3 9 2 8
    // low : 1     2
    // high: 1 3 9 2 8
    // prof:     8     14
    // Eg-2: 7 6 5 4 3
    // low : 7 6 5 4 3
    // high: 7 6 5 4 3
    // prof:            0
    public int maxProfit2(int[] prices) {
        int profit = 0, low = Integer.MAX_VALUE, high = Integer.MAX_VALUE;
        for (int price : prices) {
            if (price < high) {
                profit += high - low;
                high = low = price;
            } else {
                high = price;
            }
        }
        return profit + (high - low); // don't forget last batch and high must be MAX_INT
    }

    // Greedy
    public int maxProfit1(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                max += (prices[i] - prices[i-1]);
            }
        }
        return max;
    }

}
