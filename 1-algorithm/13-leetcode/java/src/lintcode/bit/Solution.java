package lintcode.bit;

/**
 */
public class Solution {

    // 140-Fast Power
    // O(logN) time
    // Property of mod arithmetic:
    // (a + b) % p = (a % p + b % p) % p
    // (a - b) % p = (a % p - b % p) % p
    // (a * b) % p = (a % p * b % p) % p
    // (a ^ b) % p = ((a % p)^b) % p
    public int fastPower(int a, int b, int n) {
        if (n == 0) return 1 % b;   // don't forget % b
        long pow = fastPower(a, b, n / 2);
        pow *= pow;
        pow %= b;                   // avoid overflow internally
        if (n % 2 == 1)
            pow = (n > 0) ? pow * (a % b) : pow / (a % b);
        return (int) (pow % b);
    }

}
