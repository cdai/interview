package buildingblock.sorting.bucket.lc451_sortcharactersbyfrequency;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 */
public class Solution {

    // Use bucket sort as we did for Top K Frequent Elements
    public String frequencySort(String s) {
        Map<Character,Integer> freq = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0) + 1);
        }

        StringBuilder[] bucket = new StringBuilder[s.length() + 1];
        for (Map.Entry<Character,Integer> e : freq.entrySet()) {
            if (bucket[e.getValue()] == null) {
                bucket[e.getValue()] = new StringBuilder();
            }
            for (int i = 0; i < e.getValue(); i++) {
                bucket[e.getValue()].append(e.getKey());
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int i = s.length(); i > 0; i--) {
            if (bucket[i] != null) ret.append(bucket[i]);
        }
        return ret.toString();
    }

}
