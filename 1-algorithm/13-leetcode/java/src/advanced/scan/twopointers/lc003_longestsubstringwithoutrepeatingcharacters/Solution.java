package advanced.scan.twopointers.lc003_longestsubstringwithoutrepeatingcharacters;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    // Inspired by cbmbbz from leetcode discuss
    // O(N) time, O(1) space if charset is fixed
    // Idea: don't go back and it's ok "dirty" char remained out there
    public int lengthOfLongestSubstring(String s) {
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
