package advanced.greedy.lc011_containerwithmostwater;

/**
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container.
 */
public class Solution {

    // O(n)
    public int maxArea(int[] height) {
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
