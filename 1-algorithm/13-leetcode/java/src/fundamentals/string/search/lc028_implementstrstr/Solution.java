package fundamentals.string.search.lc028_implementstrstr;

import java.util.Arrays;

/**
 * Returns the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.strStr("abcababdababaff", "ababa"));
    }

    public int strStr(String s, String t) {
        int[] f = computePrefixFunc(t);
        System.out.println(Arrays.toString(f));
        int i, j;
        for (i = 0, j = 0; i < s.length() && j < t.length(); i++) { // never back up i
            while (j > 0 && s.charAt(i) != t.charAt(j)) j = f[j - 1]; // back up j recursively until next char matched
            if (s.charAt(i) == t.charAt(j)) j++; // if matched move j, otherwise give up current i and move onto next
        }
        return j == t.length() ? i - j : -1;
    }

    // Similar to strStr except compare t against itself
    private int[] computePrefixFunc(String t) {
        int[] f = new int[t.length()];
        for (int i = 1, j = 0; i < t.length(); i++) { // now i = #matched
            while (j > 0 && t.charAt(i) != t.charAt(j)) j = f[j - 1];
            if (t.charAt(i) == t.charAt(j)) j++;
            f[i] = j;
        }
        return f;
    }

    // 4AC. Brute force from <Algorithm,4e>. Prepare for KMP.
    public int strStr4(String s, String t) {
        if (s == null || t == null) return -1;
        int i, j;
        for (i = 0, j = 0; i < s.length() && j < t.length(); i++) {
            if (s.charAt(i) == t.charAt(j)) j++;
            else { // backup i and j
                i -= j;
                j = 0;
            }
        }
        return j == t.length() ? i - j : -1;
    }

    // My 3AC: O(MN) time
    public int strStr3(String s, String t) {
        if (t.isEmpty()) return 0; // error1: "",""=>0  "a",""=>0
        for (int i = 0; i <= s.length() - t.length(); i++) { // error2: <=
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i + j) != t.charAt(j)) break;
                if (j == t.length() - 1) return i;
            }
        }
        return -1;
    }

    public int strStr_shorter(String s, String t) {
        if (t.isEmpty()) return 0;
        for (int i = 0; i <= s.length() - t.length(); i++)
            for (int j = 0; j < t.length() && s.charAt(i + j) == t.charAt(j); j++)
                if (j == t.length() - 1) return i;
        return -1;
    }

    // Elegant brute force solution from leetcode discuss
    // JDK String.indexOf() is also brute force. O(MN) time.
    public int strStr2(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i; // Nice! Or j=len needs to be checked outside loop which is a mess

                if (i + j == haystack.length())     // These two if are guard condition for the following check
                    return -1;                      // == is okay, since i + j starts from 0

                if (haystack.charAt(i + j) != needle.charAt(j)) break;
            }
        }
    }

    // My 1AC
    public int strStr1(String haystack, String needle) {
        if (haystack == null || needle == null ||
                haystack.length() < needle.length()) { // assert: both haystack and needle isn't NULL
            return -1;
        }

        for (int i = 0; i <= haystack.length() - needle.length(); i++) { // assert: haystack is longer than needle

            // Deal with smaller problem
            int j = 0;
            for ( ; j < needle.length() && (haystack.charAt(i+j) == needle.charAt(j)); j++) // assert: i+j < haystack.length (h.len-n.len+n.len-1=h.len-1 at most)
                ;

            if (j == needle.length()) {
                return i;
            }
        }
        return -1;
    }

}
