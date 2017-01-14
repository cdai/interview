package buildingblock.searching.lc278_firstbadversion;

/**
 * You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
public class Solution extends VersionControl {

    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) { /* invariant: bad version must in [l,r] */
            int m = l + (r - l) / 2;
            if (isBadVersion(m)) r = m; // no need to check, they must be all bad
            else l = m + 1;             // all good before m
        }
        return l;
    }

    // My 2nd
    // My 1st
    public int firstBadVersion2(int n) {
        /* MustBe(0,n) */
        int low = 0, high = n;

        /* MustBe(low,high) */
        // First bad version MustBe(low,high)
        while (low < high) { // low<=high -> low<=mid<=high below causing dead loop
            int mid = low + (high - low) / 2;

            /* low <= mid < high */
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        /* MustBe(low,high) and low=high */
        return low;
    }

}
