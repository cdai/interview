package advanced.dp.twodim.lc375_guessnumberhigherorlower2;

/**
 * We are playing the Guess Game. The game is as follows: I pick a number from 1 to n.
 * You have to guess which number I picked. Every time you guess wrong, I'll tell you whether the number
 * I picked is higher or lower. However, when you guess a particular number x, and you guess wrong,
 * you pay $x. You win the game when you guess the number I picked.
 * Example: n = 10, I pick 8.
 *  First round:  You guess 5, I tell you that it's higher. You pay $5.
 *  Second round: You guess 7, I tell you that it's higher. You pay $7.
 *  Third round:  You guess 9, I tell you that it's lower. You pay $9.
 *  Game over. 8 is the number I picked.
 * You end up paying $5 + $7 + $9 = $21.
 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
 */
public class Solution {

    public int getMoneyAmount(int n) {
        return minOfMaxCost(new int[n + 1][n + 1], 1, n);
    }

    // Min money to gurrantee win for 1~n: pick each k, min(k + max(cost[1,k-1],cost[1,k+1])).
    private int minOfMaxCost(int[][] dp, int from, int to) {
        if (from >= to) return 0;
        if (dp[from][to] != 0) return dp[from][to];
        int min = Integer.MAX_VALUE;
        for (int i = from; i <= to; i++) {
            min = Math.min(min, i + Math.max(minOfMaxCost(dp, from, i - 1), minOfMaxCost(dp, i + 1, to)));
        }
        dp[from][to] = min;
        return min;
    }

}
