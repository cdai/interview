package advanced.scan.window.lc159_longeststringwithatmosttwodistinctcharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.longestSubstr("eceba"));
        System.out.println(sol.longestSubstr("ecebeefff"));
    }

    public int longestSubstr(String s) {
        Map<Character, Integer> cntmap = new HashMap<>();
        char[] c = s.toCharArray();
        int from = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            cntmap.put(c[i], cntmap.getOrDefault(c[i], 0) + 1);
            if (cntmap.size() <= 2) {
                max = Math.max(max, i - from + 1);
            } else {
                while (cntmap.size() > 2) {
                    if (!cntmap.remove(c[from], 1)) {
                        cntmap.put(c[from], cntmap.get(c[from]) - 1);
                    }
                    from++;
                }
            }
        }
        return max;
    }

}
