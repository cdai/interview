package advanced.combinatorial.combination.lc077_combinations;

import java.util.ArrayList;
import java.util.Arrays;
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

    // Much faster, beat 41%
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        doCombine(result, new ArrayList<>(), n, k);
        return result;
    }

    private void doCombine(List<List<Integer>> result,
                           List<Integer> path, int n, int k) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        int last = path.isEmpty() ? 1 : path.get(path.size() - 1) + 1;
        for (int i = last; i <= n; i++) {
            path.add(i);
            doCombine(result, path, n, k);
            path.remove(path.size() - 1);
        }
    }


    // My 2nd: extremely slow due to return value, only beat 8%...
    public List<List<Integer>> combine2(int n, int k) {
        return doCombine2(new ArrayList<>(), n, k);
    }

    private List<List<Integer>> doCombine2(List<Integer> path, int n, int k) {
        if (path.size() == k) {
            return Arrays.asList(new ArrayList<>(path));
        }

        List<List<Integer>> result = new ArrayList<>();
        int last = path.isEmpty() ? 1 : path.get(path.size() - 1) + 1;
        for (int i = last; i <= n; i++) {
            path.add(i);
            result.addAll(doCombine2(path, n, k));
            path.remove(path.size() - 1);
        }
        return result;
    }

    // My 1st
    public List<List<Integer>> combine1(int n, int k) {
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
