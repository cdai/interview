package buildingblock.sorting.lc004_medianoftwosortedarrays;

/**
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *  Example 1: nums1 = [1, 3], nums2 = [2]. The median is 2.0
 *  Example 2: nums1 = [1, 2], nums2 = [3, 4]. The median is (2 + 3)/2 = 2.5
 */
public class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
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
