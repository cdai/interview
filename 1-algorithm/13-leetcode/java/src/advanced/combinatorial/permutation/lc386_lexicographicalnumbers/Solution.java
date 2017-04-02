package advanced.combinatorial.permutation.lc386_lexicographicalnumbers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given an integer n, return 1 - n in lexicographical order.
 */
public class Solution {

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(ret, i, n);
        }
        return ret;
    }

    private void dfs(List<Integer> ret, int path, int n) {
        if (path > n) return;
        ret.add(path);
        for (int i = 0; i <= 9; i++) {
            dfs(ret, path * 10 + i, n);
        }
    }

    // TLE
    public List<Integer> lexicalOrder1(int n) {
        Queue<Integer> q = new PriorityQueue<>(Comparator.comparing(String::valueOf));
        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }

        List<Integer> ret = new ArrayList<>();
        while (!q.isEmpty()) {
            ret.add(q.poll());
        }
        return ret;
    }

}
