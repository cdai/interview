package advanced.dyprog.twodim.lc118_pascalstriangle;

import java.util.ArrayList;
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
