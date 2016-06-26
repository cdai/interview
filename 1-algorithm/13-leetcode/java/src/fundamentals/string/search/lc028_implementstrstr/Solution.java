package fundamentals.string.search.lc028_implementstrstr;

/**
 * Returns the index of the first occurrence of needle in haystack,
 * or -1 if needle is not part of haystack.
 */
public class Solution {

    public int strStr(String haystack, String needle) {
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
