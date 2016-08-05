package fundamentals.stack.lc084_largestrectangleinhistogram;

/**
 */
public class Solution {

    // Consider [5,1],[2,10],[6,10] but forget [2,1,2] where [1,1,1] is largest (part of last 2)
    public int largestRectangleArea(int[] heights) {
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
