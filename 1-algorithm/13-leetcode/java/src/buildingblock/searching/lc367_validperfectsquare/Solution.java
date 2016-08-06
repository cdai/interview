package buildingblock.searching.lc367_validperfectsquare;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 * Example 1: Input: 16. Returns: True
 * Example 2: Input: 14. Returns: False
 */
public class Solution {

    public boolean isPerfectSquare(int num) {
        if (num <= 0) {
            return false;
        }

        /* Invariant: MustBe(low,high) */
        /* (1) Initialize: MustBe(1,num) */
        long low = 1, high = num;
        while (low <= high) {   // error1: <= is ok, finally low will > high
            long mid = low + (high - low) / 2;

            /* low <= mid <= high */
            long sqr = mid * mid;   // error2: overflow using Int

            /* low^2 <= sqr < high^2 */
            if (sqr > num) {
                /* low^2 <= num < sqr < high^2 */
                high = mid - 1;
                /* (2) Maintain: MustBe(low,mid-1) */
            } else if (sqr < num) {
                low = mid + 1;
            } else {
                /* (3) Terminate */
                return true;
            }
        }
        /* (3) Terminate: low > high */
        return false;
    }

}
