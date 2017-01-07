package advanced.scan.window.lc438_findallanagramsinastring;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only.
 */
public class Solution {

    // O(N) time. Window approach.
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length(), n = p.length();
        int[] pc = new int[256], sc = new int[256];
        for (int i = 0; i < n; i++) pc[p.charAt(i)]++;

        List<Integer> ret = new ArrayList<>();
        for (int r = 0, l = r - n, diff = n; r <= m; r++, l++) { // left, right of window and diff between S and P
            if (diff == 0) ret.add(l);
            if (r < m && ++sc[s.charAt(r)] <= pc[s.charAt(r)]) diff--; // found valid char, decrement diff
            if (l >= 0 && --sc[s.charAt(l)] < pc[s.charAt(l)]) diff++; // valid char is outside window, increment diff
        }
        return ret;
    }

}
