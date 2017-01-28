package miscellaneous.math.statistics.lc442_findallduplicatesinanarray;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    // Mark num on target position (j) at first time
    // Add num represented by j to dups at second time
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> dups = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int j = Math.abs(nums[i]) - 1;
            if (nums[j] < 0) {
                dups.add(j + 1);
            } else {
                nums[j] = -nums[j];
            }
        }
        return dups;
    }

    public List<Integer> findDuplicates1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int j;
            while (nums[i] > 0 && (j = nums[i] - 1) != i && nums[j] > 0) {
                if (nums[j] == nums[i]) {
                    nums[j] = -nums[j];
                    break;
                } else swap(nums, i, j);
            }
        }
        List<Integer> dups = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) dups.add(i + 1);
        }
        return dups;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
