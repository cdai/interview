package advanced.dp.lc091_decodeways;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *  'A' -> 1
 *  'B' -> 2
 *  ...
 *  'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example, Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12). The number of ways decoding "12" is 2.
 */
public class Solution {

    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || "0".equals(s)) {
            return 0;
        }

        // State f(n) means: number of decoding ways for string[0...n]
        // State transfer:
        //  1. f(n) = 0                 (c[i-1,i]=00,30,40,50...)
        //  2. f(n) = f(n-2)            (c[i-1,i]=10,20)
        //  3. f(n) = f(n-1)            (c[i-1,i]<10)
        //  4. f(n) = f(n-1)+f(n-2)     (c[i-1,i]=(10,26] not 10,20)
        //  5. f(n) = f(n-1)            (c[i-1,i]=[27,99] not 30,40,50...)

        int fn = (s.charAt(0) == '0') ? 0 : 1, fn1 = 1;
        for (int i = 1; i < s.length(); i++) {
            int sum = (s.charAt(i-1) - '0') * 10 + (s.charAt(i) - '0');

            // sum is [1,29]
            int tmp = fn;
            if (sum % 10 == 0) {
                if (sum != 10 && sum != 20) {       // case-1
                    return 0;
                }
                fn = fn1;                           // case-2
            } else {
                if ((10 < sum) && (sum <= 26)) {    // case-4
                    fn = fn + fn1;
                }
            }                                       // case 3/5
            fn1 = tmp;
        }
        return fn;
    }

}
