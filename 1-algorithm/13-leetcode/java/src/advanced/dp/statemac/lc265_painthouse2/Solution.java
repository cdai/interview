package advanced.dp.statemac.lc265_painthouse2;

import java.util.PriorityQueue;

/**
 * There are a row of n houses, each house can be painted with one of the k colors.
 * The cost of painting each house with a certain color is different.
 * You have to paint all the houses such that no two adjacent houses have the same color.
 * The cost of painting each house with a certain color is represented by a n x k cost matrix.
 * For example, costs[0][0] is the cost of painting house 0 with color 0;
 *  costs[1][2] is the cost of painting house 1 with color 2, and so on...
 *  Find the minimum cost to paint all houses.
 * Notice: All costs are positive integers.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minCostII(new int[][]{{14,2,11}, {11,14,5}, {14,3,10}}));
    }

    // My 3AC. use foreach for conciseness
    public int minCostII(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int min1 = 0, min2 = 0, color = -1; // Nice!
        for (int[] cost : costs) {
            int min1i = Integer.MAX_VALUE, min2i = min1i, colori = 0;
            for (int i = 0; i < cost.length; i++) {
                int c = cost[i] + (i != color ? min1 : min2);
                if (c < min1i) {
                    min2i = min1i; min1i = c; colori = i;
                } else if (c < min2i)
                    min2i = c;
            }
            min1 = min1i; min2 = min2i; color = colori;
        }
        return min1;
    }

    // O(NK)
    public int minCostII2(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        int min1 = 0, min2 = 0, idx = -1; // Nice!
        for (int i = 0; i < costs.length; i++) {
            int min1i = Integer.MAX_VALUE, min2i = min1i, idxi = 0;
            for (int j = 0; j < costs[i].length; j++) {
                int cost = costs[i][j] + (j != idx ? min1 : min2);
                if (cost < min1i) {
                    min2i = min1i; min1i = cost; idxi = j;
                } else if (cost < min2i)
                    min2i = cost;
            }
            min1 = min1i; min2 = min2i; idx = idxi;
        }
        return min1;
    }

    // O(N*KlogK) time + O(N) space
    // dp[i][j] = Math.min(any k!= j | dp[i-1][k]) + costs[i][j]
    public int minCostII_unnecessaryheap(int[][] costs) {
        if (costs.length == 0 || costs[0].length == 0) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int[] colors = new int[costs[0].length];
        System.arraycopy(costs[0], 0, colors, 0, costs[0].length);

        for (int i = 1; i < costs.length; i++) {
            for (int color : colors) heap.offer(color);

            int[] next = new int[colors.length];
            for (int j = 0; j < next.length; j++) { // O(KlogK)
                heap.remove(colors[j]);
                next[j] = heap.peek() + costs[i][j];
                heap.offer(colors[j]);
            }
            colors = next;
            heap.clear();
        }

        int min = colors[0];
        for (int i = 1; i < colors.length; i++)
            min = Math.min(min, colors[i]);
        return min;
    }

}
