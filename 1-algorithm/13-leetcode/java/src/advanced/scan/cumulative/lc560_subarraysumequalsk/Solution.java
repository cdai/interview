package advanced.scan.cumulative.lc560_subarraysumequalsk;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 */
public class Solution {

    public int subarraySum(int[] nums, int k) {
        Map<Integer,Integer> presum = new HashMap<>();
        presum.put(0, 1); // Don't forget!
        int cnt = 0;
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            cnt += presum.getOrDefault(sum - k, 0);
            presum.put(sum, presum.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

}
