package advanced.combinatorial.backtracking.dfs.lc044_wildcardmatching;

/**
 *
 */
public class Solution {

    public boolean isMatch(String s, String p) {
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
