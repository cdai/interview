package miscellaneous.math.arithmetic.lc067_addbinary;

/**
 * Given two binary strings, return their sum (also a binary string).
 * For example, a = "11", b = "1". Return "100".
 */
public class Solution {

    // My 3AC: O(max(M,N)) time. Avoid insert from front repeatly.
    public String addBinary(String a, String b) {
        StringBuilder ret = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int n1 = (i >= 0) ? a.charAt(i--) - '0' : 0;
            int n2 = (j >= 0) ? b.charAt(j--) - '0' : 0;
            int sum = n1 + n2 + carry;
            ret.append(sum % 2);
            carry = sum / 2;
        }
        return ret.length() == 0 ? "0" : ret.reverse().toString();
    }

    // My 2AC: emulate elegant template code
    public String addBinary2(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int b1 = (i >= 0) ? Character.getNumericValue(a.charAt(i--)) : 0;
            int b2 = (j >= 0) ? Character.getNumericValue(b.charAt(j--)) : 0;
            int sum = b1 + b2 + carry;
            carry = sum / 2;
            result.insert(0, sum % 2);
        }
        return result.length() == 0 ? "0" : result.toString();
    }

    // My 1AC: not bad
    public String addBinary1(String a, String b) {
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
