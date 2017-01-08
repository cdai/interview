package lintcode.sorting;

/**
 * Created by cdai on 1/8/17.
 */
public class Solution {

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

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

}
