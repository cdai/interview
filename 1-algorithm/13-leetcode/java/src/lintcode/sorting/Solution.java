package lintcode.sorting;

/**
 */
public class Solution {

    // 31-Partition Array
    public int partitionArray(int[] nums, int k) {
        int i = 0; /* invariant: [0,i) < k, [i,j) > k, [j,n) unknown */
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] < k) swap(nums, i++, j);
        }
        return i;
    }

    // 143-Sort colors II
    // Each round put color-1 to front and color-k to back in same manner
    public void sortColors2(int[] colors, int k) {
        int l = 0, r = colors.length - 1;
        for (int c1 = 1, ck = k; c1 < ck && l < r; c1++, ck--) {
            for (int m = l; m <= r; m++) {
                if (colors[m] == c1) swap(colors, l++, m);
                else if (colors[m] == ck) swap(colors, r--, m--);
            }
        }
    }

    // 373 Partition Array by Odd and Even
    public void partitionArray(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] % 2 == 1) swap(nums, i++, j);
        }
    }

    // 
    // 1) Compare nums[i] and nums[l] (pivot), instead of nums[m]!
    // 2) Don't forget to swap nums[l] and nums[m]
    public int median(int[] nums) {
        int l = 0, r = nums.length - 1, k = (nums.length - 1) / 2; // N/2-th if even
        while (l < r) {
            int m = l; /* invariant: [l+1,m) < A[m], [m,i) > A[m], [i,n) unknown */
            for (int i = l + 1; i <= r; i++)
                if (nums[i] < nums[l]) swap(nums, i, ++m);
            swap(nums, l, m);

            if (k < m) r = m - 1;
            else if (k > m) l = m + 1;
            else return nums[m];
        }
        return nums[l];
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // 6-Merge Two Sorted Array
    public int[] mergeSortedArray(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[] C = new int[m + n];
        int i = 0, j = 0, k = 0;
        while (i < m && j < n)
            C[k++] = (A[i] < B[j]) ? A[i++] : B[j++];

        if (i < m) System.arraycopy(A, i, C, k, m - i);
        else System.arraycopy(B, j, C, k, n - j);
        return C;
    }

}
