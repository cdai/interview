package advanced.scan.twopasses.lc135_candy;

import java.util.Arrays;

/**
 * There are N children standing in a line. Each child is assigned a rating value.
 * You are giving candies to these children subjected to the following requirements:
 *  Each child must have at least one candy.
 *  Children with a higher rating get more candies than their neighbors.
 *  What is the minimum candies you must give?
 */
public class Solution {

    // Why is this correct?
    public int candy(int[] ratings) {
        int[] candy = new int[ratings.length];
        int total = ratings.length;
        for (int i = 0; i < ratings.length - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                total += candy[i] + 1 - candy[i + 1];
                candy[i + 1] = candy[i] + 1;
            }
        }
        for (int i = ratings.length - 1; i > 0; i--) {
            if (ratings[i - 1] > ratings[i] && candy[i - 1] <= candy[i]) {
                total += candy[i] + 1 - candy[i - 1];
                candy[i - 1] = candy[i] + 1;
            }
        }
        return total;
    }

    // It's hard to finish in one pass, since you don't know what happen soon
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int[] candy = new int[n];
        Arrays.fill(candy, 1);

        int total = n, count = 0;
        for (int i = 1; count <= n; i = (i + 1) % n, count++) {
            int neighbor1 = (i > 0) ? i - 1 : n - 1;
            int neighbor2 = (i + 1) % n;

            if (ratings[i] > ratings[neighbor1] && candy[i] <= candy[neighbor1]) {
                total += candy[neighbor1] + 1 - candy[i];
                candy[i] = candy[neighbor1] + 1;
                count = 0;
            }
            if (ratings[i] > ratings[neighbor2] && candy[i] <= candy[neighbor2]) {
                total += candy[neighbor2] + 1 - candy[i];
                candy[i] = candy[neighbor2] + 1;
                count = 0;
            }
        }
        return total;
    }

}
