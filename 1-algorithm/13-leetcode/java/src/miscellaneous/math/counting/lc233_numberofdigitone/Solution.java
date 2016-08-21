package miscellaneous.math.counting.lc233_numberofdigitone;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example: Given n = 13. Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class Solution {

    // Stefan solution:log10N=O(logN)
    // Eg.3101592:
    // 1) #number at 100 digit = 1 (< 5): [1~3101]1[0~99]. So # = 3101 * 100 + 100 (Safe since 31011XX never be greater 31015XX)
    // 2) #number at 1k digit  = 1 (= 1): [1~310]1[0~592]. So # = 310 * 1000 + (592 + 1) (Unsafe, if prefix is 0, it must be <= 1592)
    // 3) #number at 10k digit = 1 (> 0): [1~30]1[0~9999]. So # = 30 * 10000 (If prefix is 0, no 1 digit number exist)
    public int countDigitOne(int n) {
        long count = 0;
        // q % 10 = current digit, n / i*10 = prefix, n % i = suffix
        for (long i = 1, q = n; q > 0; i *= 10, q /= 10) { // overflow: eg.1413910980
            long d = q % 10;
            count += (n / (i * 10)) * i;
            count += (d > 1) ? i : (d == 1 ? n % i + 1: 0);
        }
        return (int) count;
    }

}
