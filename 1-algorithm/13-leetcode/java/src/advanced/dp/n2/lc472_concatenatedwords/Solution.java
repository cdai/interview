package advanced.dp.n2.lc472_concatenatedwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 */
public class Solution {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        List<String> ret = new ArrayList<>();
        for (String w : words) {
            if (dfs(w, dict, 1)) ret.add(w);
        }
        return ret;
    }

    private boolean dfs(String word, Set<String> dict, int cnt) {
        for (int i = 0; i < word.length(); i++) { // suf from entire word to one char
            String suf = word.substring(i);
            if (!dict.contains(suf)) continue;
            if (i == 0 && cnt >= 2) return true;
            if (dfs(word.substring(0, i), dict, cnt + 1)) return true;
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADict_DP(String[] words) {
        Map<String,Boolean> memo = new HashMap<>();
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        List<String> ret = new ArrayList<>();
        for (String w : words) {
            if (dfs(w, memo, dict)) ret.add(w);
        }
        return ret;
    }

    private boolean dfs(String word, Map<String,Boolean> memo, Set<String> dict) {
        if (memo.containsKey(word)) return memo.get(word);
        for (int i = 1; i < word.length(); i++) { // suf from n-1 word to one char
            String suf = word.substring(i);
            if (!dict.contains(suf)) continue;

            String pre = word.substring(0, i);
            if (dict.contains(pre) || dfs(pre, memo, dict)) {
                memo.put(word, true);
                return true;
            }
        }
        memo.put(word, false);
        return false;
    }

    // O(n*len*len). Idea from Word Break I. TLE...
    // For "hello void" and "hello world", Trie could save time of prefix "hello" check, but Set cannot.
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        Set<String> memo = new HashSet<>();
        Set<String> dict = new HashSet<>(Arrays.asList(words));
        List<String> ret = new ArrayList<>();
        for (String s : words) {
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 1; j < i; j++) {
                    String pre = s.substring(0, j), suf = s.substring(j, i);
                    if ((memo.contains(pre) || dict.contains(pre)) && dict.contains(suf)) {
                        memo.add(s.substring(0, i));
                    }
                }
            }
            if (memo.contains(s)) ret.add(s);
        }
        return ret;
    }

}
