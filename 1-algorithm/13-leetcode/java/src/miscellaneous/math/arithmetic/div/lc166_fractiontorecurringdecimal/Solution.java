package miscellaneous.math.arithmetic.div.lc166_fractiontorecurringdecimal;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 *  Given numerator = 1, denominator = 2, return "0.5".
 *  Given numerator = 2, denominator = 1, return "2".
 *  Given numerator = 2, denominator = 3, return "0.(6)".
 * Note: No scary math, just apply elementary math knowledge.
 * Still remember how to perform a long division? Try a long division on 4/9, the repeating part is obvious.
 * Now try 4/333. Do you see a pattern? Be wary of edge cases!
 * List out as many test cases as you can think of and test your code thoroughly.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().fractionToDecimal(1, 17));
    }

    // Test case: positive, negative, no floating part, 0
    // Error: 1/11=0.(09), 1/90=0.0(1), 1/6=0.1(6), 1/17=0.(0588235294117647). Found pattern?
    public String fractionToDecimal(int numerator, int denominator) {
        // 1.Divided by zero
        if (denominator == 0) {
            return "";
        }

        // 2.Positve or negative
        boolean isNeg = false;
        if ((numerator > 0 && denominator < 0) || (numerator < 0 && denominator > 0)) {
            isNeg = true;
        }
        long numer = numerator, denom = denominator; // error3: avoid overflow, must cast first
        numer = Math.abs(numer);
        denom = Math.abs(denom);

        StringBuilder frac = new StringBuilder();
        frac.append(isNeg ? "-" : "")
                .append(numer / denom);

        // 4.Integer part: 0%N=0
        numer %= denom;
        if (numer == 0) {
            return frac.toString();
        }
        frac.append(".");

        // 5.Float part: divide until remaining is 0 (simulating long division)
        Map<Long, Integer> digits = new HashMap<>(); // error2: determine repeating pattern length. Key!!!
        while (numer != 0) {
            digits.put(numer, frac.length());      // error1: forget...

            while ((numer *= 10) < denom) {
                frac.append("0");
                digits.put(numer, frac.length());  // error2: 1*10000%9000=1000, should terminate too
            }

            // Now numerator >= denominator
            long div = numer / denom;
            numer = numer % denom;
            if (digits.containsKey(numer)) {
                frac.insert(digits.get(numer), "(")  // error2: where to put "(", this is the key problem...
                        .append(div)
                        .append(")");
                break;
            }

            frac.append(div);
        }
        // Overflow...
        return frac.toString();
    }

}

