package fundamentals.string.convert.lc012_integertoroman;

/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class Solution {

    public String intToRoman(int num) {
        StringBuilder str = new StringBuilder();

        String[][] base = {
                {"I", "V", "X"},
                {"X", "L", "C"},
                {"C", "D", "M"},
                {"M", "-", "-"},
        };

        int i = 0;
        while (num > 0) {
            str.insert(0, parse(num % 10, base[i][0], base[i][1], base[i][2]));
            num /= 10;
            i++;
        }
        return str.toString();
    }

    // num: [0,9]
    private String parse(int num, String one, String five, String ten) {
        int mod = num % 5;
        if (mod <= 3) {     // [0,3] => 0~3, 5~8
            String str = "";
            while (mod-- > 0) {
                str += one;
            }
            return num >= 5 ? five + str : str;
        } else {            // [4] => 4,9
            return num > 5 ? one + ten : one + five;
        }
    }

}
