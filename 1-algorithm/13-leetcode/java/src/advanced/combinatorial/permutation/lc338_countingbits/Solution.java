package advanced.combinatorial.permutation.lc338_countingbits;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 * Example: For num = 5 you should return [0,1,1,2,1,2].
 * Follow up: It is very easy to come up with a solution with run time O(n*sizeof(integer)).
 * But can you do it in linear time O(n) /possibly in a single pass? Space complexity should be O(n).
 * Hint: You should make use of what you have produced already. Divide the numbers in ranges like [2-3], [4-7], [8-15] and so on. And try to generate new range from previous. Or does the odd/even status of the number help you in calculating the number of 1s?
 */
public class Solution {

    // Just like Gray Code:
    // Num - 1-Bit
    //  0   -   0
    // -----------
    //  1   -   1
    // -----------
    //  2   -   1
    //  3   -   2
    // -----------
    //  4   -   1
    //  5   -   2
    //  6   -   2
    //  7   -   3
    // -----------
    //  8   -   2
    //  9   -   3
    // 10   -   3
    // 11   -   4
    // 12   -   2
    // 13   -   3
    // 14   -   3
    // 15   -   4
    // -----------
    // [0] + 1 => [1]
    // [0,1] + 1 => [2,3]
    // [0,3] + 1 => [4,7]
    // [0,7] + 1 => [8,15]...
    public int[] countBits(int num) {
        int[] bit1s = new int[num + 1];
        for (int i = 1, j = 1; i <= num; i++) {
            if (i == j * 2) {
                j <<= 1;
            }
            bit1s[i] = bit1s[i - j] + 1; // assert: i - j >= 0
        }
        return bit1s;
    }

    // My 1AC: no need to treat bits[0,1] specially
    public int[] countBits1(int num) {
        if (num == 0) {
            return new int[] { 0 };
        }

        int[] bits = new int[num + 1];
        bits[0] = 0;
        bits[1] = 1;

        // 0~1: 0,1
        // 2~3: 10,11
        // 4~7: 100,101,110,111 (1 + 0,1,2,3)
        // current range = 1 leading to all numbers before
        for (int i = 2, base = 2; i <= num; i++) {
            bits[i] = 1 + bits[i - base];
            if (i == base * 2 - 1) {
                base *= 2;
            }
        }
        return bits;
    }

}
