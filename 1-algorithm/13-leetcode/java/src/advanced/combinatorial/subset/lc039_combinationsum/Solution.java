package advanced.combinatorial.subset.lc039_combinationsum;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note: All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set [2, 3, 6, 7] and target 7,
 * A solution set is:
 *  [
 *      [7],
 *      [2, 2, 3]
 *  ]
 */
public class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int[] present = new int[candidates.length];
        subset(result, present, candidates, target, 0);
        return result;
    }

    private void subset(List<List<Integer>> result, int[] present,
                        int[] candidates, int target, int k) {
        if (target == 0) {
            List<Integer> r = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < present[i]; j++) { // error3: DONOT change present[]!
                    r.add(candidates[i]);
                }
            }
            result.add(r);
            return;
        }

        if (k == candidates.length) { //error1: individual base case outside previous "if"
            return;
        }

        // Candidates: true, false => 0 ~ unlimited
        // Backtrack (assert: 0 <= k < present/candidates.length)
        int i = 0;
        do {
            present[k] = i;
            subset(result, present, candidates, target, k+1);

            i++;
            target -= candidates[k]; // error2: reduce one each time. assert: candidates must > 0, otherwise dead loop!
        } while (target >= 0);
    }

}
