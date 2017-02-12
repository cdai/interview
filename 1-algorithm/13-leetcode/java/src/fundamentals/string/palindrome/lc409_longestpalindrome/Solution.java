package fundamentals.string.palindrome.lc409_longestpalindrome;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.
 */
public class Solution {

    public int longestPalindrome(String s) {
        boolean[] seen = new boolean[256];
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (seen[c]) {
                seen[c] = false;
                max += 2;
            } else {
                seen[c] = true;
            }
        }
        return max < s.length() ? max + 1 : max;
    }

    public int longestPalindrome_my(String s) {
        // 1.Get word frequency
        Map<Character,Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }

        // 2.Calculate max length of palindrome
        int max = 0;
        for (int cnt : freq.values()) {
            max += cnt % 2 == 0 ? cnt : cnt - 1;
        }
        return max < s.length() ? max + 1 : max;
    }

}
