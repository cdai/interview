package advanced.dp.n2.lc322_coinchange;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 * Example 1: coins = [1, 2, 5], amount = 11. return 3 (11 = 5 + 5 + 1)
 * Example 2: coins = [2], amount = 3. return -1.
 * Note: You may assume that you have an infinite number of each kind of coin.
 */
public class Solution {

    // My 2AC: Be aware for MIN problem, MIN, 0, -1... O(coins*N)
    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0 || amount < 0) {
            return -1;
        }

        int[] fewest = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;                        // Note!
            for (int coin : coins) {
                if (i - coin >= 0 && fewest[i - coin] != -1) {  // Note!
                    min = Math.min(min, fewest[i - coin] + 1);
                }
            }
            fewest[i] = (min == Integer.MAX_VALUE) ? -1 : min;  // Note!
        }
        return fewest[amount];
    }

    // My 1AC
    public int coinChange1(int[] coins, int amount) {
        int[] mincnt = new int[amount + 1];
        Arrays.fill(mincnt, Integer.MAX_VALUE);
        mincnt[0] = 0;  // error: amount=0 -> cnt=0

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin == i) {
                    mincnt[i] = 1;
                    break;
                }
                int last = i - coin;
                if (last > 0 && mincnt[last] != Integer.MAX_VALUE) {
                    mincnt[i] = Math.min(mincnt[i], mincnt[last] + 1);
                }
            }
        }
        return mincnt[amount] == Integer.MAX_VALUE ? -1 : mincnt[amount];
    }

    // Greedy doesn't work, since it may be unchangable after trying coin with largest donomination
    // eg.[10,5,1] -> 4. (5 + 2 + 2 + 2)
    public int coinChange2(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        if (amount <= 0) {
            return 0;
        }

        // 1.Sort coins by denomination in descending order
        List<Integer> sortedCoins = new ArrayList<>();
        for (int coin : coins) {
            sortedCoins.add(coin);
        }
        Collections.sort(sortedCoins, Collections.reverseOrder());

        // 2.Try greedily
        int count = 0;
        for (int coin : sortedCoins) {
            if (amount == 0) {
                break;
            }
            if (amount >= coin) {
                count += (amount / coin);
                amount %= coin;
            }
        }
        return amount > 0 ? -1 : count; // error1: try each coin and return -1 finally rather in loop body above
    }

}
