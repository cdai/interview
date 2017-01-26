package advanced.datastructure.random.lc398_randompickindex;

import java.util.Random;

/**
 * Given an array of integers with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * To those who don't understand why it works. Consider the example in the OJ
 * {1,2,3,3,3} with target 3, you want to select 2,3,4 with a probability of 1/3 each.
 *
 * 2 : It's probability of selection is 1 * (1/2) * (2/3) = 1/3
 * 3 : It's probability of selection is (1/2) * (2/3) = 1/3
 * 4 : It's probability of selection is just 1/3
 *
 * Explained for 2:
 *  1st iteration: index "2" is definitely picked, since rand.nextInt(1) must be 0. Then "2" is placed in revervoir.
 *  2nd iteration: rand.nextInt(2) could be 0 or 1, so index "2" has 1/2 probability to stay in reservoir.
 *  3rd iteration: rand.nextInt(3) could be 0, 1 or 2, so index "2" has 1/3 probability to be replaced, in other words it has 2/3 to stay there.
 */
public class Solution {

    private int[] nums;

    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int candidate = 0;
        for (int i = 0, j = 1; i < nums.length; i++) {
            if (nums[i] == target) {
                if (rand.nextInt(j++) == 0) // reservoir size = 1
                    candidate = i;
            }
        }
        return candidate;
    }

}
