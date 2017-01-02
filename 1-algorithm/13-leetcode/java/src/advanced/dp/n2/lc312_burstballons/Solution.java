package advanced.dp.n2.lc312_burstballons;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 * Find the maximum coins you can collect by bursting the balloons wisely.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        //System.out.println(sol.maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println(sol.maxCoins(new int[]{3, 4, 5}));
        System.out.println(sol.maxCoins(new int[]{8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2}));
    }

    public int maxCoins(int[] A) {
        int[] nums = new int[A.length + 2];
        nums[0] = nums[A.length + 1] = 1;
        System.arraycopy(A, 0, nums, 1, A.length);
        return dfs(nums, new boolean[nums.length], new HashMap<>());
    }

    private int dfs(int[] nums, boolean[] used, Map<String, Integer> memo) {
        String key = getKey(used);//Arrays.toString(used);
        if (memo.containsKey(key)) return memo.get(key);
        int coins = 0;
        for (int i = 0, pre = -1, cur = -1; i < nums.length; i++) {
            if (used[i]) continue;
            if (pre == -1) pre = i;
            else if (cur == -1) cur = i;
            else {
                used[cur] = true;
                System.out.printf("%d-%d-%d\n", nums[pre], nums[cur], nums[i]);
                coins = Math.max(coins, dfs(nums, used, memo) + nums[pre] * nums[cur] * nums[i]);
                used[cur] = false;
                pre = cur;
                cur = i;
            }
        }
        memo.put(key, coins);
        return coins;
    }

    private String getKey(boolean[] used) {
        StringBuilder key = new StringBuilder();
        for (boolean u : used) {
            key.append(u ? "T" : "F");
        }
        return key.toString();
    }

}
