package advanced.greedy.lc321_createmaximumnumber;

import java.util.Arrays;

/**
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved. Return an array of the k digits.
 */
public class Solution {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] max = {0};
        for (int i = Math.max(0, k - n); i <= Math.min(m, k); i++) { // i<=m, k-i<=n => k-n<=i<=m
            int[] nums = merge(maxRemoveK(nums1, m - i), maxRemoveK(nums2, n - (k - i)));
            if (greater(nums, 0, max, 0)) {
                max = nums;
            }
        }
        return max;
    }

    // Merge two "sorted" array
    private int[] merge(int[] nums1, int[] nums2) {
        int i = 0, j = 0, k = 0, m = nums1.length, n = nums2.length;
        int[] ret = new int[m + n];
        while (i < m && j < n) {
            ret[k++] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        if (i < m) System.arraycopy(nums1, i, ret, k, m - i);
        else System.arraycopy(nums2, j, ret, k, n - j);
        return ret;
    }

    // Max num after removal of k digits
    private int[] maxRemoveK(int[] nums, int k) {
        int top = 0, n = nums.length;
        int[] s = new int[n];
        for (int num : nums) {
            while (top > 0 && k > 0 && num > s[top - 1]) {
                top--;
                k--;
            }
            s[top++] = num;
        }
        return Arrays.copyOfRange(s, 0, top - k);
    }

    private boolean greater(int[] nums1, int s1, int[] nums2, int s2) {
        int m = nums1.length, n = nums2.length;
        int i = s1, j = s2;
        for (; i < m && j < n; i++, j++) {
            if (nums1[i] > nums2[j]) return true;
            if (nums1[i] < nums2[j]) return false;
        }
        return j == n || (i < m && nums1[i] > nums2[j]); // Trick:
    }

    // Wrong! Because original order of digit must be preserved!
    public int[] maxNumber_wrong(int[] nums1, int[] nums2, int k) {
        return divAndConq(nums1, 0, nums1.length, nums2, 0, nums2.length, k);
    }

    private int[] divAndConq(int[] nums1, int s1, int e1, int[] nums2, int s2, int e2, int k) {
        if (k == 1) {
            int max = 0;
            for (int i = s1; i < e1; i++) max = Math.max(max, nums1[i]);
            for (int i = s2; i < e2; i++) max = Math.max(max, nums2[i]);
            return new int[]{ max };
        }
        int m1 = (s1 + e1) / 2, m2 = (s2 + e2) / 2;
        int[] num1 = divAndConq(nums1, s1, m1, nums2, s2, m2, (k + 1)/ 2);
        int[] num2 = divAndConq(nums1, m1, e1, nums2, m2, e2, (k + 1)/ 2);
        return merge(num1, num2, k);
    }

    // O(N) time
    private int[] merge(int[] num1, int[] num2, int k) {
        int[] ret = new int[k];
        for (int i = 0, j = 0, n = 0; n < k; n++) {
            int n1 = i < num1.length ? num1[i] : -1;
            int n2 = j < num2.length ? num2[j] : -1;
            ret[n] = (n1 > n2) ? num1[i++] : num2[j++];
        }
        return ret;
    }

}
