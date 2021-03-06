package advanced.dp.recover.lc140_wordbreak2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence
 * where each word is a valid dictionary word. Return all such possible sentences.
 * For example, given s = "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(
                new Solution().wordBreak(
                        "catsanddog",
                        Arrays.asList("cat","cats","and","sand","dog")
                )
        );
        System.out.println(
                new Solution().wordBreak(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")
                )
        );
    }

    // 4AC.
    private List<String>[] memo;

    // Note disadvantage of DP:
    // Bottom-up DP incurs many useless sub-solutions even if it reduces repeating computation
    // Since we have no idea which sub-solution would be used by upper level, all are computed
    // Why? Because comparing with Rod Cutting problem, it's not all positions of this problem is "cutable"
    public List<String> wordBreak(String s, List<String> dict) {
        int n = s.length();
        if (memo == null) memo = new List[n + 1];
        if (memo[n] != null) return memo[n];

        List<String> ret = new ArrayList<>();
        for (int i = 0; i < n; i++) {   // From entire s to one char. Why?
            String suf = s.substring(i);// Because memo save solution of prefix. Why?
            if (!dict.contains(suf)) continue; // Otherwise memo is useless if recurse with suffix
            if (i == 0) ret.add(s);     // Base case: pre=""
            else {
                for (String pre : wordBreak(s.substring(0, i), dict))
                    ret.add(pre + " " + suf);
            }
        }
        memo[n] = ret;
        return ret;
    }

    // Many list in DP is useless if it cannot reach further
    public List<String> wordBreak4_tle(String s, Set<String> dict) {
        List<String>[] dp = new List[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            List<String> words = new ArrayList<>();
            for (int j = i - 1; j >= 0; j--) { // from s[i-1,i) to s[0,i)
                String suff = s.substring(j, i);
                if (!dict.contains(suff)) continue;
                if (j == 0) words.add(suff);
                else for (String pref : dp[j]) words.add(pref + " " + suff);
            }
            dp[i] = words;
        }
        return dp[dp.length - 1];
    }

    // My 3AC.
    private Map<String,List<String>> memo3 = new HashMap<>();

    public List<String> wordBreak3(String s, Set<String> dict) {
        List<String> ret = new ArrayList<>();
        if (s.isEmpty()) return ret;
        if (memo3.containsKey(s)) return memo3.get(s);
        if (dict.contains(s)) ret.add(s);

        for (int i = 1; i < s.length(); i++) { // Break from "a"-"bcd" to "abc"-"d"
            String t = s.substring(i);
            if (!dict.contains(t)) continue;
            for (String w : wordBreak3(s.substring(0, i), dict))
                ret.add(w + " " + t);
        }
        memo3.put(s, ret);
        return ret;
    }

    // My 2AC: O(N^2). Only save index, then recover by DFS
    public List<String> wordBreak2(String s, Set<String> dict) {
        if (s.isEmpty() || dict.isEmpty()) return new ArrayList<>();
        int max = 0;
        for (String word : dict)
            max = Math.max(max, word.length());
        boolean[] matched = new boolean[s.length() + 1];
        matched[0] = true;

        List<Integer>[] path = new List[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = Math.max(0, i - max); j < i; j++) {
                if (!matched[j] || !dict.contains(s.substring(j, i))) continue;
                matched[i] = true;
                if (path[i] == null)
                    path[i] = new ArrayList<>();
                path[i].add(j);
            }
        }

        List<String> result = new ArrayList<>();
        recover(result, new StringBuilder(), s, path, s.length());
        return result;
    }

    private void recover(List<String> result, StringBuilder cur, String s, List<Integer>[] path, int pos) {
        if (pos == 0) {
            result.add(cur.toString().trim());
            return;
        }
        if (path[pos] == null) return;  // error: must put behind, since path[0] is null
        for (int idx : path[pos]) {
            String word = s.substring(idx, pos) + " ";
            recover(result, cur.insert(0, word), s, path, idx);
            cur.delete(0, word.length());
        }
    }

    // Saving the path throughout the DP process is WRONG idea. Why?
    // It's too slow to save all path (many of which cannot reach the end)
    // Eg."aaa...b...aaa", ["a","aa",..."aaaaa"]
    public List<String> wordBreak_path(String s, Set<String> wordDict) {
        int max = 0;
        for (String word : wordDict)
            max = Math.max(max, word.length());                                     // prevent TLE
        boolean[] matched = new boolean[s.length() + 1];
        matched[0] = true;

        List<String>[] sentence = new List[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            for (int j = Math.max(0, i - max); j < i; j++) {
                if (!matched[j] || !wordDict.contains(s.substring(j, i))) continue;
                matched[i] = true;
                if (sentence[i] == null)
                    sentence[i] = new ArrayList<>();

                String word = s.substring(j, i);
                if (j == 0)
                    sentence[i].add(word);
                else
                    for (String sen : sentence[j])
                        sentence[i].add(sen + " " + word);
            }
        }
        return sentence[s.length()] == null ? new ArrayList<>() : sentence[s.length()]; // error1: wordDict is empty -> null
    }


    // My 1AC
    public List<String> wordBreak1(String s, Set<String> wordDict) {
        // matched[n] = all substrings [?,N) that occurred in dict
        List<String>[] matched = new ArrayList[s.length() + 1];
        matched[0] = new ArrayList<>();

        // Generate all substring of s to see which are in dict
        for (int i = 0; i < s.length(); i++) {
            if (matched[i] != null) {       // This is the key of performance!!!
                for (int j = i + 1; j <= s.length(); j++) {
                    String substr = s.substring(i, j);
                    if (wordDict.contains(substr)) {
                        add(matched, j, substr);
                    }
                }
            }
        }

        // Generate path from end to start
        List<String> result = new ArrayList<>();
        generate(result, matched, "", s.length());
        return result;
    }

    private void add(List<String>[] matched, int j, String substr) {
        if (matched[j] == null) {
            matched[j] = new ArrayList<String>();
        }
        matched[j].add(substr);
    }

    private void generate(List<String> result, List<String>[] matched, String str, int k) {
        if (k == 0) {
            result.add(str.trim());
            return;
        }
        if (matched[k] == null) {
            return;
        }

        for (String substr : matched[k]) {
            generate(result, matched, substr + " " + str, k - substr.length()); // error: must be reversed!
        }
    }

    // TLE...
    public List<String> wordBreak21(String s, Set<String> wordDict) {
        Map<String,Integer> matched = new HashMap<>();
        for (int i = 1; i <= s.length(); i++) {
            Map<String,Integer> newMatched = new HashMap<>();
            for (Map.Entry<String,Integer> e : matched.entrySet()) {
                String str = s.substring(e.getValue(), i);
                if (wordDict.contains(str)) {
                    String newStr = e.getKey() + " " + str;
                    newMatched.put(newStr, i);
                }
            }
            matched.putAll(newMatched);

            String str = s.substring(0, i);
            if (wordDict.contains(str)) {
                matched.put(str, i);
            }
        }

        List<String> result = new ArrayList<>();
        for (Map.Entry<String,Integer> e : matched.entrySet()) { // error: put it at front is NOT a waste, eg."a",[a] -> ["a"] not []
            if (e.getValue() == s.length()) {
                result.add(e.getKey());
            }
        }
        return result;
    }

    // Error: eg. "catsanddog", ["cat","cats","and","sand", "dog", "sandd", "og"]
    // after ["cat sand", "cats and"], you still need try to match from cat to get ["cat", "sandd"]
    // that means everything matched no matter how far, should be kept in map forever
    public List<String> wordBreak13(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<>();    // error1: iterate matchTo then remove from matchStr has problem, since index is changing on-the-fly
        List<String> matchStr = new ArrayList<>();  // error2: hashmap can only change value or remove kv pair
        List<Integer> matchTo = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < matchStr.size(); j++) {
                String str = s.substring(matchTo.get(j), i);
                if (wordDict.contains(str)) {
                    matchStr.set(j, matchStr.get(j) + " " + str);
                    matchTo.set(j, i);

                    if (i == s.length()) {
                        result.add(matchStr.get(j));
                    }
                }
            }

            String str = s.substring(0, i);
            if (wordDict.contains(str)) {
                matchStr.add(str);
                matchTo.add(i);
            }
        }
        return result;
    }

    public List<String> wordBreak4(String s, Set<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<>();
        }

        Map<Integer,List<String>> result = new HashMap<>();

        // fn = f(j) && substr(j,i) (0<=j<i)
        boolean[] fn = new boolean[s.length() + 1];
        fn[0] = true;   // sentinel, f[1] = state of substr(0,1) (off by 1)

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                String substr = s.substring(j, i);
                if (fn[j] && wordDict.contains(substr)) {
                    fn[i] = true;

                    // Handle result
                    // 1.Cancat at length-j [0,j] with [j,i)
                    List<String> bag = result.get(j);
                    if (bag == null) {
                        bag = new ArrayList<>();
                        bag.add(substr);
                    } else {
                        List<String> tmp = new ArrayList<>();
                        for (String str : bag) {
                            tmp.add(str + " " + substr);
                        }
                        bag = tmp;
                    }
                    // 2.Merge at length-i
                    List<String> old = result.get(i);
                    if (old == null) {
                        result.put(i, bag);
                    } else {
                        old.addAll(bag);
                    }
                }
            }
        }

        List<String> vals = result.get(s.length());
        if (vals == null) {
            return new ArrayList<>();
        }
        return vals;
    }

}
