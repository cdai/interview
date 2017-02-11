package advanced.scan.twopointers.lc159_longestsubstringwithatmosttwodistinctcharacters;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S, find the length of the longest substring T that contains at most two distinct characters.
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals(0, lengthOfLongestSubstring(""));
        Assertions.assertEquals(1, lengthOfLongestSubstring("a"));
        Assertions.assertEquals(2, lengthOfLongestSubstring("aa"));
        Assertions.assertEquals(3, lengthOfLongestSubstring("aba"));
        Assertions.assertEquals(4, lengthOfLongestSubstring("abab"));
        Assertions.assertEquals(2, lengthOfLongestSubstring("abcda"));
        Assertions.assertEquals(3, lengthOfLongestSubstring("eceba"));
    }

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> win = new HashMap<>();
        int max = 0, len = 0;
        for (int i = 0; i < s.length(); i++) {
            char r = s.charAt(i);
            win.put(r, win.getOrDefault(r, 0) + 1);
            for (int j = i - len; win.size() > 2; j++, len--) {
                char l = s.charAt(j);
                if (!win.remove(l, 1)) win.put(l, win.get(l) - 1);
            }
            max = Math.max(max, ++len);
        }
        return max;
    }

}
