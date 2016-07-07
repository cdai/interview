package buildingblock.searching.lc069_sqrt;

/**
 * Sqrt(x)
 */
public class Solution {

    public int mySqrt(int x) {
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

    public int mySqrt1(int x) {
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
