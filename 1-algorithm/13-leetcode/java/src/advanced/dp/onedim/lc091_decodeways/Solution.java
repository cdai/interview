package advanced.dp.onedim.lc091_decodeways;

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

    public static void main(String[] args) {
        System.out.println(new Solution().numDecodings("1120"));
    }

    // 4AC. Same idea but not very elegant.
    public int numDecodings4(String s) {
        if (s.isEmpty()) return 0;
        int dec0 = 1, dec1 = (s.charAt(0) == '0') ? 0 : 1; // dec0=1 make progress
        for (int i = 1; i < s.length(); i++) {
            int dec2 = 0;
            char c = s.charAt(i), p = s.charAt(i - 1);
            if (c != '0') dec2 += dec1; // cur position decodes individually
            if (p == '1' || (p == '2' && '0' <= c && c <= '6')) dec2 += dec0; // pre+cur decodes together
            dec0 = dec1;
            dec1 = dec2;
        }
        return dec1;
    }

    // way,way2,way1:
    // 1/2,[0] -> way1
    // 3~9,[0] -> 0 (way1)
    // 1~26 -> way1 + way2
    // 0,[0] -> 0
    public int numDecodings_backward(String s) {
        if (s.isEmpty()) return 0;
        int way1 = 1, way2 = (s.charAt(s.length() - 1) == '0') ? 0 : 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            int way = 0;
            if (s.charAt(i) != '0')
                way = (Integer.valueOf(s.substring(i, i + 2))) <= 26 ? way1 + way2 : way2;
            way1 = way2;
            way2 = way;
        }
        return way2;
    }

    // Best solution
    // 1~9:   dp[i-1]
    // 10~26: dp[i-1] + dp[i-2]
    // 0,27~99: 0
    public int numDecodings(String s) {
        if (s.isEmpty()) return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;
        for (int i = 2; i <= s.length(); i++) {
            int one = s.charAt(i - 1) - '0';
            int ten = Integer.valueOf(s.substring(i - 2, i));
            if (1 <= one && one <= 9) dp[i] += dp[i - 1];
            if (10 <= ten && ten <= 26) dp[i] += dp[i - 2];
        }
        return dp[s.length()];
    }

    public int numDecodings1(String s) {
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
