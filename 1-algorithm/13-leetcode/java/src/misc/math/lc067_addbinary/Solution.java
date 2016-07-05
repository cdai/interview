package misc.math.lc067_addbinary;

/**
 * Given two binary strings, return their sum (also a binary string).
 * For example, a = "11", b = "1". Return "100".
 */
public class Solution {

    public String addBinary(String a, String b) {
        if (a.length() == 0 || b.length() == 0) {
            return "0";
        }

        StringBuilder ret = new StringBuilder();
        int carry = 0;
        for (int i = a.length()-1, j = b.length()-1; (i >= 0 || j >= 0); i--, j--) {
            int d1 = (i < 0) ? 0 : (a.charAt(i) - '0');
            int d2 = (j < 0) ? 0 : (b.charAt(j) - '0');
            int sum = d1 + d2 + carry;
            if (sum >= 2) {
                ret.insert(0, sum % 2);
                carry = 1;
            } else {
                ret.insert(0, sum);
                carry = 0;
            }
        }

        if (carry == 1) {
            ret.insert(0, carry);
        }
        return ret.toString();
    }

}
