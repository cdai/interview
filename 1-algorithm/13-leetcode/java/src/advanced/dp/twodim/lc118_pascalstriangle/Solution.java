package advanced.dp.twodim.lc118_pascalstriangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given numRows, generate the first numRows of Pascal's triangle.
 * For example, given numRows = 5,
 * Return
 *  [
 *      [1],
 *      [1,1],
 *      [1,2,1],
 *      [1,3,3,1],
 *      [1,4,6,4,1]
 *  ]
 */
public class Solution {

    public List<List<Integer>> generate(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    row.add(result.get(i - 1).get(j - 1) + result.get(i - 1).get(j));
                }
            }
            result.add(row);
        }
        return result;
    }

    // My 2AC: O(N^2) time
    public List<List<Integer>> generate_noboundarycheck(int numRows) {
        if (numRows < 1) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> last = result.get(result.size() - 1);
            row.add(1);
            for (int j = 1; j < i; j++) { // No need to worry about outofbound now
                row.add(last.get(j - 1) + last.get(j));
            }
            row.add(1);
            result.add(row);
        }
        return result;
    }

    // My 1AC: complex boundary check
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> lastRow = null;
        for (int i = 0; i < numRows; i++) {
            List<Integer> curRow = new ArrayList<>();
            for (int j = 0; j < i + 1; j++) {
                if (lastRow == null) {
                    curRow.add(1);
                    break;
                }
                if (0 < j && j < i) {
                    curRow.add(lastRow.get(j-1) + lastRow.get(j));
                } else if (0 < j) {
                    curRow.add(lastRow.get(j-1));
                } else {
                    curRow.add(lastRow.get(j));
                }
            }
            result.add(curRow);
            lastRow = curRow;
        }
        return result;
    }

}
