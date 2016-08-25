package buildingblock.table.dict.lc205_isomorphicstrings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 * No two characters may map to the same character but a character may map to itself.
 * For example,
 *  Given "egg", "add", return true.
 *  Given "foo", "bar", return false.
 *  Given "paper", "title", return true.
 * Note: You may assume both s and t have the same length.
 */
public class Solution {

    // Explaination: ASCII includes definitions for 128 characters:
    // 33 are non-printing control characters that affect how text and space are processed
    // 95 printable characters, including the space
    // Solution from leetcode discuss.
    // Clean and beautiful one using two alphabet table to save two-way mapping!!!
    public boolean isIsomorphic(String s, String t) {
        int[] alpha1 = new int[128], alpha2 = new int[128];
        Arrays.fill(alpha1, -1);
        Arrays.fill(alpha2, -1);

        for (int i = 0; i < s.length(); i++) {
            if (alpha1[s.charAt(i)] != alpha2[t.charAt(i)]) {
                return false;
            }
            alpha1[s.charAt(i)] = i;
            alpha2[t.charAt(i)] = i;
        }
        return true;
    }

    // My 2nd: O(N) time, O(N) space
    // Test case:
    // 1) ab -> ad is OK, can map itself
    // 2) ab -> da is OK, a->b has no effect on b->a
    // 3) direction-1: ab -> dd is error, since d is already mapped
    // 4) direction-2: aba -> cde is error, since first a is already mapped to c
    public boolean isIsomorphic2(String s, String t) {
        Map<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = map.get(s.charAt(i));
            if (c == null) {
                if (map.containsValue(t.charAt(i))) { // error: map.containsKey(t.charAt(i))
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            } else {
                if (c != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // My 1st
    public boolean isIsomorphic1(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        Map<Character,Character> replaced = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char src = s.charAt(i);
            char tar = t.charAt(i);
            if (replaced.containsKey(src)) {
                if (tar != replaced.get(src)) { // Same char only maps to one char
                    return false;
                }
            } else {
                if (replaced.containsValue(tar)) { // error: different chars map to different ones
                    return false;
                }
                replaced.put(src, tar);
            }
        }
        return true;
    }

}
