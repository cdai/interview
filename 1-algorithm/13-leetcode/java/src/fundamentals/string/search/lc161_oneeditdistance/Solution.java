package fundamentals.string.search.lc161_oneeditdistance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class Solution {

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (Math.abs(m - n) > 1) return false;
        for (int i = 0; i < Math.min(m, n); i++) {
            if (s.charAt(i) == t.charAt(i)) continue;
            if (m == n) return s.substring(i + 1).equals(t.substring(i + 1));
            if (m > n) return s.substring(i + 1).equals(t.substring(i));
            else return s.substring(i).equals(t.substring(i + 1));
        }
        return m != n; /* Only last char different. eg."abcd" "abc". Rule out equal case "abc" "abc" */
    }

    @Test
    void testEmpty() {
        Assertions.assertFalse(isOneEditDistance("", ""));
        Assertions.assertTrue(isOneEditDistance("", "a"));
        Assertions.assertTrue(isOneEditDistance("b", ""));
    }

    @Test
    void testNormal() {
        Assertions.assertTrue(isOneEditDistance("abcfg", "abcefg"));
        Assertions.assertTrue(isOneEditDistance("abcefg", "abcfg"));
        Assertions.assertTrue(isOneEditDistance("abcefg", "abcxfg"));
    }

    @Test
    void testFalse() {
        Assertions.assertFalse(isOneEditDistance("abc", "abc")); // equals = zero edit distance!!!
        Assertions.assertFalse(isOneEditDistance("axxbc", "abc"));
        Assertions.assertFalse(isOneEditDistance("abc", "abxxc"));
        Assertions.assertFalse(isOneEditDistance("abcd", "aDcE"));
    }

    @Test
    void testLastChar() {
        Assertions.assertTrue(isOneEditDistance("abcf", "abcfg"));
        Assertions.assertTrue(isOneEditDistance("abcfg", "abcf"));
        Assertions.assertTrue(isOneEditDistance("abcfg", "abcfx"));
    }

    // My 3AC. O(N) time using one loop.
    public boolean isOneEditDistance3(String s, String t) {
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
