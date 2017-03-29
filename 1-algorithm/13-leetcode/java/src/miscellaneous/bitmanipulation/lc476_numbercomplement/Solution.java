package miscellaneous.bitmanipulation.lc476_numbercomplement;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 */
public class Solution {



    public int findComplement_my(int num) {
        int comp = 0;
        for (int i = 0; num != 0; i++, num /= 2) {
            if (num % 2 == 0) {
                comp |= 1 << i;
            }
        }
        return comp;
    }

}
