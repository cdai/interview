package miscellaneous.design.lc304_rangesumquery2dimmutable;

/**
 */
public class NumMatrix2 {

    public static void main(String[] args) {
        NumMatrix2 matrix = new NumMatrix2(
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

    // Similar to intermediate result table
    private int[][] cacheSum;

    public NumMatrix2(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        this.cacheSum = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                rowSum += matrix[i][j];
                cacheSum[i][j] = rowSum;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (cacheSum == null) {
            return 0;
        }
        int rangeSum = 0;
        if (col1 == 0) {
            for (int i = row1; i <= row2; i++) {
                rangeSum += cacheSum[i][col2];
            }
        } else {
            for (int i = row1; i <= row2; i++) {
                rangeSum += (cacheSum[i][col2] - cacheSum[i][col1 - 1]);
            }
        }
        return rangeSum;
    }

}
