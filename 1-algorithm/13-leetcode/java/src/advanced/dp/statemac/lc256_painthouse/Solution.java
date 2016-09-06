package advanced.dp.statemac.lc256_painthouse;

/**
 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color red;
 * costs[1][2] is the cost of painting house 1 with color green, and so on...
 * Find the minimum cost to paint all houses.
 */
public class Solution {

    // O(N) time + O(1) space. Think of state machine with 3 states.
    public int minCost(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int red = costs[0][0], blue = costs[0][1], green = costs[0][2]; // error: MIN_VAL and 0 both wrong
        for (int i = 1; i < costs.length; i++) {
            int red2 = Math.min(blue, green) + costs[i][0];
            int blue2 = Math.min(red, green) + costs[i][1];
            int green2 = Math.min(red, blue) + costs[i][2];
            red = red2;
            blue = blue2;
            green = green2;
        }
        return Math.min(red, Math.min(blue, green));
    }

}
