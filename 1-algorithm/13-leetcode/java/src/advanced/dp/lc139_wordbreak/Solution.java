package advanced.dp.lc139_wordbreak;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class Solution {

    private Set<String> memo = new HashSet<>();

    public boolean wordBreak(String s, Set<String> wordDict) {
        if (s.length() == 0) {
            return true;
        }

        if (memo.contains(s)) {
            return false;
        }

        for (int i = 1; i <= s.length(); i++) {
            String substr = s.substring(0, i);
            if (wordDict.contains(substr)) {
                String tail = s.substring(i);
                if (wordBreak(tail, wordDict)) {
                    return true;
                }
                memo.add(tail);
            }
        }
        return false;
    }

}
