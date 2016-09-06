package fundamentals.string.search.lc028_implementstrstr;

/**
 * Returns the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
public class Solution {

    // Elegant brute force solution from leetcode discuss
    // JDK String.indexOf() is also brute force. O(MN) time.
    public int strStr(String haystack, String needle) {
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
