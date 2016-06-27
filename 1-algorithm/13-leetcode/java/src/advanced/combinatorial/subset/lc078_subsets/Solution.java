package advanced.combinatorial.subset.lc078_subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example, If nums = [1,2,3], a solution is:
 *  [
 *      [3],
 *      [1],
 *      [2],
 *      [1,2,3],
 *      [1,3],
 *      [2,3],
 *      [1,2],
 *      []
 *  ]
 */
public class Solution {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] present = new boolean[nums.length];
        subset(result, present, nums, 0);
        return result;
    }

    private void subset(List<List<Integer>> result, boolean[] present, int[] nums, int k) {
        if (k == nums.length) {
            List<Integer> r = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (present[i]) {
                    r.add(nums[i]);
                }
            }
            result.add(r);
            return;
        }

        // Candidates: true, false
        // Backtrack (assert: 0 <= k < present/candidates.length)
        present[k] = false;
        subset(result, present, nums, k+1);

        present[k] = true;
        subset(result, present, nums, k+1);
    }

}
