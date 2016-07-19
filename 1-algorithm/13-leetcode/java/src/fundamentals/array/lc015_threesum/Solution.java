package fundamentals.array.lc015_threesum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 */
public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
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
    public List<List<Integer>> threeSum2(int[] nums) {
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
