package miscellaneous.bitmanipulation.xor.lc136_singlenumber;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note: Your algorithm should have a linear runtime complexity.
 * Could you implement it without using extra memory?
 */
public class Solution {

    public int singleNumber(int[] nums) {
        // Xor: https://en.wikipedia.org/wiki/Exclusive_or
        // Property-1: A^0=A (e.g. 0^1=0, 1^1=0)
        int result = 0;

        // Property-2 (Commutativity & Associativity): A^B^C=A^C^B=A^(C^B)
        // Property-3 (Non-idempotency): A^A=0
        // Property-4 (Falsehood-preserving): 0^0^...^0=0
        for (int n : nums) {
            result ^= n;
        }

        // Therefore: A^B^C^D^E^B^A^D^C = E^(A^A)^...(D^D) = E^0^0^0 = E^0 = E.
        return result;
    }

}
