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

    // My 2AC: much easier to understand now!
    public int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Substract if less than prev, since IIV is impossible
        int prev = 0, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int cur = map.get(s.charAt(i));
            num += (cur >= prev) ? cur : -cur;
            prev = cur;
        }
        return num;
    }

    // My 1AC: bad dealing with cur and last
    public int romanToInt1(String s) {
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
