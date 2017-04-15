package advanced.combinatorial.subset.lc494_targetsum;

import java.util.LinkedList;
import java.util.Queue;

/**
 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S.
 * Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 */
public class Solution {

    // TLE...
    public int findTargetSumWays(int[] nums, int S) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = q.size(); j > 0; j--) {
                int sum = q.poll();
                q.offer(sum + nums[i]);
                q.offer(sum - nums[i]);

                if (i == n - 1) {
                    if (sum + nums[i] == S) cnt++;
                    if (sum - nums[i] == S) cnt++;
                }
            }
        }
        return cnt;
    }

    public int findTargetSumWays_queue(int[] nums, int S) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        for (int num : nums) {
            for (int i = q.size(); i > 0; i--) {
                int sum = q.poll();
                q.offer(sum + num);
                q.offer(sum - num);
            }
        }

        int cnt = 0;
        while (!q.isEmpty()) {
            if (q.poll() == S) cnt++;
        }
        return cnt;
    }

    public int findTargetSumWays_dfs(int[] nums, int S) {
        return dfs(nums, 0, S, 0);
    }

    private int dfs(int[] nums, int sum, int target, int k) {
        if (nums.length == k) {
            return sum == target ? 1 : 0;
        }
        return dfs(nums, sum + nums[k], target, k + 1) +
                dfs(nums, sum - nums[k], target, k + 1);
    }

}
