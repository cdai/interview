package buildingblock.sorting.bucket.lc041_firstmissingpositive;

/**
 * Given an unsorted integer array, find the first missing positive integer.
 * For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().firstMissingPositive(new int[]{3,4,-1,1}));
    }

    public int firstMissingPositive(int[] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            // Move forward whenever cur is "dirty" negative, zero or no need to swap (o.w. dead loop, eg.[1,1])
            while (0 < A[i] && A[i] <= n && A[i] != A[A[i] - 1])
                swap(A, i, A[i] - 1);
        }
        for (int i = 0; i < n; i++) {
            if (A[i] - 1 != i) return i + 1;
        }
        return n + 1;
    }

    // 3AC.
    public int firstMissingPositive_mine(int[] A) {
        if (A.length == 0) return 1;
        for (int i = 0; i < A.length; ) {
            if (A[i] - 1 == i) i++;
            else if (!safeSwap(A, A[i] - 1, i)) i++;
        }
        for (int i = 0; i < A.length; i++) {
            if (A[i] - 1 != i) return i + 1;
        }
        return A.length + 1;
    }

    // Move forward if cur is "dirty" negative, zero or no need to swap (all cause dead loop)
    private boolean safeSwap(int[] A, int i, int j) {
        if (i < 0 || i >= A.length || A[i] == A[j]) return false;
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
        return true;
    }

    // My 2nd: still hard to remember... O(N) time
    public int firstMissingPositive2(int[] nums) {
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
