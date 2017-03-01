package advanced.scan.twopointers.lc015_threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class Solution {

    // Also work...
    // 1.Sort first
    // 2.Stop at len-2
    // 3.Check duplicate
    // 4.Dead loop when found solution or duplicate
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ret = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1, k = n - 1; j < k; ) {
                int sum = nums[i] + nums[j] + nums[k];
                if (j > i + 1 && nums[j - 1] == nums[j]) j++;
                else if (sum < 0) j++;
                else if (sum > 0) k--;
                else ret.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
            }
        }
        return ret;
    }

    // Minor improvement on duplicates bypass from leetcode discuss
    // A little faster than previous
    public List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue; // Bypass duplicate!
            for (int l = i + 1, r = n - 1; l < r; ) {
                if (nums[i] + nums[l] + nums[r] < 0) l++;
                else if (nums[i] + nums[l] + nums[r] > 0) r--;
                else {
                    result.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    while (l < r && nums[l] == nums[l - 1]) l++; // Bypass duplicate!
                    while (l < r && nums[r] == nums[r + 1]) r--;
                }
            }
        }
        return result;
    }

    // My 2nd: O(N^2) with normal 2Sum sweep
    // You can find some similarity between this and combination version above
    // See how this one iterate in the search space and why it's faster?
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {  // Common trick to bypass duplicate. Better than a nested while loop!
                continue;                           // But it's quite annoying to bypass in nested loop. eg.[-2,0,0,2,2]
            }
            int target = 0 - nums[i];
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                if (nums[left] + nums[right] < target || (left > i + 1 && nums[left - 1] == nums[left])) {
                    left++;
                } else if (nums[left] + nums[right] > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) {
                    right--;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    // My 2nd: combination with O(N*N-1*N-2)
    // eg.[-2,0,0,1,2,2]
    //      ---root----
    //     /    |   |  \
    //   -2     0   1  2
    //  / | \ / | \  \
    //  0 1 2 0 1 2   2
    // /\\
    // 012 ...
    public List<List<Integer>> threeSum22(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        doThreeSum(result, new ArrayList<>(), nums, 0, 0);
        return result;
    }

    private void doThreeSum(List<List<Integer>> result, List<Integer> path, int[] nums, int target, int start) {
        if (path.size() == 3) {
            if (target == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i - 1] == nums[i]) {
                continue;
            }
            path.add(nums[i]);
            doThreeSum(result, path, nums, target - nums[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    // My 1st: ugly...
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }

        // 1.Sort for scan
        Arrays.sort(nums);

        // 2.Find target in each [i,j] interval
        for (int i = 0; i < nums.length - 2; i++) {             // Error1: must from end to start, or [-4,0,2,2] will skip [-4,2,2]
            for (int j = nums.length - 1; j > i + 1; j--) {     // Error2: must perform on every [i,j]
                findInTheMiddle(result, nums, i, j);
                while (j > i + 1 && nums[j] == nums[j - 1]) {   // trick: nums[j]!=nums[j+1] then j++ will move to nums[j+1]
                    j--;
                }
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return result;
    }

    // Error: findInTheMiddle() should perform on i...j
    public List<List<Integer>> threeSum1_2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        // Squeeze from left and right
        int i = 0, j = nums.length - 1;
        while (i + 1 < j) { // at least 3 numbers in the middle
            // 1.Skip the same number
            int ileft = i + 1;
            for ( ; (ileft + 1 < j) && (nums[ileft] == nums[ileft - 1]); ileft++);
            int jright = j - 1;
            for ( ; (i + 1 < jright) && nums[jright] == nums[jright + 1]; jright--);

            // 2.Find target from left half or right half
            findInTheMiddle(result, nums, i, j);
            findInTheMiddle(result, nums, i, jright);
            findInTheMiddle(result, nums, ileft, j);

            // 3.Squeeze
            i = ileft;
            j = jright;
        }
        return result;
    }

    private void findInTheMiddle(List<List<Integer>> result, int[] nums, int i, int j) {
        int target = 0 - nums[i] - nums[j];
        if (target >= 0) {                      // Case-1: we need a positive or zero
            for (int k = j - 1; (k > i) && (nums[k] >= target); k--) {
                if (nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[k], nums[j]));
                    break;
                }
            }
        } else {                                // Case-2: we need a negative
            for (int k = i + 1; (k < j) && (nums[k] <= target); k++) {
                if (nums[k] == target) {
                    result.add(Arrays.asList(nums[i], nums[k], nums[j]));
                    break;
                }
            }
        }
    }

}
