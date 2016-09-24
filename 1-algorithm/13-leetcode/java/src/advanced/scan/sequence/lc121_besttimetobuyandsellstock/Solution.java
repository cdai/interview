package advanced.scan.sequence.lc121_besttimetobuyandsellstock;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction
 * (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 */
public class Solution {

    // My 2AC: O(N)
    // Update low if found lower point or update max if found higher point
    public int maxProfit(int[] prices) {
        int low = Integer.MAX_VALUE, max = 0;
        for (int price : prices) {
            if (price < low) low = price;
            else max = Math.max(max, price - low);
        }
        return max;
    }

    // Solution from leetcode discuss
    // A little slow, but really clear and short after getting rid of the If
    // Since Math.min/max has their own If inside.
    public int maxProfit_short(int[] prices) {
        int low = Integer.MAX_VALUE, max = 0;
        for (int price : prices) {
            low = Math.min(low, price);
            max = Math.max(max, price - low);
        }
        return max;
    }

    // 1AC
    public int maxProfit1(int[] prices) {
        // Max profit exists between low and high point followed
        // So find low and high, update low if find lower point
        // Update max if current gap = high - low is greater
        int low = Integer.MAX_VALUE, high = 0, max = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < low) {
                low = prices[i];
                high = 0;
            } else {
                if (prices[i] > high) {
                    high = prices[i];
                    max = Math.max(max, high - low);
                } else {
                    // go ahead...
                }
            }
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        return max;
    }

}
