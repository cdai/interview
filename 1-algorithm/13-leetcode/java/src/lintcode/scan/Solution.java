package lintcode.scan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().subarraySum(new int[]{-3, 1, 2, -3, 4}));
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

}
