package advanced.dp.onedim.lc140_wordbreak2;

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
                        "catsandabdog",
                        new HashSet<>(Arrays.asList("cat","cats","and","sand","dog","a","b","ab"))
                )
        );
        System.out.println(
                new Solution().wordBreak(
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                        new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"))
                )
        );
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
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
    public List<String> wordBreak2(String s, Set<String> wordDict) {
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
    public List<String> wordBreak3(String s, Set<String> wordDict) {
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
