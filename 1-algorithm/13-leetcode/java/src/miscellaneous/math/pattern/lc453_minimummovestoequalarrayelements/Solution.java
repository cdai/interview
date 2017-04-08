package miscellaneous.math.pattern.lc453_minimummovestoequalarrayelements;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal,
 * where a move is incrementing n - 1 elements by 1.
 */
public class Solution {

    // "Adding 1 to n - 1 elements is the same as subtracting 1 from one element, w.r.t goal of making the elements in the array equal.
    // So, best way to do this is make all the elements in the array equal to the min element. sum(array) - n * minimum."
    public int minMoves(int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }
        return sum - nums.length * min;
    }

}
