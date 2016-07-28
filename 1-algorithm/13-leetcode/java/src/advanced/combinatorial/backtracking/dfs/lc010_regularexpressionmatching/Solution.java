package advanced.combinatorial.backtracking.dfs.lc010_regularexpressionmatching;

/**
 *
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
    }

    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int sidx, int pidx) {
        if (sidx == s.length() && pidx == p.length()) {
            return true;    // error1: "a" and "a.*.*.*" is matched, so no chance to handle by code below if only compare sidx or pidx
        }
        if (sidx >= s.length() || pidx >= p.length()) { // error2: either s or p reach the end, eg."abc","a" or "a","abc"
            return false;
        }

        // Parse pattern to see if it's end with '*'
        char pchar = p.charAt(pidx);
        boolean isWildcard = (pidx < p.length() - 1 && p.charAt(pidx + 1) == '*');
        pidx += (isWildcard ? 2 : 1);

        // Invariant: string from [0,sidx) is matched by pattern [0,pidx)
        if (pchar == '.') {
            // "." or ".*" (try to match to i-th char, then check remaining recursively)
            if (isWildcard) {
                for (int i = sidx; i < s.length(); i++) {
                    if (isMatch(s, p, i, pidx)) {   // error1: i not i+1, since * could match 0 char
                        return true;
                    }
                }
            } else {
                if (isMatch(s, p, sidx + 1, pidx)) {
                    return true;
                }
            }
        } else {
            // "c"
            if (isWildcard) {
                // "c*"
                for (int i = sidx; i < s.length(); i++) {
                    if ((i > sidx) && (s.charAt(i) != pchar)) { // error1: don't check if only match 0 char
                        break;
                    }
                    if (isMatch(s, p, i, pidx)) {
                        return true;
                    }
                }
            } else {
                // "c"
                if ((s.charAt(sidx) == pchar) && isMatch(s, p, sidx + 1, pidx)) {
                    return true;
                }
            }
        }
        return false;
    }

}
