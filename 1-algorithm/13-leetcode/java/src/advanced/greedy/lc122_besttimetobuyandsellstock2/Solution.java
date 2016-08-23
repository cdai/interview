package advanced.greedy.lc122_besttimetobuyandsellstock2;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit.
 * You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class Solution {

    // My 2AC: form [low,high] a increasing window
    // Eg-1: 1 3 9 2 8
    // low : 1     2
    // high: 1 3 9 2 8
    // prof:     8     14
    // Eg-2: 7 6 5 4 3
    // low : 7 6 5 4 3
    // high: 7 6 5 4 3
    // prof:            0
    public int maxProfit(int[] prices) {
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
