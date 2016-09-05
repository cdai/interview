package advanced.dp.recover.lc368_largestdivisiblesubset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements
 * in this subset satisfies: Si % Sj = 0 or Sj % Si = 0. If there are multiple solutions, return any subset is fine.
 * Example 1: nums: [1,2,3]. Result: [1,2] (of course, [1,3] will also be ok)
 * Example 2: nums: [1,2,4,8]. Result: [1,2,4,8].
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().largestDivisibleSubset(new int[]{8, 4, 1, 3}));
    }

    // My 2AC: note sub(N) is optimal solution including num N, not global
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int[] sub = new int[nums.length];
        int[] path = new int[nums.length];
        Arrays.fill(sub, 1);                // must set to 1. it's ok to leave path 0.
        Arrays.sort(nums);

        int max = 0, maxIdx = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && sub[j] + 1 > sub[i]) {
                    sub[i] = sub[j] + 1;
                    path[i] = j;
                }
            }
            if (max < sub[i]) {
                max = sub[i];
                maxIdx = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = maxIdx; max-- > 0; i = path[i]) {
            result.add(0, nums[i]);
        }
        return result;
    }

    // Use extra space to reconstruct path
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        int n = nums.length;
        int[] path = new int[n];
        int[] subsets = new int[n];
        Arrays.fill(subsets, 1);
        Arrays.sort(nums);                      // error2: must sort, eg.nums=[8,4,1,3] -> subsets=[1,2,3,4]

        // Compute optimal solution
        int max = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            // Find nearest divisible number    // error1: may not be optimal, eg.[1,2,3,8,9,72]
            for (int j = i - 1; j >= 0; j--) {
                if ((subsets[j] + 1 > subsets[i]) && (nums[i] % nums[j] == 0)) {
                    subsets[i] = subsets[j] + 1;
                    path[i] = j;
                }
            }
            if (max < subsets[i]) {
                max = subsets[i];
                maxIndex = i;
            }
        }

        // Reconstruct optimal solution path
        List<Integer> result = new ArrayList<>();
        int index = maxIndex;
        while (max-- > 0) {
            result.add(nums[index]);
            index = path[index];
        }
        return result;
    }

    public List<Integer> largestDivisibleSubset2(int[] nums) {
        int n = nums.length;
        int[] subsets = new int[n];
        Arrays.fill(subsets, 1);
        Arrays.sort(nums);                      // error2: must sort, eg.nums=[8,4,1,3] -> subsets=[1,2,3,4]

        // Compute optimal solution
        int max = 0;
        for (int i = 0; i < n; i++) {
            // Find nearest divisible number    // error1: may not be optimal, eg.[1,2,3,8,9,72]
            for (int j = i - 1; j >= 0; j--) {
                if ((subsets[j] + 1 > subsets[i]) && (nums[i] % nums[j] == 0)) {
                    subsets[i] = subsets[j] + 1;
                }
            }
            max = Math.max(max, subsets[i]);
        }

        // Reconstruct optimal solution path
        List<Integer> result = new ArrayList<>();
        for (int i = n - 1; i >= 0; i--) {
            int target = subsets[i];
            if (target == max) {
                for (int j = i; j >= 0; j--) {
                    if ((subsets[j] == target) && (nums[i] % nums[j] == 0)) {
                        result.add(0, nums[j]);
                        target--;
                    }
                }
                break;
            }
        }
        return result;
    }

}
