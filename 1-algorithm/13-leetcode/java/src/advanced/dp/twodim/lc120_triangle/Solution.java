package advanced.dp.twodim.lc120_triangle;

import java.util.Arrays;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 *  [
 *      [2],
 *      [3,4],
 *      [6,5,7],
 *      [4,1,8,3]
 *  ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(
                Arrays.asList(
                        Arrays.asList(2),
                        Arrays.asList(3, 4),
                        Arrays.asList(6, 5, 7),
                        Arrays.asList(4, 1, 8, 3)
                )
        );
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.isEmpty()) {
            return 0;
        }

        int[] fn = new int[triangle.size()];
        fn[0] = val(triangle, 0, 0);

        // f(i,j) = min(f(i-1,j-1),f(i-1,j)) + nums[i,j]
        for (int i = 1; i < fn.length; i++) {
            int n1 = Integer.MAX_VALUE, n2 = fn[0];                 // error1: n1=MAX_VAL not 0
            for (int j = 0; j < i + 1; j++) {
                fn[j] = Math.min(n1, n2) + val(triangle, i, j);
                n1 = n2;
                n2 = (j < i - 1) ? fn[j + 1] : Integer.MAX_VALUE;   // error2: j < i-1(last second)
            }
        }

        int min = Integer.MAX_VALUE;
        for (int f : fn) {
            min = Math.min(min, f);
        }
        return min;
    }

    private int val(List<List<Integer>> triangle, int row, int col) {
        return triangle.get(row).get(col);
    }

}
