package advanced.scan.sequence.lc522_longestuncommonsubsequence2;

import java.util.Arrays;

/**
 * Given a list of strings, you need to find the longest uncommon subsequence among them.
 * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any subsequence of the other strings.
 */
public class Solution {

    // LUS must be entire string of input array!
    public int findLUSlength(String[] strs) {
        // Sort by length in descending order to ensure first uncommon subseq is longest
        Arrays.sort(strs, (s1, s2) -> Integer.compare(s2.length(), s1.length()));

        // Compare each str if it's subseq of any other except itself
        int n = strs.length;
        for (int i = 0; i < n; i++) {
            int subseq = n;
            for (int j = 0; j < n; j++, subseq--) {
                if (i != j && isSubseq(strs[i], strs[j])) break;
            }
            if (subseq == 0) return strs[i].length();
        }
        return -1;
    }

    // Leetcode-392 Is Subsequence: O(M+N), binary search, DP...
    private boolean isSubseq(String s1, String s2) {
        int i = 0, m = s1.length(), n = s2.length();
        for (int j = 0; i < m && j < n; j++) {
            if (s1.charAt(i) == s2.charAt(j)) i++;
        }
        return i == m;
    }

}
