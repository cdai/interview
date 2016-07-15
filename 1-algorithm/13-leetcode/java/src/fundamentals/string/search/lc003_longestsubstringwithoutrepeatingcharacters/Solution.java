package fundamentals.string.search.lc003_longestsubstringwithoutrepeatingcharacters;

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

    public int lengthOfLongestSubstring(String s) {
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
