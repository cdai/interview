package fundamentals.string.search.lc010_regularexpressionmatching;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        System.out.println(new Solution().isMatch("aaa", "a*a"));
//        System.out.println(new Solution().isMatch("aab", "c*a*b"));
//        System.out.println(new Solution().isMatch("a", ".*.."));
    }

    // 1.Must check s.isEmpty() later, eg."a","a**" -> "", "**"
    // 2.Don't match * to zero to all, just zero with p[1..] and one with p[0..]
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) ||
                    (!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p));
        }
        return !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.') && isMatch(s.substring(1), p.substring(1));
    }

    // 1.Build the NFA transition table of pattern P to represent "empty transition" for "*", "(", "|".
    // 2.Iterate through string S, save all "reachable" states from previous states. (multiple-source reachability problem)
    // 3.Finally, if any path reaches the final state, then S and P is matched.
    public boolean isMatch3_nfa(String s, String p) {
        // Build NFA of pattern
        int n = p.length();
        List<Integer>[] trans = new List[n + 1]; // final state
        for (int i = 0; i < n + 1; i++) trans[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (p.charAt(i) == '*') trans[i].add(i + 1);
            else if (i < n - 1 && p.charAt(i + 1) == '*') { //look ahead
                trans[i + 1].add(i);
                trans[i].add(i + 1);
            }
        }

        // Match str: compute possible states for s[i+1] in each iteration
        Set<Integer> states = dfs(new HashSet<>(), Collections.singleton(0), trans);
        for (int i = 0; i < s.length(); i++) {
            Set<Integer> match = new HashSet<>();
            for (int v : states)
                if (v < n && (p.charAt(v) == s.charAt(i) || p.charAt(v) == '.'))
                    match.add(v + 1);
            states = dfs(new HashSet<>(), match, trans);
        }

        // Check if any path reach final state
        for (int v : states) if (v == n) return true;
        return false;
    }

    // Multiple-source reachability
    private Set<Integer> dfs(Set<Integer> dst, Set<Integer> src, List<Integer>[] trans) {
        dst.addAll(src);
        for (int s : src)
            for (int v : trans[s])
                if (!dst.contains(v)) dst.addAll(dfs(dst, Collections.singleton(v), trans));
        return dst;
    }

    // My 2AC: O(M*N) time?
    // Note: Must match zero (move p) first, otherwise it terminates early if s reach end
    // s="aaa", p="a*a"
    //  s=aaa, p=a*a
    //  s=aaa, p=a
    //  s=aa, p=
    //  s=aa, p=a*a
    //  s=aa, p=a
    //  s=a, p=
    //  s=a, p=a*a
    //  s=a, p=a
    //  s=, p=
    // true
    public boolean isMatch2(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        // encounter star: match zero or match one only if . or char matched
        if (p.length() > 1 && p.charAt(1) == '*')
            return isMatch(s, p.substring(2)) ||
                    ((!s.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0))) && isMatch(s.substring(1), p));

        // encounter dot: match one letter
        if (p.charAt(0) == '.')
            return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));

        // just normal char
        return !s.isEmpty() && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }

    // Must match zero first!!!
    public boolean isMatch_wrong(String s, String p) {
        System.out.println("s=" + s + ", p=" + p);
        if (p.isEmpty()) return s.isEmpty();

        // encounter star: match zero or match one only if . or char matched
        if (p.length() > 1 && p.charAt(1) == '*') {
            if (p.charAt(0) == '.' || (!s.isEmpty() && s.charAt(0) == p.charAt(0)))
                return isMatch(s.substring(1), p);
            return isMatch(s, p.substring(2));
        }

        // encounter dot: match one letter
        if (p.charAt(0) == '.')
            return !s.isEmpty() && isMatch(s.substring(1), p.substring(1));

        // just normal char
        return !s.isEmpty() && s.charAt(0) == p.charAt(0) && isMatch(s.substring(1), p.substring(1));
    }

    // My 1AC
    public boolean isMatch1(String s, String p) {
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

