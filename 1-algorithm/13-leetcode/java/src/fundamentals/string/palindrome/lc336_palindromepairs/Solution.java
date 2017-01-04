package fundamentals.string.palindrome.lc336_palindromepairs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 */
public class Solution {

    // From naive O(nnk) to O(nkk) time.
    // Edge case: ["a, ""]
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) map.put(words[i], i);

        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j <= words[i].length(); j++) { // Note j=[0..len]: s1="",s2=word -> s1=word,s2=""
                String s1 = words[i].substring(0, j), s2 = words[i].substring(j);
                if (isPalindrome(s1)) { // word2 + s1 + s2 is palindrome
                    String t = new StringBuilder(s2).reverse().toString();
                    if (map.getOrDefault(t, i) != i) ret.add(Arrays.asList(map.get(t), i));
                }
                if (isPalindrome(s2) && !s2.isEmpty()) { // s1 + s2 + word2 is palindrome (avoid duplicate if s2="": try only w2+s1 nor s1+w2)
                    String t = new StringBuilder(s1).reverse().toString();
                    if (map.getOrDefault(t, i) != i) ret.add(Arrays.asList(i, map.get(t)));
                }
            }
        }
        return ret;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--)
            if (s.charAt(i) != s.charAt(j)) return false;
        return true;
    }

}
