package advanced.dp.lc140_wordbreak2;

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
    }

    public List<String> wordBreak(String s, Set<String> wordDict) {
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
