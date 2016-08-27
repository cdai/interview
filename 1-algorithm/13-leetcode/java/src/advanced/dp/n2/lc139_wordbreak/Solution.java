package advanced.dp.n2.lc139_wordbreak;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into
 * a space-separated sequence of one or more dictionary words.
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 */
public class Solution {

    // O(N^2) but very concise DP solution.
    public boolean wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++)
            for (int j = 0; j < i && !dp[i]; j++)
                dp[i] = dp[j] && wordDict.contains(s.substring(j, i)); //substr(j) not j+1, since char and dp off-by-1
        return dp[s.length()];
    }

    // Wrong! "abcd", ["a","abc","b","cd"]. there's no 'd' but 'cd'
    // So we need Trie + DFS/BFS like 211-Add and Search Word, still has TLE problem
    public boolean wordBreak2(String s, Set<String> wordDict) {
        // Build a trie tree with leaf connected to root
        TrieNode root = new TrieNode();
        for (String word : wordDict) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                TrieNode next = cur.next.get(c);
                if (next == null)
                    cur.next.put(c, next = new TrieNode());
                cur = next;
            }
            cur.next.put(' ', root);
        }

        // Traverse string along the trie tree
        TrieNode cur = root;
        for (int i = 0; i < s.length(); i++) {
            TrieNode next = cur.next.get(s.charAt(i));
            if (next == null)
                next = cur.next.get(' ');
            if (next == null)
                return false;
            cur = next;
            if (cur == root)
                i--;
        }
        return true;
    }

    static class TrieNode {
        Map<Character,TrieNode> next = new HashMap<>();
    }

    // DFS is the natural first idea, but there is "bad" test case...
    // Even terminate early, still TLE. eg."aaaaa...b", ["a","aa",..."aaaaa"]
    // Permutation + Subset + Infinite used
    public boolean wordBreak_dfs(String s, Set<String> wordDict) {
        return doBreak(s, new StringBuilder(), wordDict.toArray(new String[0]));
    }

    private boolean doBreak(String s, StringBuilder path, String[] dict) {
        if (path.length() > s.length()) return false;       // terminate early
        if (!s.startsWith(path.toString())) return false;   // terminate early
        if (path.length() == s.length()) return true;

        for (String word : dict) {
            if (doBreak(s, path.append(word), dict))
                return true;
            path.delete(path.length() - word.length(), path.length());
        }
        return false;
    }

    // My 1AC: "DP" solution
    private Set<String> memo = new HashSet<>();

    public boolean wordBreak1(String s, Set<String> wordDict) {
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
                if (wordBreak1(tail, wordDict)) {
                    return true;
                }
                memo.add(tail);
            }
        }
        return false;
    }

}
