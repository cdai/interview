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

    // My 3AC: map is overkill, switch is adequate!
    public int romanToInt(String s) {
        int prev = 0, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int cur = r2i(s.charAt(i));
            num += (cur >= prev) ? cur : -cur;
            prev = cur;
        }
        return num;
    }

    private int r2i(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0; // assume never happen
        }
    }

    // My 2AC: much easier to understand now!
    public int romanToInt2(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Subtract if less than prev, since IIV is impossible
        int prev = 0, num = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int cur = map.get(s.charAt(i));
            num += (cur >= prev) ? cur : -cur;  // Nice -cur! Note >=
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
