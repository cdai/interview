package advanced.scan.twopointers.lc011_containerwithmostwater;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 */
public class Solution {

    // My 2AC: When left=right, move whatever will be ok
    // [1,x,x,x,x,7]: no need to calculate all [1,x] pairs since area is determined by 1
    // [o,8,x,x,x,7]: no need to calculate all [x,7] pairs for same reason
    // [o,8,x,x,5,o]
    // [o,8,x,9,o,o]
    // [o,o,7,9,o,o] - done!
    // Same as two sum O(N) solution, eg. target=4:
    // [1,x,x,x,x,8]: no need to check all [x,8] pairs because they're greater than 1+8
    // [1,x,x,x,6,o]
    // [1,x,x,2,o,o]: no need to check all [1,x] pairs because they're smaller than 1+2
    // [o,1,x,2,o,o]
    // [o,o,1,2,o,o]
    public int maxArea(int[] height) {
        int max = 0, n = height.length;
        for (int l = 0, r = n - 1; l < r; ) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    // My 1AC: O(n)
    public int maxArea1(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int left = 0, right = height.length - 1;
        int result = 0;
        while (left < right) {
            result = Math.max(result, Math.min(height[left], height[right]) * (right - left));
            if (height[left] < height[right]) { // Move "right" must cause <= result, since result is decided by min(left,right)
                left++;
            } else if (height[left] == height[right]) {
                left++;
                right--;
            } else {
                right--;
            }
        }
        return result;
    }

    // This makes more sense: eg.[2,5,3,3,10,2,6] -> min(5,10)*(4-1) = 15
    public int maxArea2(int[] height) {
        if (height.length < 2) { // At least two lines exist
            return 0;
        }

        int last = 0, max = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= height[last]) {
                max = Math.max(max, Math.min(height[i], height[last]) * (i - last));
                last = i;
            }
        }
        return max;
    }

}
