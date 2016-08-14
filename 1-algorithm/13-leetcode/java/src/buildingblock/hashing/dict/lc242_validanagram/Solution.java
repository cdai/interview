package buildingblock.hashing.dict.lc242_validanagram;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
 *  s = "anagram", t = "nagaram", return true.
 *  s = "rat", t = "car", return false.
 * Note: You may assume the string contains only lowercase alphabets.
 * Follow up: What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
public class Solution {

    // My 2nd: O(N) time, O(1) space (fixed 26 int array)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
            alphabet[t.charAt(i) - 'a']--;
        }

        for (int i : alphabet) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram1(String s, String t) {
        int[] dict = new int[26];
        for (char c : s.toCharArray()) {
            dict[c - 'a']++;
        }

        for (char c : t.toCharArray()) {
            dict[c - 'a']--;
        }

        for (int feq : dict) {
            if (feq != 0) {
                return false;
            }
        }
        return true;
    }

    // Fairly slow...
    public boolean isAnagram2(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        // 1.Build dict-freq from S
        Map<String,Integer> letters = new HashMap<>(); // use string not char for unicode
        for (int i = 0; i < s.length(); i++) {
            String letter = s.substring(i, i + 1);
            Integer count = letters.get(letter);
            if (count == null) {
                count = 0;
            }
            letters.put(letter, count + 1);
        }

        // 2.Remove from T
        for (int i = 0; i < t.length(); i++) {
            String letter = t.substring(i, i + 1);
            Integer count = letters.get(letter);
            if (count == null) {
                return false;
            }
            if (count == 1) {
                letters.remove(letter);
            } else {
                letters.put(letter, count - 1);
            }
        }
        return letters.isEmpty();
    }

}
