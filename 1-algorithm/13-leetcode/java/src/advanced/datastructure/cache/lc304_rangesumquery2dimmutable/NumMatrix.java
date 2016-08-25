package advanced.datastructure.cache.lc304_rangesumquery2dimmutable;

/**
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 * Example:
 * Given matrix = [
 *  [3, 0, 1, 4, 2],
 *  [5, 6, 3, 2, 1],
 *  [1, 2, 0, 1, 5],
 *  [4, 1, 0, 1, 7],
 *  [1, 0, 3, 0, 5]
 * ]
 *  sumRegion(2, 1, 4, 3) -> 8
 *  sumRegion(1, 1, 2, 2) -> 11
 *  sumRegion(1, 2, 2, 4) -> 12
 * Note: You may assume that the matrix does not change. There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class NumMatrix {

    public static void main(String[] args) {
        NumMatrix matrix = new NumMatrix(
                new int[][]{
                        {3, 0, 1, 4, 2},
                        {5, 6, 3, 2, 1},
                        {1, 2, 0, 1, 5},
                        {4, 1, 0, 1, 7},
                        {1, 0, 3, 0, 5}
                }
        );
        System.out.println(matrix.sumRegion(2, 1, 4, 3));
        System.out.println(matrix.sumRegion(1, 1, 2, 2));
        System.out.println(matrix.sumRegion(1, 2, 2, 4));

        System.out.println(matrix.sumRegion(1, 0, 2, 4));
        System.out.println(matrix.sumRegion(1, 1, 1, 1));
    }

    // cacheSum[i][j] = sum from (0,0) to (i,j)
    private int[][] cacheSum;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        this.cacheSum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                cacheSum[i][j] = (i > 0) ? (cacheSum[i - 1][j] + rowSum) : rowSum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (cacheSum == null) {
            return 0;
        }
        int rangeSum = cacheSum[row2][col2];
        if (row1 > 0) {
            rangeSum -= cacheSum[row1 - 1][col2];
        }
        if (col1 > 0) {
            rangeSum -= cacheSum[row2][col1 - 1];
        }
        if (col1 > 0 && row1 > 0) {     // error: add overlap region if any
            rangeSum += cacheSum[row1 - 1][col1 - 1];
        }
        return rangeSum;
    }

}
