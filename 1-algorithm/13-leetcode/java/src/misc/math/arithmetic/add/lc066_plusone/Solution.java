package misc.math.arithmetic.add.lc066_plusone;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class Solution {

    public int[] plusOne(int[] digits) {
        if (digits.length == 0) {
            return new int[]{ 1 }; // the judge needs...
        }

        int carry = 0;
        digits[digits.length - 1]++;    // error3: last digit not first
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            if (sum >= 10) {
                digits[i] = sum % 10;
                carry = 1;
            } else {
                digits[i] = sum;
                carry = 0;      // error2: reset carry
                break;
            }
        }

        if (carry == 1) {
            int[] newdigits = new int[digits.length + 1];
            newdigits[0] = carry;
            for (int i = 1; i < newdigits.length; i++) {
                newdigits[i] = digits[i - 1];   // error1: digits[i-1]
            }
            return newdigits;
        }
        return digits;
    }

}
