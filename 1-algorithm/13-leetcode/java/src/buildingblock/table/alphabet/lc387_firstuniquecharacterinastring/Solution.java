package buildingblock.table.alphabet.lc387_firstuniquecharacterinastring;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it and return it's index.
 * If it doesn't exist, return -1.
 * Examples:
 *  s = "leetcode". return 0.
 *  s = "loveleetcode", return 2.
 * Note: You may assume the string contain only lowercase letters.
 */
public class Solution {

    // Very straightforward and elegant O(N) time and O(1) space solution
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) return i;
        }
        return -1;
    }

    public int firstUniqChar1(String s) {
        int[] letters = new int[26];
        for (char c : s.toCharArray()) {
            letters[c - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (letters[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    // Not straightforward and waste space
    public int firstUniqChar21(String s) {
        if (s.isEmpty()) return -1;

        Map<Character, Integer> idx = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (idx.containsKey(c)) idx.put(c, Integer.MAX_VALUE);
            else idx.put(c, i);
        }

        int min = Integer.MAX_VALUE;
        for (int i : idx.values()) min = Math.min(min, i);
        return min == Integer.MAX_VALUE ? -1 : min;
    }

}
