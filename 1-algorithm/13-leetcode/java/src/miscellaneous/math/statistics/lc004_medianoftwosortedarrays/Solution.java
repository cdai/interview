package miscellaneous.math.statistics.lc004_medianoftwosortedarrays;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *  Example 1: nums1 = [1, 3], nums2 = [2]. The median is 2.0
 *  Example 2: nums1 = [1, 2], nums2 = [3, 4]. The median is (2 + 3)/2 = 2.5
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findMedianSortedArrays(
                new int[]{1, 2, 3, 4, 5}, new int[]{0, 1, 6, 7}));
    }

    public double findMedianSortedArrays(int[] A1, int[] A2) {
        int m = A1.length, n = A2.length;
        int l = (m + n + 1) / 2, r = (m + n + 2) / 2; // potential two median number
        return (getKth(A1, 0, m, A2, 0, n, l) + getKth(A1, 0, m, A2, 0, n, r)) / 2.0;
    }

    private int getKth(int[] A1, int s1, int m, int[] A2, int s2, int n, int k) {
        print(A1, s1, m, A2, s2, n, k);
        if (m > n) return getKth(A2, s2, n, A1, s1, m, k);
        if (m == 0) return A2[s2 + k - 1];
        if (k == 1) return Math.min(A1[s1], A2[s2]);

        int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
        if (A1[s1 + i - 1] > A2[s2 + j - 1]) return getKth(A1, s1, m, A2, s2 + j, n - j, k - j);
        else return getKth(A1, s1 + i, m - i, A2, s2, n, k - i); // discard #i num of A1's left half
    }

    private void print(int[] A1, int s1, int m, int[] A2, int s2, int n, int k) {
        System.out.print("A1: [");
        for (int i = s1; i < s1 + m; i++) System.out.printf("%d, ", A1[i]);
        System.out.print("]  A2: [");
        for (int i = s2; i < s2 + n; i++) System.out.printf("%d, ", A2[i]);
        System.out.printf("]  K: %d\n", k);
    }

    // Why?
    public double findMedianSortedArrays_mostupvoted(int[] A1, int[] A2) {
        int n1 = A1.length, n2 = A2.length;
        if (n1 < n2) return findMedianSortedArrays(A2, A1); // A2 is shorter

        int l = 0, r = n2 * 2;
        while (l <= r) {
            int m2 = (l + r) / 2, m1 = n1 + n2 - m2; // cut 1 and cut 2
            double l1 = (m1 == 0) ? Double.MIN_VALUE : A1[(m1 - 1) / 2];
            double l2 = (m2 == 0) ? Double.MIN_VALUE : A2[(m2 - 1) / 2];
            double r1 = (m1 == n1 * 2) ? Double.MAX_VALUE : A1[m1 / 2];
            double r2 = (m2 == n2 * 2) ? Double.MAX_VALUE : A2[m2 / 2];
            if (l1 > r2) l = m2 + 1;        // A1's lower half too big, move cut 1 left
            else if (l2 > r1) r = m2 - 1;   // A2's lower half too big, move cut 2 left
            else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2; // otherwise, correct cut
        }
        return -1;
    }

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }

        int idx1 = (nums1.length + nums2.length - 1) / 2;
        int idx2 = (nums1.length + nums2.length) / 2;

        int med1 = 0, med2 = 0;
        for (int i = 0, j = 0, k = 0; k <= idx2; k++) {
            int n1 = (i < nums1.length) ? nums1[i] : Integer.MAX_VALUE;
            int n2 = (j < nums2.length) ? nums2[j] : Integer.MAX_VALUE;
            int min = (n1 < n2) ? nums1[i++] : nums2[j++];
            if (k == idx1) {
                med1 = min;
            }
            if (k == idx2) {        // error1: idx1=idx2 if total length is odd
                med2 = min;
            }
        }
        return (med1 + med2) / 2.0; // error2: divide by 2.0 instead of 2 to calculate in double
    }

}
