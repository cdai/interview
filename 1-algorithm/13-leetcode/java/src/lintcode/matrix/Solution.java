package lintcode.matrix;

/**
 */
public class Solution {

    // 185-Matrix ZigZag Traversal
    // [1, 2,  3,  4]
    // [5, 6,  7,  8]
    // [9, 10, 11, 12]
    // 1->2, 5->9: move right or down one step each time
    // 9->6->3, 4->7->10: zig or zag until reach boundary
    // At 10, impossible to move down, next direction is back to 7, need to avoid!
    // At 8, impossible to move right, next is to back to 11, avoid too!
    public int[] printZMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length, mn = m * n;
        int[] ret = new int[mn];
        int[][] dirs = {{0, 1}, {1, -1}, {1, 0}, {-1, 1}};
        int x = 0, y = 0, lastx = 0, lasty = 0;
        ret[0] = matrix[x][y];
        for (int i = 1, d = 0; i < mn; ) {
            int x2 = x + dirs[d][0], y2 = y + dirs[d][1];
            if (x2 < 0 || x2 >= m || y2 < 0 || y2 >= n || (x2 == lastx && y2 == lasty)) {
                d = (d + 1) % dirs.length; // out of boundary or go backward, try next direction
            } else {
                if (d % 2 == 0) d = (d + 1) % dirs.length; // move right/down only one step
                lastx = x; lasty = y;
                x = x2; y = y2;
                ret[i++] = matrix[x][y];
            }
        }
        return ret;
    }

}
