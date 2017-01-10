package advanced.scan.sequence.lc084_largestrectangleinhistogram;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().largestRectangleArea(new int[]{3,2,4}));
    }

    public int largestRectangleArea(int[] height) {
        Stack<Integer> s = new Stack<>();
        int max = 0, n = height.length;
        for (int i = 0; i <= n; i++) { // 0 to empty stack
            int h = (i < n) ? height[i] : 0;
            while (!s.isEmpty() && height[s.peek()] > h) // safe to compute area of s.peek() till previous idx
                max = Math.max(max, height[s.pop()] * (s.isEmpty() ? i : i - 1 - s.peek()));
            s.push(i);
        }
        return max;
    }

    // eg.[1,7,8,5,6,10,11,8] -> [1,x,x,5,6,x,x,8]
    // Key: idx stack=[0,3,4,7], height in gaps (1~2,5~6) MUST greater than left and right!
    // At the end, the stack looks like [0,3,4,7], namely [1,7,8,5,6,10,11,8] -> [1,x,x,5,6,x,x,8].
    // Does that ring a bell with you? Wiggle subsequence!!! h[0] < (h[1] < h[2]) > h[3] < h[4] < (h[5] < h[6]) > h[7].
    //  1) It means that the height in the "gaps" must be taller than left and right.
    //  2) Meanwhile, the height on stack itself is increasing subsequence.
    // These two facts are the reason why it's safe to calculate area by height[j] * (i - 1 - s.peek())
    // where left=s.peek() and right=i-1, since height of all rectangles between left and right must be >= height[j].
    public int largestRectangleArea_leetcode(int[] heights) {
        Stack<Integer> idx = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i]; // Very nice to handle last batch!
            if (idx.isEmpty() || h >= heights[idx.peek()]) {
                idx.push(i);
            } else {
                int j = idx.pop();
                max = Math.max(max, heights[j] * (idx.isEmpty() ? i : (i - 1 - idx.peek())));
                i--;
            }
        }
        return max;
    }

    // My 2AC: more natural than i--. O(N) time
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length) ? 0 : heights[i]; // Very nice to handle last batch!
            while (!s.isEmpty() && h < heights[s.peek()])
                max = Math.max(max, heights[s.pop()] * (s.isEmpty() ? i : (i - 1 - s.peek())));
            s.push(i);
        }
        return max;
    }

    // Nested loop is unnecessary. How to handle last one.
    // Min is a waste, since previous height must be shorter.
    public int largestRectangleArea_howtohandlelastone(int[] heights) {
        Stack<Integer> index = new Stack<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            if (index.isEmpty() || heights[i] >= heights[index.peek()]) {
                index.push(i);
            } else {
                int minH = Integer.MAX_VALUE;
                while (!index.isEmpty() && heights[i] < heights[index.peek()]) {
                    int j = index.pop();
                    minH = Math.min(minH, heights[j]);
                    max = Math.max(max, (i - j) * minH);
                }
                i--;
            }
        }
        return max;
    }

    // Consider [5,1],[2,10],[6,10] but forget [2,1,2] where [1,1,1] is largest (part of last 2)
    public int largestRectangleArea1(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }

        // length/width of max area including i
        int[] length = new int[heights.length];
        int[] width = new int[heights.length];
        length[0] = heights[0];
        width[0] = 1;

        int max = length[0] * width[0];
        for (int i = 1; i < heights.length; i++) {
            if (length[i - 1] <= heights[i]) { // error1
                int area1 = length[i - 1] * (width[i - 1] + 1);
                int area2 = heights[i];
                if (area1 < area2) {
                    length[i] = heights[i];
                    width[i] = 1;
                    max = Math.max(max, area2);
                } else {
                    ++width[i];
                    max = Math.max(max, area1);
                }
            } else {
                int j = i;
                for (; j >= 0 && heights[i] >= heights[j]; j--);
                int area2 = (j - i) * heights[i];
                length[i] = heights[i];
                width[i] = j - i;
                max = Math.max(max, area2);
            }
        }
        return max;
    }

}
