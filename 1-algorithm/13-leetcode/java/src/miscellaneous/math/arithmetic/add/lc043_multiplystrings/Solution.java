package miscellaneous.math.arithmetic.add.lc043_multiplystrings;

/**
 * Given two numbers represented as strings, return multiplication of the numbers as a string.
 * Note: The numbers can be arbitrarily large and are non-negative.
 * Converting the input string to integer is NOT allowed.
 * You should NOT use internal library such as BigInteger.
 */
public class Solution {

    public String multiply(String num1, String num2) {
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
