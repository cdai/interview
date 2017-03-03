package advanced.datastructure.random.lc384_shuffleanarray;

import java.util.Random;

/**
 */
public class Solution {

    private int[] nums;
    private Random rand = new Random();

    public Solution(int[] nums) {
        this.nums = nums;
    }

    // Nice use of clone (Almost exclusive use of clone)
    public int[] reset() {
        return nums.clone();
    }

    // Goal: Any permutation of nums array must equally likely to be returned
    // Prove: arbitrary element i goes to every position with same probability
    // Elt-i goes to last position = 1/n
    // Elt-i goes to second last position
    //   for elt-(n-1):      (n-1)/n       *      1/(n-1)
    //                  swapped last round   picked again in this round
    //   for elt-(0~n-2):    (n-1)/n       *      1/(n-1)
    //                  stay in last round   picked in this round
    public int[] shuffle() {
        int[] cpy = reset();
        for (int i = cpy.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // j=[0,i)
            swap(cpy, i, j);
        }
        return cpy;
    }

    private void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

}
