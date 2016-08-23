package miscellaneous.bitmanipulation.lc137_singlenumber2;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note: Your algorithm should have a linear runtime complexity.
 *  Could you implement it without using extra memory?
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(
                new Solution().singleNumber(new int[]{
                        4, 2, 4, 4
                })
        );
        System.out.println(singleNumber5(new int[]{2,1,2,2,1,2,2,1,3,1,1}));
    }

    //    c b a num
    // 0: 0 0 0  1
    // 1: 0 0 1  1
    // 2: 0 1 0  1
    // 3: 0 1 1  1
    // 4: 1 0 0  1
    // 0: 0 0 0  1
    public static int singleNumber5(int[] nums) {
        int one = 0, two = 0, three = 0;
        for (int num : nums) {
            two = two ^ (one & num);
            one = one ^ num ^ three;
            three = three ^ (num & one & two);
        }
        return one;
    }

    // Each bit in ones and same bit in twos combinely means: number of 1's for all nums
    // Importantly, it's wrapped by 3: 00 -> 01 -> 10 -> 00 ...
    // Eg.nums=[ 4,  2,  4,  4 ]
    // one:     100 110 010 010
    // two:     000 000 100 000
    // 2nd bit: 01 ->    -> 10 -> 00
    // 3rd bit:       01 ->
    public int singleNumber2(int[] nums) {
        int ones = 0, twos = 0;
        for (int i = 0; i < nums.length; i++) {
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
    }

    // Explaination of elegant solution above
    // we count each bit 1's by hand, one num one bit.
    public int singleNumber(int[] nums) {
        // Count number of 1's on every bit
        byte[] count = new byte[Integer.SIZE];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < Integer.SIZE; j++) {
                count[j] += ((nums[i] >> j) & 1);
                count[j] %= 3;                      // error: must % here to avoid overflow if we use byte[]
            }
        }

        // Belong to target if number of 1's is NOT divisible by 3
        int single = 0;
        for (int i = 0; i < Integer.SIZE; i++) {
            single |= (count[i] << i);
        }
        return single;
    }

}
