package fundamentals.string.convert.lc008_stringtointeger;

/**
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 */
public class Solution {

    public int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        char[] chars = str.toCharArray();
        if (chars.length == 0) {
            return 0;
        }

        // Trap-1: Trim leading whitespace
        int j = 0;
        while (chars[j] == ' ') {
            j++;
            if (j >= chars.length) {
                return 0;
            }
        }

        // Trap-2: Plug/minus flag
        boolean isNeg = false;
        if (chars[j] == '+') {
            j++;
        } else if (chars[j] == '-') {
            j++;
            isNeg = true;
        }

        // Perform conversion
        int i = 0;
        for (; j < chars.length; j++) {
            // Trap-3: Ignore following invalid char
            if (chars[j] < '0' || chars[j] > '9') {
                break;
            }

            // Trap-4: Overflow
            if (!isNeg && (Integer.MAX_VALUE - (chars[j] - '0')) / 10 < i) {
                return Integer.MAX_VALUE;
            }
            if (isNeg && (Integer.MIN_VALUE + (chars[j] - '0')) / -10 < i) {
                return Integer.MIN_VALUE;
            }

            i = i * 10 + (chars[j] - '0');
        }
        return isNeg ? -i : i;
    }

}
