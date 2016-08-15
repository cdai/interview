package advanced.scan.twopointers.lc018_foursum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
 * Find all unique quadruplets in the array which gives the sum of target.
 * Note: The solution set must not contain duplicate quadruplets.
 */
public class Solution {

    // My 2nd: O(N^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) {
                    continue;
                }
                int low = j + 1, high = nums.length - 1;
                int sum = target - nums[i] - nums[j];
                while (low < high) {
                    if (nums[low] + nums[high] > sum) {
                        high--;
                    } else if (nums[low] + nums[high] < sum) {
                        low++;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[low++], nums[high--]));
                        while (low < high && nums[low - 1] == nums[low]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high + 1]) {
                            high--;
                        }
                    }
                }
            }
        }
        return result;
    }

    // My 1st
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                int m = j + 1, n = nums.length - 1;
                while (m < n) {
                    int sum = nums[i] + nums[j] + nums[m] + nums[n];
                    if (sum < target) {
                        do { m++; } while (m < n && nums[m] == nums[m - 1]);    // Trick-1: do-while move pointer
                    } else if (sum > target) {
                        do { n--; } while (m < n && nums[n] == nums[n + 1]);    // Trick-2: m < n for inner loop as outer loop
                    } else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[m], nums[n]));
                        do { m++; } while (m < n && nums[m] == nums[m - 1]);
                        do { n--; } while (m < n && nums[n] == nums[n + 1]);
                    }
                }
                while (j < nums.length - 1 && nums[j] == nums[j + 1]) { j++; }; // Trick-3: while not do-while, since j++ in outer loop and boundary-1
            }
            while (i < nums.length - 2 && nums[i] == nums[i + 1]) { i++; };     // Trick-4: when to bypass, when to iterate
        }
        return result;
    }

}
