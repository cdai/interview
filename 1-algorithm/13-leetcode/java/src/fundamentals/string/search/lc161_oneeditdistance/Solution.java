package fundamentals.string.search.lc161_oneeditdistance;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isOneEditDistance("abcdef", "abcdef"));
        System.out.println(s.isOneEditDistance("abcXef", "abcdef"));
        System.out.println(s.isOneEditDistance("abcdef", "abcde"));
        System.out.println(s.isOneEditDistance("abde", "abcde"));

        System.out.println(!s.isOneEditDistance("abcdef", "abcdefgh"));
        System.out.println(!s.isOneEditDistance("aXcXef", "abcdef"));
    }

    // My 3AC. O(N) time using one loop.
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 1) return false;
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) == t.charAt(i)) continue;
            if (s.length() == t.length())
                return s.substring(i + 1).equals(t.substring(i + 1));
            if (s.length() > t.length())
                return s.substring(i + 1).equals(t.substring(i));
            return s.substring(i).equals(t.substring(i + 1));
        }
        return true; /* Only last char different. eg."abcd" "abc" */
    }

    // My solution. O(N) time.
    public boolean isOneEditDistance2(String s, String t) {
        if (s.length() == t.length()) {
            for (int i = 0, diff = 0; i < s.length(); i++)
                if (s.charAt(i) != t.charAt(i) && ++diff > 1) return false;
            return true;
        }
        if (Math.abs(s.length() - t.length()) == 1) {
            for (int i = 0; i < Math.min(s.length(), t.length()); i++)
                if (s.charAt(i) != t.charAt(i))
                    return s.length() > t.length()
                                ? s.substring(i + 1).equals(t.substring(i))
                                    : s.substring(i).equals(t.substring(i + 1));
            return true;
        }
        return false;
    }

}
