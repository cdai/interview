package advanced.dp.palindrome.lc132_palindromepartitioning2;

import java.util.Arrays;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab", return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().minCut("abadfewfsgasgsagdggggggggg"));
    }

    public int minCut(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int[] f = new int[s.length()];  // f(n) means: length of last palindrome which is from [f(n), n]
        f[0] = 0;
        int start = 0, end = 0;         // Start and end of same char sequence to speed up comparing

        // Transfer from 1 to n
        for (int i = 1; i < s.length(); i++) {
            int last = i - 1;
            if (start == f[last] && end == last && s.charAt(last) == s.charAt(i)) { // Case-1: [e,e] or [eeee,e]
                f[i] = f[last];
                end = i;
            } else {
                if (f[last] > 0 && s.charAt(f[last] - 1) == s.charAt(i)) {          // Case-2: [aeee,a]
                    f[i] = f[last] - 1;
                } else {                                                            // Case-3: [eee,a] or [f,eee,a]/[f,ebe,a]
                    f[i] = i;
                }
                start = i;
                end = i;
            }
            System.out.println(Arrays.toString(f));
        }

        // Get final result: [0,0,...,1] -> cut=1
        int cut = 0, i = f.length - 1;
        while (f[i] > 0) {
            System.out.println("cut: " + cut + ", i: " + i);
            cut++;
            i = f[i] - 1;
        }
        return cut - 1;
    }

}
