package miscellaneous.fancy.lc303_rangesumqueryimmutable;

/**
 * sumRange() is O(1), but totally TLE, since matrix could be very huge!
 */
public class NumArray2 {

    // sum[i][j] = sum of [i,j]
    // note: only right half of matrix is available
    private int[][] sum;

    public NumArray2(int[] nums) {
        sum = new int[nums.length][nums.length];
        for (int i = 0; i < sum.length; i++) {
            sum[i][i] = nums[i];
        }
        for (int i = 0; i < sum.length; i++) {
            for (int j = i + 1; j < sum[i].length; j++) {
                sum[i][j] = sum[i][j - 1] + nums[j];
            }
        }
    }

    public int sumRange(int i, int j) {
        return sum[i][j];
    }

}
