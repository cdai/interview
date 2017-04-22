package fundamentals.string.search.lc521_longestuncommonsubsequence;

/**
 * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings.
 * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
 */
public class Solution {

    public int findLUSlength(String a, String b) {
        return a.equals(b) ? -1 : Math.max(a.length(), b.length());
    }

    // Only several cases possible:
    // 1) a="abcd", b="ab"  => lus="abcd"
    // 2) a="ab",  b="abcd" => lus="abcd"
    // 3) a="def", b="def"  => lus=""
    // 4) a="def", b="deg"  => lus="def" or "deg"
    public int findLUSlength_my(String a, String b) {
        int m = a.length(), n = b.length();
        return (m != n) ? Math.max(m, n) : (a.equals(b) ? -1 : m);
    }

}
