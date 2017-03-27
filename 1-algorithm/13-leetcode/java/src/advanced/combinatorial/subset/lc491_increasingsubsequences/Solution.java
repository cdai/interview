package advanced.combinatorial.subset.lc491_increasingsubsequences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array,
 * and the length of an increasing subsequence should be at least 2.
 */
public class Solution {

    // Similar to Subset II but cannot sort then de-duplicate by i > k && nums[i]==nums[i-1]
    public List<List<Integer>> findSubsequences(int[] nums) {
        Set<List<Integer>> ret = new HashSet<>();
        if (nums.length >= 2) {
            dfs(ret, new ArrayList<>(), nums, 0);
        }
        return new ArrayList<>(ret);
    }

    private void dfs(Set<List<Integer>> ret, List<Integer> path, int[] nums, int start) {
        if (path.size() >= 2) {
            ret.add(new ArrayList<>(path));
        }
        for (int i = start; i < nums.length; i++) {
            if (path.isEmpty() || path.get(path.size() - 1) <= nums[i]) {
                path.add(nums[i]);
                dfs(ret, path, nums, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

}
