package miscellaneous.math.statistics.lc229_majorityelement2;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 * Hint: How many majority elements could it possibly have?
 */
public class Solution {

    public List<Integer> majorityElement(int[] nums) {
        // Use two counters to save at most two majority num
        // The left elements at most [0,2/3) is unable to cancel off two counters

        //    [1,1,1,3,5,2,9,4]
        // n1= 1
        // c1= 1 2 3
        // n2=       3   2   4
        // c2=       1 0 1 0 1
        Integer n1 = null, n2 = null;
        int c1 = 0, c2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (n1 != null && nums[i] == n1) {
                c1++;
            } else if (n2 != null && nums[i] == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = nums[i];
                c1 = 1;
            } else if (c2 == 0) {
                n2 = nums[i];
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }

        // Verify n1 and n2
        List<Integer> result = new ArrayList<>();
        c1 = c2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n1) {
                c1++;
            } else if (nums[i] == n2) {
                c2++;
            }
        }
        if (c1 > nums.length / 3) {
            result.add(n1);
        }
        if (c2 > nums.length / 3) {
            result.add(n2);
        }
        return result;
    }

}
