package advanced.combinatorial.permutation.lc267_palindromepermutation2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.
 * For example:
 *  Given s = "aabb", return ["abba", "baab"].
 *  Given s = "abc", return [].
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generatePalindromes("abc"));
        System.out.println(sol.generatePalindromes("aabb"));
        System.out.println(sol.generatePalindromes("cababaa"));

        System.out.println(sol.generatePalindromes("cababaad"));
        System.out.println(sol.generatePalindromes2("cababaad"));
        System.out.println(sol.generatePalindromes("cababaadd"));
        System.out.println(sol.generatePalindromes2("cababaadd"));
        System.out.println(sol.generatePalindromes("cababaaaa"));
        System.out.println(sol.generatePalindromes2("cababaaaa"));
    }

    public List<String> generatePalindromes(String s) {
        int odd = 0;
        String mid = "";
        List<String> res = new ArrayList<>();
        List<Character> list = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        // step 1. build character count map and count odds
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
            odd += map.get(c) % 2 != 0 ? 1 : -1;
        }

        // cannot form any palindromic string
        if (odd > 1) return res;

        // step 2. add half count of each character to list
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char key = entry.getKey();
            int val = entry.getValue();

            if (val % 2 != 0) mid += key;

            for (int i = 0; i < val / 2; i++) list.add(key);
        }

        // step 3. generate all the permutations
        getPerm(list, mid, new boolean[list.size()], new StringBuilder(), res);

        return res;
    }

    // generate all unique permutation from list
    void getPerm(List<Character> list, String mid, boolean[] used, StringBuilder sb, List<String> res) {
        if (sb.length() == list.size()) {
            // form the palindromic string
            res.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            // avoid duplication
            if (i > 0 && list.get(i) == list.get(i - 1) && !used[i - 1]) continue;

            if (!used[i]) {
                used[i] = true; sb.append(list.get(i));
                // recursion
                getPerm(list, mid, used, sb, res);
                // backtracking
                used[i] = false; sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    public List<String> generatePalindromes2(String str) {
        int[] dict = new int[256];
        for (char c : str.toCharArray())
            dict[c]++;

        List<String> ret = new ArrayList<>();
        String odd = null;
        for (int i = 0; i < dict.length; i++) { // If there is single char, then it must be in middle
            if (dict[i] == 1) {
                if (odd == null) odd = String.valueOf((char) i);
                else return ret;
            }
        }
        generate(ret, (odd == null) ? "" : odd, dict, str.length());
        return ret;
    }

    private void generate(List<String> ret, String path, int[] dict, int max) {
        if (path.length() == max) {
            ret.add(path);
            return;
        }

        for (int i = 0; i < dict.length; i++) {
            if (dict[i] >= 2) { // Only use once for same character
                dict[i] -= 2;
                generate(ret, (char) i + path + (char) i, dict, max);
                dict[i] += 2;
            }
        }
    }
}
