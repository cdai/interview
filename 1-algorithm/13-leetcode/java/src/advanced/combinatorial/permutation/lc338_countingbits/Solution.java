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

    public int[] countBits(int num) {
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
