package buildingblock.searching.lc069_sqrt;

/**
 * Sqrt(x)
 */
public class Solution {

    // Newton method
    public int mySqrt(int x) {
        if (x == 0) return 0;
        long r = x;
        while (r > x / r)
            r = (r + x / r) / 2;
        return (int) r;
    }

    // My 2nd: preserve invariant. O(logN) time.
    public int mySqrt_binarysearch(int x) {
        if (x <= 1) return x;
        int low = 1, high = x;
        // MustBe(low,high)
        while (low + 1 < high) {    // low+1=high -> low=mid will cause dead loop.
            int mid = low + (high - low) / 2;
            long result = x - (long) mid * mid;
            if (result > 0) {
                low = mid;          // mid*mid<x does NOT mean mid<int(sqrt(x)). eg.2*2<5 but int(sqrt(5))=2.
            } else if (result < 0) {
                high = mid - 1;     // but mid*mid>x must suggest mid>int(sqrt(x)).
            } else {
                return mid;
            }
        }
        // low + 1 == high and MustBe(low,high)
        // => MustBe(low,low+1)
        return ((long) high * high <= x) ? high : low;
    }

    // My 2nd: revised by others solution (invariant violated in the end)
    public int mySqrt2(int x) {
        if (x <= 1) {
            return x;
        }

        int low = 0, high = x;

        // MustBe(low,high)
        while (low <= high) {   // error1: low <= high
            int mid = low + (high - low) / 2;
            long result = x - (long) mid * mid;
            if (result > 0) {
                low = mid + 1;
            } else if (result < 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        // low > high and MustBe(low,high)
        return high;            // error1: high not low, but it violates the invariant
    }

    // Newton method
    public int mySqrt3(int x) {
        if (x <= 1) {
            return x;
        }
        long r = x;
        while (r*r > x)
            r = (r + x/r) / 2;
        return (int) r;
    }

    // My 1st
    public int mySqrt1(int x) {
        if (x < 2) {
            return x;
        }

        int low = 1, high = x / 2;
        int last = 0;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (x / mid > mid) {
                low = mid + 1;
                last = mid;
            } else if (x / mid < mid) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return last;
    }

    public int mySqrt0(int x) {
        if (x <= 1) {
            return x;
        }

        int low = 0, high = x;
        do {
            int mid = (low + high) / 2;
            if (Integer.MAX_VALUE / mid < mid) {
                high = mid;
                continue;
            }

            int sqr = mid * mid;
            if (sqr < x) {
                low = mid;
            } else {
                high = mid;
            }
        } while (low < high);
        return low;
    }

}
