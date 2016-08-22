package advanced.dp.twodim.lc120_triangle;

import java.util.ArrayList;
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
                new Solution().minimumTotal(
                Arrays.asList(
                        Arrays.asList(2),
                        Arrays.asList(3, 4),
                        Arrays.asList(6, 5, 7),
                        Arrays.asList(4, 1, 8, 3)
                ))
        );
        System.out.println(new Solution().minimumTotal(
                param(new Integer[][]{{-7},{-2,1},{-5,-5,9},{-4,-5,4,4},{-6,-6,2,-1,-5},{3,7,8,-3,7,-9}})
        ));
    }

    private static List<List<Integer>> param(Integer[][] triangle) {
        List<List<Integer>> result = new ArrayList<>();
        for (Integer[] row : triangle) {
            result.add(Arrays.asList(row));
        }
        return result;
    }

    // So briliant solution! No need to worry default 0 affect Math.min
    //    [2],
    //   [3,4],
    //  [6,5,7],
    // [4,1,8,3]
    // i=3: [4,1,8,3,0]
    // i=2: [7,1,8,3,0] -> [7,6,8,3,0] -> [7,6,10,3,0]
    // i=1: [9,1,8,3,0] -> [9,10,8,3,0]
    // i=0: [11,10,8,3,0]
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] sum = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                sum[j] = Math.min(sum[j], sum[j + 1]) + triangle.get(i).get(j);
            }
        }
        return sum[0];
    }

    // Wrong! Since tri[i][n] relys on tri[i-1][n-1] and tri[i-1][n]. So tri[i-1][n-1] will be overriden if rolling
    public int minimumTotal_wrongrollinghash(List<List<Integer>> triangle) {
        if (triangle.isEmpty() || triangle.get(0).isEmpty()) {
            return 0;
        }

        int[] sum = new int[triangle.size()];
        sum[0] = triangle.get(0).get(0);

        // Be careful to roll sum state
        for (int i = 1; i < triangle.size(); i++) {
            List<Integer> row = triangle.get(i);
            int n = row.size() - 1;
            sum[n] = sum[n - 1] + row.get(n);   // Update last one since N-1 is gonna be override soon and no one use N in this round
            for (int j = 1; j < n; j++) {
                sum[j] = Math.min(sum[j - 1], sum[j]) + row.get(j);
            }
            sum[0] += row.get(0);               // First one is safe to update now
        }

        int min = Integer.MAX_VALUE;
        for (int s : sum) {
            min = Math.min(min, s);
        }
        return min;
    }

    // My 1AC
    public int minimumTotal1(List<List<Integer>> triangle) {
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
