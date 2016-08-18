package buildingblock.sorting.radix.lc041_firstmissingpositive;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{3,4,-1,1}));
    }

    // My 2nd: still hard to remember... O(N) time
    public int firstMissingPositive(int[] nums) {
        // Radix sort in-place
        for (int i = 0; i < nums.length; ) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                i++;
            } else if (nums[nums[i] - 1] != nums[i]) {  // 1 <= A[i] <= N -> 0 <= A[i]-1 <= N-1
                swap(nums, nums[i] - 1, i);             // Very nice! Swap and "spin", otherwise nested loop is required
            } else {
                i++;                                    // And it works for duplicates. eg.[2,2,2]. i++ if A[A[i]-1]=i, so no dead loop
            }
        }

        // Find first missing number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;     // error: nums[] includes 1..N, so missing is N+1
    }

    // My 1st
    public int firstMissingPositive1(int[] nums) {
        // 1.Sort by radix
        for (int i = 0; i < nums.length; i++) {
            int target = nums[i] - 1;
            while (target != i) {
                if (target < 0) {                   // Case-1: [-1,1] (nums[i]<1)
                    break;
                }
                if (target > nums.length - 1) {     // Case-2: [5,2,3]=>[-1,2,3] (nums[i]>nums.length)
                    nums[i] = -1;
                    break;
                }
                if (nums[target] == nums[i]) {      // Case-3: [2,3,3]=>[3,2,3]=>[-1,2,3] (nums[i]=nums[target],avoid dead loop)
                    nums[i] = -1;
                    break;
                }
                swap(nums, i, target);              // Case-4: [2,3,1]=>[3,2,1]=>[1,2,3]
                target = nums[i] - 1;
            }
        }

        // 2.Find first missing
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int x, int y) {
        int tmp = nums[x];
        nums[x] = nums[y];
        nums[y] = tmp;
    }

}
