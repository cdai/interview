package fundamentals.string.convert.lc012_integertoroman;

/**
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
public class Solution {

    // Extremely simple and straightforward solution
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    private String[] syms = {
            "I", "V", "X",
            "L", "C", "D",
            "M", "", ""     // avoid outofbound error
    };

    // My 2AC: divide into 3 groups 1~10,10~100,100~1000,1000~...
    // A little better but same thought as 1AC
    public String intToRoman2(int num) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0, j = 0; num > 0; i++, j += 2, num /= 10) {
            roman.insert(0, int2ro(num % 10, syms[j], syms[j + 1], syms[j + 2]));
        }
        return roman.toString();
    }

    private String int2ro(int digit, String i, String v, String x) {
        switch (digit) {
            case 1: return i;
            case 2: return i + i;
            case 3: return i + i + i;
            case 4: return i + v;
            case 5: return v;
            case 6: return v + i;
            case 7: return v + i + i;
            case 8: return v + i + i + i;
            case 9: return i + x;
            default: return "";     /* digit=0 */
        }
    }

    // My 1AC
    public String intToRoman1(int num) {
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
