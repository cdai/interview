package advanced.combinatorial.backtracking.dfs.lc010_regularexpressionmatching;

/**
 * Implement regular expression matching with support for '.' and '*'.
 *  '.' Matches any single character.
 *  '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial). The function prototype should be: bool isMatch(const char *s, const char *p)
 * Some examples:
 *  isMatch("aa","a") → false
 *  isMatch("aa","aa") → true
 *  isMatch("aaa","aa") → false
 *  isMatch("aa", "a*") → true
 *  isMatch("aa", ".*") → true
 *  isMatch("ab", ".*") → true
 *  isMatch("aab", "c*a*b") → true
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isMatch("aab", "c*a*b"));
        System.out.println(new Solution().isMatch("a", ".*.."));
    }

    public boolean isMatch(String s, String p) {
        return isMatch(s, p, 0, 0);
    }

    private boolean isMatch(String s, String p, int sidx, int pidx) {
        if (pidx == p.length()) {
            return (sidx == s.length());
        }

        char schar = (sidx == s.length()) ? 0 : s.charAt(sidx); // Keep schar at the end, try if pattern could go to the end
        char pchar = p.charAt(pidx);
        if (pidx < p.length() - 1 && p.charAt(pidx + 1) == '*') {
            for (; sidx < s.length() && (((schar = s.charAt(sidx)) == pchar) || (pchar == '.' && schar != 0)); sidx++) {
                if (isMatch(s, p, sidx, pidx + 2)) {
                    return true;
                }
            }
            return isMatch(s, p, sidx, pidx + 2); // Key!!! Loop above could never run, so try match 0 char
        } else {
            if ((schar == pchar) || (pchar == '.' && schar != 0)) {
                return isMatch(s, p, sidx + 1, pidx + 1);
            }
        }
        return false;
    }

    private boolean isMatch2(String s, String p, int sidx, int pidx) {
        if (sidx == s.length() && pidx == p.length()) {
            return true;    // error1: "a" and "a.*.*.*" is matched, so no chance to handle by code below if only compare sidx or pidx
        }
        /*if (sidx >= s.length() || pidx >= p.length()) { // error2: eg."abc","a" or "a","abc" is correct, but "a","a.*.*" is wrong
            return false;
        }*/

        // Parse pattern to see if it's end with '*'
        char pchar = (pidx >= p.length()) ? 0 : p.charAt(pidx);
        char schar = (sidx >= s.length()) ? 0 : s.charAt(sidx);
        boolean isWildcard = (pidx < p.length() - 1 && p.charAt(pidx + 1) == '*');
        pidx += (isWildcard ? 2 : 1);

        // Invariant: string from [0,sidx) is matched by pattern [0,pidx)
        if (pchar == '.') {
            if (isWildcard) {   // ".*": try to match to i-th char, then check remaining recursively)
                for (int i = sidx - 1; i < s.length(); i++) { // error1: i=sidx-1, since * could match 0 char
                    if (isMatch(s, p, i + 1, pidx)) {
                        return true;
                    }
                }
            } else {    // "." or
                if (isMatch(s, p, sidx + 1, pidx)) {
                    return true;
                }
            }
        } else {
            if (isWildcard) {   // "c*"
                for (int i = sidx - 1; i < s.length(); i++) {
                    if (i >= sidx && schar != pchar) { // error1: don't check if only match 0 char
                        break;
                    }
                    if (isMatch(s, p, i + 1, pidx)) {
                        return true;
                    }
                }
            } else {    // "c"
                if ((schar == pchar) && isMatch(s, p, sidx + 1, pidx)) {
                    return true;
                }
            }
        }
        return false;
    }

}

