package advanced.combinatorial.subset.lc090_subsets2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note: The solution set must not contain duplicate subsets.
 * For example, If nums = [1,2,2], a solution is:
 *  [
 *      [2],
 *      [1],
 *      [1,2,2],
 *      [2,2],
 *      [1,2],
 *      []
 *  ]
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().subsetsWithDup(new int[]{1, 2, 2, 2}));
    }

    // My 2nd from leetcode discuss - iterative DP solution
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        Arrays.sort(nums); // must sort for this problem

        int size = 0;
        for (int i = 0, j = 0; i < nums.length; i++) {
            List<List<Integer>> tmp = new ArrayList<>();
            j = (i > 0 && nums[i] == nums[i - 1]) ? size : 0;
            size = result.size();
            for ( ; j < result.size(); j++) {
                List<Integer> newSub = new ArrayList<>(result.get(j));
                newSub.add(nums[i]);
                tmp.add(newSub);
            }
            result.addAll(tmp);
        }
        return result;
    }

    // My 1st: solution from <The Algorithm Design Manual>
    public List<List<Integer>> subsetsWithDup1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        boolean[] presents = new boolean[nums.length];
        Arrays.sort(nums);
        subset(result, presents, nums, 0);
        return new ArrayList<>(result);
    }

    private void subset(Set<List<Integer>> result, boolean[] presents, int[] nums, int k) {
        if (k == nums.length) {
            List<Integer> r = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (presents[i]) {
                    r.add(nums[i]);
                }
            }

            if (!result.contains(r)) {
                result.add(r);
            }
            return;
        }

        presents[k] = false;
        subset(result, presents, nums, k+1);

        presents[k] = true;
        subset(result, presents, nums, k+1);
    }

}
