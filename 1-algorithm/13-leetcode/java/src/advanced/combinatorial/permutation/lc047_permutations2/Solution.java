package advanced.combinatorial.permutation.lc047_permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example, [1,1,2] have the following unique permutations:
 *  [
 *      [1,1,2],
 *      [1,2,1],
 *      [2,1,1]
 *  ]
 */
public class Solution {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> path = new LinkedHashSet<>();
        Arrays.sort(nums);
        permute(result, path, nums, 0);
        return result;
    }

    private void permute(List<List<Integer>> result, Set<Integer> path, int[] nums, int k) {
        if (k == nums.length) {
            List<Integer> r = new ArrayList<>();
            for (int i : path) {
                r.add(nums[i]);
            }
            result.add(r);
            return;
        }

        Integer prev = null; // error2: failed to assume nums all positive and use -1 as indicator
        for (int i = 0; i < nums.length; i++) {
            if ((prev != null && nums[i] == prev)
                    || path.contains(i)) {  // error1: path could possibly contain nums[i],
                continue;                   // but from another location, so record i instead
            }

            path.add(i);
            permute(result, path, nums, k+1);
            path.remove(i);

            prev = nums[i];
        }
    }

}
