package advanced.scan.window.lc159_longeststringwithatmosttwodistinctcharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.lengthOfLongestSubstringTwoDistinct("eceba")); // 3
        System.out.println(sol.lengthOfLongestSubstringTwoDistinct("ecebeefff")); // 5
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> cnt = new HashMap<>();
        char[] c = s.toCharArray();
        int from = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            cnt.put(c[i], cnt.getOrDefault(c[i], 0) + 1);
            while (cnt.size() > 2) {
                if (!cnt.remove(c[from], 1))
                    cnt.put(c[from], cnt.get(c[from]) - 1);
                from++;
            }
            max = Math.max(max, i - from + 1);
        }
        return max;
    }

}
