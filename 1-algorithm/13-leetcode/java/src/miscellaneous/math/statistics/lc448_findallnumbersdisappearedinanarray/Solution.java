package miscellaneous.math.statistics.lc448_findallnumbersdisappearedinanarray;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    // Test case: [], [1], [1,1], [2,3,2]
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1; // overflow perhaps but array must be very huge
            if (nums[idx] > 0) { // don't swap to idx, just mark nums[idx] since elet in [1,n]
                nums[idx] = -nums[idx];
            }
        }
        List<Integer> miss = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) miss.add(i + 1);
        }
        return miss;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 != i && nums[nums[i] - 1] != nums[i])
                swap(nums, nums[i] - 1, i);
        }
        List<Integer> miss = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - 1 != i) miss.add(i + 1);
        }
        return miss;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

}
