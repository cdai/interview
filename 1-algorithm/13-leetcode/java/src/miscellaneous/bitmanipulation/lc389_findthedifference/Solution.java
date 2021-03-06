package miscellaneous.bitmanipulation.lc389_findthedifference;

/**
 * Given two strings s and t which consist of only lowercase letters.
 * String t is generated by random shuffling string s and then add one more letter at a random position.
 * Find the letter that was added in t.
 */
public class Solution {

    public char findTheDifference(String s, String t) {
        int diff = 0;
        for (int i = 0; i < s.length(); i++) {
            diff = diff ^ s.charAt(i) ^ t.charAt(i);
        }
        diff ^= t.charAt(t.length() - 1);
        return (char) diff;
    }

    public char findTheDifference_dict(String s, String t) {
        int n = s.length();
        int[] diff = new int[26];
        for (int i = 0; i < n; i++) {
            diff[s.charAt(i) - 'a']--;
            diff[t.charAt(i) - 'a']++;
        }
        diff[t.charAt(n) - 'a']++;
        for (int i = 0; i < 26; i++) {
            if (diff[i] > 0) return (char) ('a' + i);
        }
        return 0;
    }

}
