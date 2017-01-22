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
