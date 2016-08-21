package miscellaneous.math.arithmetic.lc066_plusone;

/**
 * Given a non-negative number represented as an array of digits, plus one to the number.
 * The digits are stored such that the most significant digit is at the head of the list.
 */
public class Solution {

    // Extremely simple and beautiful solution
    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;                        // Use original array
                return digits;                      // No carry then return immediately
            }
            digits[i] = 0;                          // No carry variable required.
        }
        int[] result = new int[digits.length + 1];
        result[0] = 1;                              // Least significant digits are 0 default. No copy need. So smart!
        return result;
    }

    // My 2AC: a little better but don't take advantage of plus one...
    public int[] plusOne2(int[] digits) {
        int[] result = new int[digits.length];
        int carry = 1;      // Put that "plus one" here
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            carry = sum / 10;
            result[i] = sum % 10;
        }

        // Deal with last carry (if digits is empty, this return [1] correctly)
        if (carry > 0) {
            int[] tmp = new int[result.length + 1];
            tmp[0] = carry;
            for (int i = 1; i < tmp.length; i++) {
                tmp[i] = result[i - 1];
            }
            result = tmp;
        }
        return result;
    }

    // My 1AC
    public int[] plusOne1(int[] digits) {
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
