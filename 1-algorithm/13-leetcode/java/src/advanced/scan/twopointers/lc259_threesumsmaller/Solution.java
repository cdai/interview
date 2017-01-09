package advanced.scan.twopointers.lc259_threesumsmaller;

import java.util.Arrays;

/**
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that
 * satisfy the condition nums[i] + nums[j] + nums[k] < target.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.threeSumSmaller(new int[]{-2, 0, 1, 3}, 2));
    }

    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1, k = n - 1; j < k;) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    cnt += k - j; // all k-j pairs between j and k, (j,j+1),(j,j+2)...(j,k)
                    j++;
                } else k--;
            }
        }
        return cnt;
    }

}
