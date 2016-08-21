package advanced.dp.twodim.lc119_pascalstriangle2;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3, Return [1,3,3,1].
 * Note: Could you optimize your algorithm to use only O(k) extra space?
 */
public class Solution {

    public List<Integer> getRow(int rowIndex) {
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
