package buildingblock.hashing.lc049_groupanagrams;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Return:
 * [
 *  ["ate", "eat","tea"],
 *  ["nat","tan"],
 *  ["bat"]
 * ]
 * Note: All inputs will be in lower-case.
 */
public class Solution {

    // k=s.length, O(n * klogk)
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            List<String> g = group.get(key);
            if (g == null) {
                g = new ArrayList<>();
                group.put(key, g);
            }
            g.add(s);
        }
        return new ArrayList<>(group.values());
    }

    // O(n*n)
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        List<String> strlist = new LinkedList<>(Arrays.asList(strs));
        while (!strlist.isEmpty()) {
            Iterator<String> it = strlist.iterator();
            String str = it.next();
            it.remove();

            List<String> anagrams = new ArrayList<>();
            anagrams.add(str);
            Map<Character,Integer> letters = charsOf(str);  // error1: word could contain same letter!

            while (it.hasNext()) {
                String ana = it.next();
                if (letters.equals(charsOf(ana))) {
                    anagrams.add(ana);
                    it.remove();
                }
            }
            result.add(anagrams);
        }
        return result;
    }

    private Map<Character,Integer> charsOf(String str) {
        Map<Character,Integer> chars = new HashMap<>(str.length());
        for (char c : str.toCharArray()) {
            Integer freq = chars.get(c);
            chars.put(c, (freq == null) ? 1 : (freq + 1));
        }
        return chars;
    }

}
