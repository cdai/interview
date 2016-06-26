package advanced.combinatorial.combination.lc017_lettercombinationsofaphonenumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class Solution {

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return Collections.emptyList();
        }

        String[] letters;
        switch (digits.charAt(0)) {
            case '2': letters = new String[]{ "a", "b", "c" }; break;
            case '3': letters = new String[]{ "d", "e", "f" }; break;
            case '4': letters = new String[]{ "g", "h", "i" }; break;
            case '5': letters = new String[]{ "j", "k", "l" }; break;
            case '6': letters = new String[]{ "m", "n", "o" }; break;
            case '7': letters = new String[]{ "p", "q", "r", "s" }; break;
            case '8': letters = new String[]{ "t", "u", "v" }; break;
            case '9': letters = new String[]{ "w", "x", "y", "z" }; break;
            default: letters = new String[]{}; break;
        }

        if (digits.length() == 1) {
            return Arrays.asList(letters);
        }

        List<String> ret = new ArrayList<>();
        List<String> comb = letterCombinations(digits.substring(1));
        for (String l : letters) {
            for (String str : comb) {
                ret.add(l + str);
            }
        }
        return ret;
    }

}
