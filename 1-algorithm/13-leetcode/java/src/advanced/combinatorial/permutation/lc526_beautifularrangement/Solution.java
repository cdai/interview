package advanced.combinatorial.permutation.lc526_beautifularrangement;

/**
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully
 * if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:
 *
 * 1) The number at the ith position is divisible by i.
 * 2) i is divisible by the number at the ith position.
 *
 * Now given N, how many beautiful arrangements can you construct?
 */
public class Solution {

    public int countArrangement(int N) {
        return dfs(N, new boolean[N + 1], 1);
    }

    private int dfs(int n, boolean[] used, int k) {
        if (k == n + 1) return 1;
        int cnt = 0;
        for (int i = 1; i <= n; i++) { // If not used and "beautiful", put number i in position k
            if (used[i] || !(i % k == 0 || k % i == 0)) continue;
            used[i] = true;
            cnt += dfs(n, used, k + 1);
            used[i] = false;
        }
        return cnt;
    }

}
