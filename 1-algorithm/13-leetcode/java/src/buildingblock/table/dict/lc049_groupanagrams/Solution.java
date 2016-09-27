package buildingblock.table.dict.lc049_groupanagrams;

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

    // My 3AC
    public List<List<String>> groupAnagrams_quicksort(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            if (!group.containsKey(key))
                group.put(key, new ArrayList<>());
            group.get(key).add(str);
        }
        return new ArrayList<>(group.values());
    }

    // O(MNlogN) time using quicksort. O(MN) using counting sort.
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            //Arrays.sort(chars);
            sort(chars);

            String key = new String(chars);
            if (!group.containsKey(key))
                group.put(key, new ArrayList<>());
            group.get(key).add(str);
        }
        return new ArrayList<>(group.values());
    }

    private void sort(char[] chars) {
        int[] cnt = new int[26];
        for (char c : chars) cnt[c - 'a']++;
        for (int i = 0, j = 0; i < chars.length && j < 26; ) {
            if (cnt[j]-- > 0) chars[i++] = (char) ('a' + j);
            else j++;
        }
    }

    // My 2nd: it's still hard to come up with sort(chars) as key
    // k=s.length, O(n * klogk)
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String,List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);

            String key = new String(chars);
            List<String> anagrams = group.get(key);
            if (anagrams == null) {
                anagrams = new ArrayList<>();
                group.put(key, anagrams);
            }
            anagrams.add(str);
        }
        return new ArrayList<>(group.values());
    }

    // O(n*n)
    public List<List<String>> groupAnagrams1(String[] strs) {
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
