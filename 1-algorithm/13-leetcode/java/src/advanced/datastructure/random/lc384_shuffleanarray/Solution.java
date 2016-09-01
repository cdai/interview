package advanced.datastructure.random.lc384_shuffleanarray;

import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * Example:
 *  // Init an array with set 1, 2, and 3.
 *  int[] nums = {1,2,3};
 *  Solution solution = new Solution(nums);
 *  // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 *  solution.shuffle();
 *  // Resets the array back to its original configuration [1,2,3].
 *  solution.reset();
 *  // Returns the random shuffling of array [1,2,3].
 *  solution.shuffle();
 */
public class Solution {

    private int[] nums;

    private int[] shuffled;

    private Random rand;

    public Solution(int[] nums) {
        this.nums = nums;
        this.shuffled = new int[nums.length];
        this.rand = new Random();
        reset();
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        System.arraycopy(nums, 0, shuffled, 0, nums.length);
        return shuffled;
    }

    // P[ith elet goes to 1st position] = 1/n
    // P[ith elet goes to 2st position] = n-1/n (i doesn't move in last round) * 1/n-1 = 1/n
    // ...
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for (int i = 0; i < shuffled.length; i++) {
            int idx = rand.nextInt(shuffled.length - i);
            int tmp = shuffled[i];
            shuffled[i] = shuffled[i + idx];
            shuffled[i + idx] = tmp;
        }
        return shuffled;
    }

}
