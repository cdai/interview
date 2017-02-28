package advanced.greedy.lc452_minimumnumberofarrowstoburstballoons;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Find the minimum number of arrows that must be shot to burst all balloons.
 */
public class Solution {

    // All considered overlapping, must exist a vertical line burst all of them:
    // [-----]
    //    [----]
    //   [----]
    //       [---]
    //  [-][-] - impossible earlier finish time
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
        long max = 0, last = Long.MIN_VALUE;
        for (int[] p : points) {
            if (last < p[0]) { // an arrow shot at x if xstart ≤ x ≤ xend
                last = p[1];
                max++;
            }
        }
        return (int) max;
    }

}
