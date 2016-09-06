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

    // Solution from leetcode discuss: bit manipulation
    // Fastest and common solution for Set intersect, join and diff.
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int size = 1 << nums.length;
        for (int i = 0; i < size; i++) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j < nums.length; j++)
                if (((i >> j) & 1) == 1) sub.add(nums[j]);
            result.add(sub);
        }
        return result;
    }

    // Inspired by leetcode discuss - iterative DP solution
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> sub : result) {
                List<Integer> newSub = new ArrayList<>(sub);
                newSub.add(num);
                tmp.add(newSub);
            }
            result.addAll(tmp);
        }
        return result;
    }

    // My 2nd: from recusive tree view, save all internal and leaf nodes
    public List<List<Integer>> subsets3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doSubset(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void doSubset(List<List<Integer>> result,
                          List<Integer> path,
                          int[] nums, int k) {
        if (k == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        result.add(new ArrayList<>(path));
        for (int i = k; i < nums.length; i++) {
            path.add(nums[i]);
            doSubset(result, path, nums, i + 1);    // error: k + 1
            path.remove(path.size() - 1);
        }
    }

    // My 1st: solution from <The Algorithm Design Manual>
    public List<List<Integer>> subsets1(int[] nums) {
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
