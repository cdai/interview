package fundamentals.array.lc324_wigglesort2;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * Example:
 *  (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 *  (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 * Note: You may assume all input has valid answer.
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 */
public class Solution {

    // My 2AC. O(NlogN) time + O(N) space.
    //      n/2-1  vs.  (n-1)/2
    // n=3    0            1     [1,2,3]
    // n=4    1            1     [1,2,3,4]
    // n=5    1            2     [1,2,3,4,5]
    public void wiggleSort(int[] nums) {
        if (nums.length == 0) return;
        Arrays.sort(nums);
        int[] tmp = new int[nums.length];
        int mid = (nums.length - 1) / 2, end = nums.length - 1;
        for (int i = 0; i < tmp.length; i++)
            tmp[i] = (i % 2 == 0) ? nums[mid--] : nums[end--];
        System.arraycopy(tmp, 0, nums, 0, nums.length);
    }

    // My 1AC
    public void wiggleSort1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] tmp = new int[n];
        int mid = (n + 1) >> 1, end = n;                        // error: (n+1)/2 not n/2 or n/2-1 (optimize-1: >> replace /2)
        for (int i = 0; i < n; i++) {                           // difference is even: for 6, (n+1)/2=3 (--mid so it's 2) but n/2=3 cause outofbound
            tmp[i] = (i & 1) == 0 ? nums[--mid] : nums[--end];  // note: odd place holds bigger number got from latter (optimize-2: & replace %)
        }

        for (int i = 0; i < n; i++) {
            nums[i] = tmp[i];
        }
    }

    // Problem with duplicates. eg.[1,3,2,2,3,1] -> [1,3,1,3,2,2] Wrong!
    public void wiggleSort2(int[] nums) {
        // 1.Sort
        List<Integer> numlist = new LinkedList<>();
        for (int n : nums) {
            numlist.add(n);
        }
        Collections.sort(numlist);

        // 2.Wiggle sort
        int n = numlist.size();
        //for (int i = 1, j = n / 2 + 1; i < n && j < n; i += 2, j++) {
        for (int i = 1, j = (n + 1) / 2; i < n && j < n; i += 2, j++) {
            numlist.add(i, numlist.remove(j));
        }

        // 3.Copy back
        for (int i = 0; i < numlist.size(); i++) {
            nums[i] = numlist.get(i);
        }
    }

}
