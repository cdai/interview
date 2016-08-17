package advanced.combinatorial.permutation.lc030_substringwithconcatenationofallwords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.
 * For example, given: s: "barfoothefoobarman", words: ["foo", "bar"]
 * You should return the indices: [0,9]. (order does not matter).
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findSubstring(
                "barfoofoobarthefoobarman",
                new String[]{"foo", "bar", "the"}
        ));
        System.out.println(new Solution().findSubstring(
                "wordgoodgoodgoodbestword",
                new String[]{"word", "good", "best", "good"}
        ));
    }

    // "lingmindraboofooowingdingbarrwingmonkeypoundcake"
    // ["fooo","barr","wing","ding","wing"]
    public List<Integer> findSubstring(String s, String[] words) {
        Map<String,Integer> target = new HashMap<>();
        for (String w : words) {
            increment(target, w);
        }

        Map<String,Integer> matched = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int total = words.length, wlen = words[0].length();
        for (int i = 0, j = 0; i <= s.length() - wlen; i += wlen) {
            String word = s.substring(i, i + wlen);

            // Widen window if word exist in T
            if (target.containsKey(word)) {
                if (matched.getOrDefault(word, 0) >= target.get(word)) {// error1: cannot interleave even by itself. eg.[""]
                    while (j < i) {                                     // error2: find first occurence of this word, start over there
                        String substr = s.substring(j, j + wlen);
                        decrement(matched, substr);
                        total++;
                        j += wlen;
                        if (substr.equals(word)) {
                            break;
                        }
                    }
                }
                increment(matched, word);
                total--;

                // Find a valid window, narrow it to see if smaller solution
                if (total == 0) {
                    result.add(j);
                    decrement(matched, s.substring(j, j + wlen));
                    total++;
                    j += wlen;
                }
            } else {
                // No interleave allowed, so reset all states
                matched.clear();
                total = words.length;
                j = i + wlen;
            }
        }
        return result;
    }

    private int increment(Map<String,Integer> map, String key) {
        Integer val = map.get(key);
        if (val == null) {
            val = 0;
        }
        map.put(key, val + 1);
        return val + 1;
    }

    private int decrement(Map<String,Integer> map, String key) {
        int val = map.get(key);
        map.put(key, val - 1);
        return val - 1;
    }

    // My 1st
    public List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s.length() == 0 || words.length == 0) {
            return result;
        }

        // 1.Create frequency bag. Error1: same words could appear in dict
        Map<String,Integer> dict = new HashMap<>();
        for (String w : words) {
            add(dict, w);
        }

        // 2.Search from each position
        int n = words.length;
        int m = words[0].length();
        int end = s.length() - m * n;
        for (int i = 0; i <= end; i++) {
            if (doFindSubstring(dict, s.substring(i), m, n)) {
                result.add(i);
            }
        }
        return result;
    }

    private boolean doFindSubstring(Map<String,Integer> dict, String s, int m, int n) {
        Map<String,Integer> copy = new HashMap<>(dict);
        for (int j = 0; j < n * m; j += m) {
            String word = s.substring(j, j + m);    // Error2: j+m not m, this is index not length
            if (!remove(copy, word)) {
                return false;
            }
        }
        return copy.isEmpty();
    }

    // error: Timeout!
    /*private boolean doFindSubstring2(Map<String,Integer> dict, String s, int size) {
        if (dict.isEmpty()) {
            return true;
        }

        boolean isMatched = false;
        String word = s.substring(0, size);
        if (remove(dict, word)) {
            isMatched = doFindSubstring2(dict, s.substring(size), size);
            add(dict, word);
        }
        return isMatched;
    }*/

    private boolean remove(Map<String,Integer> dict, String word) {
        Integer count = dict.remove(word);
        if (count == null) {
            return false;
        }
        if (count > 1) {
            dict.put(word, count - 1);
        }
        return true;
    }

    private void add(Map<String,Integer> dict, String word) {
        Integer count = dict.remove(word);
        if (count == null) {
            dict.put(word, 1);
        } else {
            dict.put(word, count + 1);
        }
    }

}
