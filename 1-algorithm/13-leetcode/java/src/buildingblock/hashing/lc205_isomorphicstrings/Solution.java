package buildingblock.hashing.lc205_isomorphicstrings;

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

    public boolean isIsomorphic(String s, String t) {
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
