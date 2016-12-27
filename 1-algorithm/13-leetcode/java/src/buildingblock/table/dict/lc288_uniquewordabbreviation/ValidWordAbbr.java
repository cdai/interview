package buildingblock.table.dict.lc288_uniquewordabbreviation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class ValidWordAbbr {

    public static void main(String[] args) {
        ValidWordAbbr sol = new ValidWordAbbr(Arrays.asList("deer", "door", "cake", "card"));
        System.out.println(sol.isUnique("dear")); // false
        System.out.println(sol.isUnique("cart")); // true
        System.out.println(sol.isUnique("cane")); // false
        System.out.println(sol.isUnique("make")); // true
    }

    private Map<String, String> dict = new HashMap<>();

    public ValidWordAbbr(List<String> strs) {
        for (String s : strs) {
            String abbr = abbreviate(s);
            if (dict.containsKey(abbr) && !dict.get(abbr).equals(s))
                dict.put(abbr, ""); // make it invalid
            else
                dict.put(abbr, s);
        }
    }

    public boolean isUnique(String word) {
        if (word.isEmpty()) return true;
        String key = abbreviate(word);
        return !dict.containsKey(key) || dict.get(key).equals(word);
    }

    private String abbreviate(String s) {
        if (s.length() < 3) return s;
        return "" + s.charAt(0) + (s.length() - 2) + s.charAt(s.length() - 1);
    }

}
