package advanced.combinatorial.combination.lc077_combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example, If n = 4 and k = 2, a solution is:
 *  [
 *      [2,4],
 *      [3,4],
 *      [2,3],
 *      [1,2],
 *      [1,3],
 *      [1,4],
 *  ]
 */
public class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        int[] a = new int[k];   //assert: k>=0
        combine(ret, a, n, 0);
        return ret;
    }

    private void combine(List<List<Integer>> ret, int[] a, int n, int k) {
        if (k == a.length) {
            // Annoying: Arrays.asList only works for Integer[] nor int[]
            ArrayList<Integer> r = new ArrayList<>();
            for (int i : a) {
                r.add(i);
            }
            ret.add(r);
            return;
        }

        // Candidates: a[k]/0 < i < n (assert: k <= a.length-1)
        int min = (k == 0) ? 1 : (a[k-1] + 1);

        // Backtrack
        for (int i = min; i <= n; i++) {
            a[k] = i;
            combine(ret, a, n, k+1);
        }
    }

}
