package advanced.datastructure.geometry.lc296_bestmeetingpoint;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Solution {

    public int minTotalDistance(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        List<Integer> I = new ArrayList<>(), J = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) I.add(i);
            }
        }
        for (int j = 0; j < n; j++) { // Force J is in order
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) J.add(j);
            }
        }
        return minDist(I) + minDist(J);
    }

    private int minDist(List<Integer> points) {
        int dist = 0;
        for (int i = 0, j = points.size() - 1; i < j; i++, j--) {
            dist = points.get(j) - points.get(i);
        }
        return dist;
    }

}
