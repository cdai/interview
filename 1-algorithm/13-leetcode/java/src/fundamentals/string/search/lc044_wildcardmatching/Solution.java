package fundamentals.string.search.lc044_wildcardmatching;

/**
 *
 */
public class Solution {

    // O(MN) DP solution.
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n && (p.charAt(j - 1) == '*'); j++)
            dp[0][j] = true;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; // match zero or 1+ char (eg. a, aa, abc...)
                } else {
                    dp[i][j] = dp[i - 1][j - 1] && (p.charAt(j - 1) == '?' || p.charAt(j - 1) == s.charAt(i - 1));
                }
            }
        }
        return dp[m][n];
    }

    // TLE again... but clear logic
    public boolean isMatch31(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.charAt(0) == '*') { // Match 0 to all char, s could be ""
            return isMatch(s, p.substring(1)) || (!s.isEmpty() && isMatch(s.substring(1), p));
        }
        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') && isMatch(s.substring(1), p.substring(1));
    }

    // TLE: "aaabbbaabaaaaababaabaaabbabbbbbbbbaabababbabbbaaaaba"
    //      "a*******b"
    public boolean isMatch2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.charAt(0) == '*') /* star match zero or many (irrelevant to previous char) */
            return isMatch(s, p.substring(1)) || (!s.isEmpty() && isMatch(s.substring(1), p));
        if (p.charAt(0) == '?')
            return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));
        return !s.isEmpty() && p.charAt(0) == s.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }

    // My 1AC
    public boolean isMatch1(String s, String p) {
        // Merge *
        StringBuilder ptrim = new StringBuilder();
        char last = 0;
        for (char c : p.toCharArray()) {
            if (last == '*' && c == '*') {
                continue;
            }
            ptrim.append(c);
            last = c;
        }
        return isMatch(s, ptrim.toString(), 0, 0);
    }

    private boolean isMatch(String s, String p, int sidx, int pidx) {
        if (pidx == p.length()) {
            return (sidx == s.length());
        }

        char schar = (sidx == s.length()) ? 0 : s.charAt(sidx); // Keep schar at the end, try if pattern could go to the end
        char pchar = p.charAt(pidx);
        if (pchar == '*') {
            for (; sidx < s.length(); sidx++) {
                if (isMatch(s, p, sidx, pidx + 1)) {
                    return true;
                }
            }
            return isMatch(s, p, sidx, pidx + 1);
        } else {
            if ((schar == pchar) || (pchar == '?' && schar != 0)) {
                return isMatch(s, p, sidx + 1, pidx + 1);
            }
        }
        return false;
    }

}
