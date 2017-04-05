package miscellaneous.math.counting.lc413_arithmeticslices;

/**
 * The function should return the number of arithmetic slices in the array A.
 */
public class Solution {

    // [1,2,3]       = 1
    // [1,2,3,4]     = 1 + 2 ([2,3,4], [1,2,3,4])
    // [1,2,3,4,5]   = 3 + 3 ([3,4,5], [2,3,4,5], [1,2,3,4,5])
    // [1,2,3,4,5,6] = 6 + 4 ...
    public int numberOfArithmeticSlices(int[] A) {
        int cnt = 0;
        for (int i = 2, len = 2; i < A.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                cnt += (len++ - 1);
            } else {
                len = 2;
            }
        }
        return cnt;
    }

}
