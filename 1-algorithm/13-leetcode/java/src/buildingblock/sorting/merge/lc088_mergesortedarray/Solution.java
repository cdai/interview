package buildingblock.sorting.merge.lc088_mergesortedarray;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note: You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
public class Solution {

    // Too crazy...
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        while (n > 0) {
            nums1[m + n - 1] = (m == 0 || nums2[n - 1] > nums1[m - 1]) ? nums2[--n] : nums1[--m];
        }
    }

    // My 2nd: idea is good, but it could have been simpler
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m - 1, j = n - 1, k = m + n - 1; k >= 0; k--) {
            int n1 = (i >= 0) ? nums1[i] : Integer.MIN_VALUE;
            int n2 = (j >= 0) ? nums2[j] : Integer.MIN_VALUE;
            nums1[k] = (n1 > n2) ? nums1[i--] : nums2[j--];
        }
    }

    // My 1st: not elegant
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = (nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

}
