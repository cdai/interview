package fundamentals.string.convert.lc008_stringtointeger;

/**
 * Implement atoi to convert a string to an integer.
 * Hint: Carefully consider all possible input cases.
 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().myAtoi("1"));
        System.out.println(new Solution().myAtoi("   b123"));
    }

    public int myAtoi(String s) {
        int n = s.length(), num = 0, i = 0, sign = 1;
        // 1.Trim leading space
        while (i < n && s.charAt(i) == ' ') i++;

        // 2.Check sign if there is any
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i++) == '-') ? -1 : 1;
        }

        // 3.Parse num
        for (; i < n && Character.isDigit(s.charAt(i)); i++) {
            int tmp = num * 10 + sign * (s.charAt(i) - '0');
            if (tmp / 10 != num) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = tmp;
        }
        return num;
    }

    // Very concise and clean version from leetcode discuss
    // Since c[i]=0 when reach end, C++ is more concise even!
    public int myAtoi3(String str) {
        char[] c = str.toCharArray();
        int i = 0, sign = 1, num = 0, n = c.length;

        // 1.Skip leading whitespace
        while (i < n && Character.isWhitespace(c[i])) i++;

        // 2.Get sign of number
        if (i < n && (c[i] == '+' || c[i] == '-'))
            sign = (c[i++] == '-') ? -1 : 1;

        // 3.Digit or letter
        for (; i < n && Character.isDigit(c[i]); i++) {
            int add = c[i] - '0';
            if (num > Integer.MAX_VALUE / 10
                    || (num == Integer.MAX_VALUE / 10 && add > 7)) // Nice check!!!
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            num = num * 10 + add;
        }
        return num * sign;
    }

    // How messy it looks like when you put all together...
    public int myAtoi_oneloop(String str) {
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isWhitespace(c)) {
                continue;
            }
            if (Character.isLetter(c)) {
                break;
            }

            // Find potential integer
            boolean isNeg = (c == '-');
            int j = (c == '+' || c == '-') ? i + 1 : i;
            for ( ; j < str.length(); j++) {
                if (!Character.isDigit(str.charAt(j))) {
                    break;
                }

                // Pre-compute to check if overflow
                int digit = str.charAt(j) - '0';
                if (!isNeg && (Integer.MAX_VALUE - digit) / 10 < result) {
                    return Integer.MAX_VALUE;
                }
                if (isNeg && (Integer.MIN_VALUE + digit) / -10 < result) {
                    return Integer.MIN_VALUE;
                }
                result = result * 10 + digit;
            }
            result = isNeg ? -result : result;
            break;
        }
        return result;
    }

    // My 1st: C style
    public int myAtoi1(String str) {
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
