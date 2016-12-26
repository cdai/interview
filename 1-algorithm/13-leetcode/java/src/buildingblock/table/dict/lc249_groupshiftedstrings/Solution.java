package buildingblock.table.dict.lc249_groupshiftedstrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.groupStrings(new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z", "b"}));

        int shift = 3;
        for (char c = 'a'; c <= 'z'; c++)
            System.out.printf("%c => %d\n", c, ((c + 26 - shift) % 26));
    }

    // O(N^2) time. Don't forget to sort.
    public List<List<String>> groupStrings(String[] strs) {
        Map<String, List<String>> group = new HashMap<>();
        for (String str : strs) {
            char[] c = str.toCharArray();
            for (int i = 0, shift = c[0] - 'a'; i < c.length; i++)
                //ch[i] = (char) ('a' + (ch[i] - 'a' + (26 - shift)) % 26);
                c[i] = (char) ((c[i] + 26 - shift) % 26); // 26-shift for shifting left

            String key = String.valueOf(c);
            if (!group.containsKey(key))
                group.put(key, new ArrayList<>());
            group.get(key).add(str);
        }

        List<List<String>> ret = new ArrayList<>();
        for (List<String> vals : group.values()) {
            Collections.sort(vals);
            ret.add(vals);
        }
        return ret;
    }

}
