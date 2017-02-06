package lintcode.scan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{-3, 1, 2, -3, 4}));
        System.out.println(Arrays.toString(new Solution().subarraySumClosest(new int[]{-3, 1, 1, -3, -5})));
    }

    // 138-Subarray Sum
    // Classic prefix sum O(N) time algorithm.
    public List<Integer> subarraySum(int[] nums) {
        Map<Integer, Integer> presum = new HashMap<>();
        presum.put(0, -1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            if (presum.containsKey(sum)) // check if diff=sum-target(0) in prefix sum
                return Arrays.asList(presum.get(sum) + 1, i);
            presum.put(sum, i);
        }
        return Arrays.asList(-1, -1);
    }

    // 139-Subarray Sum Closest
    // Alternative:
    // 1) Binary search is not working since orginal cumulative array is disorder (negative num possibly)
    // 2) Sort cumulative array then find most closest two is ok, but index is missing (extra Pair class required)
    public int[] subarraySumClosest(int[] nums) {
        TreeMap<Integer, Integer> presum = new TreeMap<>();
        presum.put(0, -1);
        int[] idx = new int[2];
        for (int i = 0, sum = 0, min = Integer.MAX_VALUE; i < nums.length; i++) {
            sum += nums[i];
            Map.Entry[] entries = { presum.floorEntry(sum), presum.ceilingEntry(sum) };
            for (Map.Entry<Integer, Integer> e : entries) {
                if (e != null && Math.abs(sum - e.getKey()) < min) {
                    min = Math.abs(sum - e.getKey()); // sum-key=num closer to zero
                    idx[0] = e.getValue() + 1;
                    idx[1] = i;
                }
            }
            presum.put(sum, i);
        }
        return idx;
    }

    // 405-Submatrix sum
    public int[][] submatrixSum(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            int[] presum = new int[n + 1]; // reuse for same "base line" row-i
            for (int h = i; h < m; h++) {
                for (int j = 1; j <= n; j++) {
                    presum[j] += A[h][j - 1];
                }
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = 0, sum = 0; j <= n; j++) { // Start from j=0 since no (0,0) in map
                    sum += presum[j];
                    if (map.containsKey(sum)) {
                        return new int[][]{{i, map.get(sum)}, {h, j - 1}};
                    }
                    map.put(sum, j);
                }
            }
        }
        return new int[][]{{0, 0}, {0, 0}};
    }

    // 42-Maximum Subarray Sum II
    public int maxTwoSubArrays(ArrayList<Integer> nums) {
        if (nums.isEmpty()) return 0;
        int n = nums.size(), maxSoFar, maxEndHere;
        int[] left = new int[n], right = new int[n];

        left[0] = maxSoFar = maxEndHere = nums.get(0);
        for (int i = 1; i < n; i++) {
            maxEndHere = Math.max(nums.get(i), maxEndHere + nums.get(i));
            maxSoFar = Math.max(maxSoFar, maxEndHere);
            left[i] = maxSoFar;
        }

        right[n - 1] = maxSoFar = maxEndHere = nums.get(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            maxEndHere = Math.max(nums.get(i), maxEndHere + nums.get(i));
            maxSoFar = Math.max(maxSoFar, maxEndHere);
            right[i] = maxSoFar;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) // from (0,1) -> (n-2,n-1)
            max = Math.max(max, left[i] + right[i + 1]);
        return max;
    }

    // 43-Maximum Subarray Sum III
    // dp[i][j] = max(dp[m][j-1] + maxSoFar) Note:
    //  1) maxSoFar calculated backwards.
    //  2) m cannot be < j - 1, since j-1 subarrays require at least j-1 numbers
    public int maxSubArray(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Integer.MIN_VALUE;
                int maxSoFar = Integer.MIN_VALUE, maxEndHere = 0;
                for (int m = i - 1; m >= j - 1; m--) {
                    maxEndHere = Math.max(nums[m], nums[m] + maxEndHere);
                    maxSoFar = Math.max(maxSoFar, maxEndHere);
                    dp[i][j] = Math.max(dp[i][j], dp[m][j - 1] + maxSoFar);
                }
            }
        }
        return dp[n][k];
    }

    // 45-Maximum Subarray Difference
    // Do four times maxSubArray or two times maxSubArray II from two directions
    public int maxDiffSubArrays(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length, lmaxlocal, lminlocal, rmaxlocal, rminlocal;
        int[] lmax = new int[n], lmin = new int[n], rmax = new int[n], rmin = new int[n];
        lmax[0] = lmaxlocal = lmin[0] = lminlocal = nums[0];
        rmax[n - 1] = rmaxlocal = rmin[n - 1] = rminlocal = nums[n - 1];

        for (int i = 1; i < n; i++) {
            lmaxlocal = Math.max(nums[i], nums[i] + lmaxlocal);
            lminlocal = Math.min(nums[i], nums[i] + lminlocal);
            rmaxlocal = Math.max(nums[n - i - 1], nums[n - i - 1] + rmaxlocal);
            rminlocal = Math.min(nums[n - i - 1], nums[n - i - 1] + rminlocal);
            lmax[i] = Math.max(lmax[i - 1], lmaxlocal);
            lmin[i] = Math.min(lmin[i - 1], lminlocal);
            rmax[n - i - 1] = Math.max(rmax[n - i], rmaxlocal);
            rmin[n - i - 1] = Math.min(rmin[n - i], rminlocal);
        }

        int diff = Integer.MIN_VALUE;
        for (int i = 0; i < n - 1; i++) {
            diff = Math.max(diff, lmax[i] - rmin[i + 1]);
            diff = Math.max(diff, -lmin[i] + rmax[i + 1]);
        }
        return diff;
    }

    // 402-Continuous Subarray Sum
    public List<Integer> continuousSubarraySum(int[] A) {
        if (A.length == 0) return new ArrayList<>();
        int maxSoFar = A[0], maxEndHere = A[0];
        int from = 0, to = 0, last = 0;
        for (int i = 1; i < A.length; i++) {
            if (maxEndHere < 0) {
                last = i;
                maxEndHere = A[i];
            } else maxEndHere += A[i];

            if (maxSoFar < maxEndHere) {
                from = last;
                to = i;
                maxSoFar = maxEndHere;
            }
        }
        return Arrays.asList(from, to);
    }

}
