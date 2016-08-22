package advanced.dp.twodim.lc119_pascalstriangle2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3, Return [1,3,3,1].
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 */
public class Solution {

    // Very smart solution from leetcode discuss
    // Take advantage of symmetry of Pascal's triangle
    // i=0: [1]
    // i=1: [1,1]
    // i=2: [1,1,1] -> [1,2,1]
    // i=3: [1,1,2,1] -> [1,3,2,1] -> [1,3,3,1]
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            row.add(0, 1);                  // Each round insert a new 1 at the head.
            for (int j = 1; j < i; j++) {   // Then nums of last round will off-by-one behind
                row.set(j, row.get(j) + row.get(j + 1));
            }
        }
        return row;
    }

    // My 2AC. Inspired by solution above.
    // i=0: [1]
    // i=1: [1,1]
    // i=2: [1,1,1] -> [1,2,1]
    // i=3: [1,2,1,1] -> [1,2,3,1] -> [1,3,3,1]
    public List<Integer> getRow(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        Arrays.fill(row, 1);
        for (int i = 0; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                row[j] += row[j - 1];
            }
        }
        return Arrays.asList(row);
    }

    // My 1AC: don't take advantage of symmetry
    public List<Integer> getRow1(int rowIndex) {
        int[] row = new int[rowIndex + 1];
        row[0] = 1;

        for (int i = 1; i <= rowIndex; i++) {
            // Rolling row[] after remembering n1,n2
            int n1 = 0, n2 = 1;
            for (int j = 0; j < i + 1; j++) {
                row[j] = n1 + n2;
                n1 = n2;
                n2 = (j < i) ? row[j + 1] : 0; // j < i-1 ?
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int n : row) {
            result.add(n);
        }
        return result;
    }

}
