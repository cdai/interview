package miscellaneous.math.arithmetic.lc043_multiplystrings;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 */
public class Solution {

    // eg.1234*567 = 1234*7 + 1234*60 + 1234*500
    // Key: n1[i] * n2[j] will be at ret[i+j,i+j+1]. eg.2*6=12 -> 2+1=3,4
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] mul = new int[m + n]; // left shift num1(m) n-1 times, plus 1 digit for carry
        for (int i = 0, d1 = m - 1; i < m; i++, d1--) {
            for (int j = 0, d2 = n - 1; j < n; j++, d2--) {
                int sum = (num1.charAt(d1) - '0') * (num2.charAt(d2) - '0') + mul[i + j];
                mul[i + j] = sum % 10;      // Low digit
                mul[i + j + 1] += sum / 10; // High carry, must accumulate!
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = mul.length - 1; i >= 0; i--)
            if (!(ret.length() == 0 && mul[i] == 0)) ret.append(mul[i]);
        return ret.length() == 0 ? "0" : ret.toString();
    }

    // Elegant solution by yavinci from leetcode discuss
    // num1 - 1234: i=0, charAt(3)
    // num2 -  234: j=0, charAt(2)
    // prod[,,1,6]
    public String multiply3(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] prod = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int sum = prod[i + j + 1] + mul;// total m+n, this is i+j+1
                prod[i + j] += sum / 10;
                prod[i + j + 1] = sum % 10;
            }
        }

        StringBuilder ret = new StringBuilder();
        for (int p : prod)
            if (!(ret.length() == 0 && p == 0)) // Nice! Ignore leading zero
                ret.append(p);
        return (ret.length() == 0) ? "0" : ret.toString();
    }

    // Elegant solution by yavinci from leetcode discuss
    // num1 - 1234: i=0, charAt(3)
    // num2 -  234: j=0, charAt(2)
    // prod[6,1,,]: high=0, low=1
    public String multiply_mydirection(String num1, String num2) {
        int len1 = num1.length(), len2 = num2.length();
        int[] prod = new int[len1 + len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                int d1 = num1.charAt(len1 - 1 - i) - '0';
                int d2 = num2.charAt(len2 - 1 - j) - '0';
                int sum = prod[i + j] + d1 * d2;
                prod[i + j + 1] += sum / 10;
                prod[i + j] = sum % 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = prod.length - 1; i >= 0; i--) {
            if (result.length() == 0 && prod[i] == 0) { // Ignore leading zero
                continue;
            }
            result.append(prod[i]);
        }
        return (result.length() == 0) ? "0" : result.toString();
    }

    // My 1AC: not fine-grained, actually can only do 1-on-1 mul and add
    public String multiply1(String num1, String num2) {
        if ("0".equals(num1) || "0".equals(num2)) {  // error1: 999*0, 0*999=000
            return "0";
        }

        String ret = "0";
        for (int i = num2.length()-1; i >= 0; i--) {
            String prod = multiplyOneDigit(num1, num2.charAt(i));
            for (int j = num2.length()-1; j > i; j--) {
                prod += "0";
            }
            ret = add(ret, prod);
        }
        return ret;
    }

    private String multiplyOneDigit(String num1, char num2) {
        StringBuilder ret = new StringBuilder();
        int d2 = (num2 - '0');
        int carry = 0;
        for (int i = num1.length()-1; i >= 0; i--) {
            int d1 = (num1.charAt(i) - '0');
            int sum = d1 * d2 + carry;
            if (sum >= 10) {
                ret.insert(0, sum % 10);
                carry = sum / 10;
            } else {
                ret.insert(0, sum);
                carry = 0;
            }
        }
        if (carry > 0) {
            ret.insert(0, carry);
        }
        return ret.toString();
    }

    private String add(String num1, String num2) {
        StringBuilder ret = new StringBuilder();
        int carry = 0;
        for (int i = num1.length()-1, j = num2.length()-1; i >= 0 || j >= 0; i--, j--) {
            int d1 = (i >= 0) ? (num1.charAt(i) - '0') : 0;
            int d2 = (j >= 0) ? (num2.charAt(j) - '0') : 0;
            int sum = d1 + d2 + carry;
            if (sum >= 10) {
                ret.insert(0, sum % 10);
                carry = sum / 10;
            } else {
                ret.insert(0, sum);
                carry = 0;
            }
        }
        if (carry > 0) {
            ret.insert(0, carry);
        }
        return ret.toString();
    }

}
