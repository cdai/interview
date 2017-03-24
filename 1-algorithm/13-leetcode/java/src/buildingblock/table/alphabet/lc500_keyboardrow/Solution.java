package buildingblock.table.alphabet.lc500_keyboardrow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.
 */
public class Solution {

    public String[] findWords(String[] words) {
        if (words.length == 0) return words;
        String[] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < keyboard.length; i++) {
            for (int j = 0; j < keyboard[i].length(); j++)
                map.put(keyboard[i].charAt(j), i);
        }

        List<String> ret = new ArrayList<>();
        for (String w : words) {
            Integer row = null;
            for (int i = 0; i < w.length(); i++) {
                char c = Character.toLowerCase(w.charAt(i));
                if (row == null) {
                    row = map.get(c);
                } else {
                    if (map.get(c) != row) {
                        row = -1;
                        break;
                    }
                }
            }
            if (row != -1) ret.add(w);
        }
        return ret.toArray(new String[ret.size()]);
    }

}
