package fundamentals.string.convert.lc013_romantointeger;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class Solution {

    private static Map<Character,Integer> roman = new HashMap<>();

    static {
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
    }

    public int romanToInt(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        int integer = roman.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i--) {
            int cur = roman.get(s.charAt(i));
            int last = roman.get(s.charAt(i + 1));
            integer = (cur >= last) ? (integer + cur) : (integer - cur); // error1: must >=, DCXXI=621 not 601
        }
        return integer;
    }

}
