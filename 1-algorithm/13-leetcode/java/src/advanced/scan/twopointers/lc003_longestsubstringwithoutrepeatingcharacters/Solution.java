package advanced.scan.twopointers.lc003_longestsubstringwithoutrepeatingcharacters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 *  Given "abcabcbb", the answer is "abc", which the length is 3.
 *  Given "bbbbb", the answer is "b", with the length of 1.
 *  Given "pwwkew", the answer is "wke", with the length of 3.
 * Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("bb"));
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    // Save index not count
    public int lengthOfLongestSubstring(String s) {
        int[] idx = new int[256];
        Arrays.fill(idx, -1);
        int max = 0;
        for (int i = 0, from = 0; i < s.length(); i++) { /* [from,i) has no repeating str */
            if (idx[s.charAt(i)] >= from) // Since we never clear idx after init, must >= from for validity
                from = idx[s.charAt(i)] + 1;
            idx[s.charAt(i)] = i;
            max = Math.max(max, i - from + 1); /* Fix invariant. [from,i] has no neither */
        }
        return max;
    }

    public int lengthOfLongestSubstring3_set(String s) {
        Set<Character> seen = new HashSet<>();
        int max = 0;
        for (int i = 0, from = 0; i < s.length(); i++) {
            while (!seen.add(s.charAt(i))) // Repeat char in window
                seen.remove(s.charAt(from++));
            max = Math.max(max, seen.size());
        }
        return max;
    }

    public int lengthOfLongestSubstring3_array(String s) {
        int[] seen = new int[256];
        int max = 0;
        for (int i = 0, from = 0; i < s.length(); i++) {
            while (seen[s.charAt(i)] > 0)
                seen[s.charAt(from++)]--;
            seen[s.charAt(i)]++;
            max = Math.max(max, i - from + 1);
        }
        return max;
    }

    // Inspired by cbmbbz from leetcode discuss
    // O(N) time, O(1) space if charset is fixed
    // Idea: don't go back and it's ok "dirty" char remained out there
    public int lengthOfLongestSubstring21(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int max = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer pos = map.put(s.charAt(i), i);
            if (pos != null && pos >= start) { // Only matters when repeated char is after "start"
                max = Math.max(max, i - start);
                start = pos + 1;
            }
        }
        return Math.max(max, s.length() - start);
    }

    // My 2nd: start over from last occurence if repeated
    public int lengthOfLongestSubstring2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Integer pos = map.put(s.charAt(i), i);
            if (pos != null) {
                max = Math.max(max, map.size());
                i = pos;
                map.clear();
            }
        }
        return Math.max(max, map.size());
    }

    public int lengthOfLongestSubstring1(String s) {
        Map<Character,Integer> chars = new HashMap<>();
        int maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars.containsKey(c)) {
                maxLen = Math.max(maxLen, chars.size());
                i = chars.get(c); // start over from next of last repeated char (i++ soon)
                chars.clear();
            } else {
                chars.put(c, i);
            }
        }
        maxLen = Math.max(maxLen, chars.size()); // error1: no repeat until the end
        return maxLen;
    }

}
