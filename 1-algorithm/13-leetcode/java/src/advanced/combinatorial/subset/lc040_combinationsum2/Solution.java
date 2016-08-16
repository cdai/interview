package advanced.combinatorial.subset.lc040_combinationsum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T. Each number in C may only be used once in the combination.
 * Note: All numbers (including target) will be positive integers. The solution set must not contain duplicate combinations.
 * For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
 * A solution set is:
 *  [
 *      [1, 7],
 *      [1, 2, 5],
 *      [2, 6],
 *      [1, 1, 6]
 *  ]
 */
public class Solution {

    // My 2nd: O(2^N) time, O(N) space
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        doCombineSum(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void doCombineSum(List<List<Integer>> result,
                              List<Integer> path, int[] candidates, int target, int k) {
        if (target <= 0) {
            if (target == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = k; i < candidates.length; i++) {
            if (i > k && candidates[i] == candidates[i - 1]) { // Very nice!!! Or use prev, both way is better than mine
                continue;
            }
            path.add(candidates[i]);
            doCombineSum(result, path, candidates, target - candidates[i], i + 1);
            path.remove(path.size() - 1);

            /*while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) { // bypass duplidate candidates
                i++;
            }*/
            /* candidates[i] <> candidates[i + 1], then i++ in for move i to what we want */
        }
    }

    // My 1st
    public List<List<Integer>> combinationSum2_old(int[] candidates, int target) {
        // Avoid duplicates: List equals cannot handle like [2,1,5] and [1,2,5]
        Set<List<Integer>> result = new HashSet<>();
        Arrays.sort(candidates);

        boolean[] presents = new boolean[candidates.length];
        subset(result, presents, candidates, target, 0);
        return new ArrayList<>(result);
    }

    private void subset(Set<List<Integer>> result, boolean[] presents,
                        int[] candidates, int target, int k) {
        if (target == 0) {
            List<Integer> r = new ArrayList<>();
            for (int i = 0; i < k; i++) { // must be <k (not candidates.length) to avoid gabage data
                if (presents[i]) {
                    r.add(candidates[i]);
                }
            }

            if (!result.contains(r)) {
                result.add(r);
            }
            return;
        }

        if (k == candidates.length || target < 0) { // could cause time limit exceeded if put outside previous if
            return;
        }

        presents[k] = false;
        subset(result, presents, candidates, target, k+1);

        presents[k] = true;
        subset(result, presents, candidates, target-candidates[k], k+1);
    }

}
