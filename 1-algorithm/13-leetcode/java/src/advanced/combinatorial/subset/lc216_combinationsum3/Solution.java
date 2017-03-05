package advanced.combinatorial.subset.lc216_combinationsum3;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Example 1: Input: k = 3, n = 7. Output: [[1,2,4]]
 * Example 2: Input: k = 3, n = 9. Output: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class Solution {

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new ArrayList<>();
        dfs(ret, new ArrayList<>(), k, n, 1);
        return ret;
    }

    private void dfs(List<List<Integer>> ret, List<Integer> path, int k, int n, int start) {
        if (n <= 0 || k <= 0) {
            if (n == 0 && k == 0) ret.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= 9; i++) {
            path.add(i);
            dfs(ret, path, k - 1, n - i, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // My 2nd: O(9!) time, O(K) space
    public List<List<Integer>> combinationSum3_2(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        doCombineSum(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void doCombineSum(List<List<Integer>> result,
                              List<Integer> path, int k, int n, int start) {
        if (path.size() == k || n <= 0) {
            if (path.size() == k && n == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            path.add(i);
            doCombineSum(result, path, k, n - i, i + 1);
            path.remove(path.size() - 1);
        }
    }

    // My 1st
    public List<List<Integer>> combinationSum3_old(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combine(result, new ArrayList<>(), k, n);
        return result;
    }

    private void combine(List<List<Integer>> result, List<Integer> path, int k, int n) {
        if (path.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }

        int start = (path.isEmpty()) ? 1 : (path.get(path.size() - 1) + 1);
        for (int i = start; (i <= n) && (i <= 9); i++) {
            path.add(i);
            combine(result, path, k, n - i);
            path.remove(path.size() - 1);
        }
    }

}
