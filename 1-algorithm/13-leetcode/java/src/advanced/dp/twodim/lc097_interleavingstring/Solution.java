package advanced.dp.twodim.lc097_interleavingstring;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example, Given: s1 = "aabcc", s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class Solution {

    // "DP table represents if s3 is interleaving at (i+j)th position
    // when s1 is at ith position, and s2 is at jth position. 0th position means empty string."
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray(), c3 = s3.toCharArray();
        boolean[][] dp = new boolean[c1.length + 1][c2.length + 1];
        dp[0][0] = true;                            // Add 1st col and 1st row as buffer (for i-1,j-1)

        for (int i = 1; i <= c2.length; i++)        // note: off-by-1 between DP index and Str index
            dp[0][i] = dp[0][i - 1] && c2[i - 1] == c3[i - 1];

        for (int i = 1; i <= c1.length; i++) {
            dp[i][0] = dp[i - 1][0] && c1[i - 1] == c3[i - 1];
            for (int j = 1; j <= c2.length; j++)
                dp[i][j] = (dp[i - 1][j] && c1[i - 1] == c3[i + j - 1])
                        || (dp[i][j - 1] && c2[j - 1] == c3[i + j - 1]);
        }
        return dp[c1.length][c2.length];
    }

    // Why BFS work? Because this tree is very skinny. Think about it!
    // Branching appears only when s1[0]=s2[0]=s3[0] and then split into 2 only.
    // But due to duplicate XXX, don't forget to use Set
    public boolean isInterleave_bfs(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] visited = new boolean[s1.length() + 1][s2.length() + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            if (visited[p[0]][p[1]]) continue;
            if (p[0] == s1.length() && p[1] == s2.length()) return true;

            if (p[0] < s1.length() && s1.charAt(p[0]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0] + 1, p[1]});
            if (p[1] < s2.length() && s2.charAt(p[1]) == s3.charAt(p[0] + p[1]))
                queue.offer(new int[]{p[0], p[1] + 1});
            visited[p[0]][p[1]] = true;
        }
        return false;
    }

    private boolean[][] visited;

    public boolean isInterleave_dfs(String s1, String s2, String s3) { // memorization could help
        if (visited == null) visited = new boolean[s1.length() + 1][s2.length() + 1];
        if (s3.isEmpty()) return s1.isEmpty() && s2.isEmpty();
        if (visited[s1.length()][s2.length()]) return false; // must be false!

        if (!s1.isEmpty() && s1.charAt(0) == s3.charAt(0)
                && isInterleave(s1.substring(1), s2, s3.substring(1))) return true;
        if (!s2.isEmpty() && s2.charAt(0) == s3.charAt(0)
                && isInterleave(s1, s2.substring(1), s3.substring(1))) return true;
        visited[s1.length()][s2.length()] = true;
        return false;
    }

}
