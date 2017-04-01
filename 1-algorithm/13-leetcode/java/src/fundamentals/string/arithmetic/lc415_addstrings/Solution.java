package fundamentals.string.arithmetic.lc415_addstrings;

/**
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
 */
public class Solution {

    public String addStrings(String num1, String num2) {
        StringBuilder ret = new StringBuilder();
        int carry = 0, m = num1.length(), n = num2.length();
        for (int i = 1; i <= Math.max(m, n) || carry != 0; i++) {
            int d1 = i <= m ? num1.charAt(m - i) - '0' : 0;
            int d2 = i <= n ? num2.charAt(n - i) - '0' : 0;
            int sum = d1 + d2 + carry;
            ret.append(sum % 10);
            carry = sum / 10;
        }
        return ret.reverse().toString();
    }

}
