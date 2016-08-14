package buildingblock.hashing.dict.lc290_wordpattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 *  pattern = "abba", str = "dog cat cat dog" should return true.
 *  pattern = "abba", str = "dog cat cat fish" should return false.
 *  pattern = "aaaa", str = "dog cat cat dog" should return false.
 *  pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes: You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */
public class Solution {

    // Stefan solution: very similiar to 205-Isomorphic Strings
    // Eg."abc","b a c" won't confuse map, since key is of different type
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Object,Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            if (!Objects.equals(map.put(pattern.charAt(i), i), map.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }

    // My 2nd: O(KN)?
    public boolean wordPattern2(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }

        Map<Character,String> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            String word = map.get(pattern.charAt(i));
            if (word == null) {
                if (map.containsValue(words[i])) { // Note: linear time O(N)
                    return false;
                }
                map.put(pattern.charAt(i), words[i]);
            } else {
                if (!word.equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    // My 1st
    public boolean wordPattern1(String pattern, String str) {
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (patterns.length != strs.length) {
            return false;
        }

        // Bijection mapping
        Map<Character,String> map = new HashMap<>();
        for (int i = 0; i < patterns.length; i++) {
            String mapped = map.get(patterns[i]);
            if (mapped == null) {
                if (map.values().contains(strs[i])) {
                    return false;
                }
                map.put(patterns[i], strs[i]);
            } else {
                if (!mapped.equals(strs[i])) {
                    return false;
                }
            }
        }
        return true;
    }

}
