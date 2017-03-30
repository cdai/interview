package buildingblock.table.hashing.lc447_numberofboomerangs;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k)
 * such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Find the number of boomerangs.
 */
public class Solution {

    public int numberOfBoomerangs(int[][] points) {
        int cnt = 0, n = points.length;
        for (int i = 0; i < n; i++) {
            Map<Integer,Integer> map = new HashMap<>();
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    int d = dist(points[i], points[j]);
                    map.put(d, map.getOrDefault(d, 0) + 1);
                }
            }
            // Permutation of points with same distance from point-i
            for (int p : map.values()) {
                cnt += p * (p - 1);
            }
        }
        return cnt;
    }

    private int dist(int[] p1, int[] p2) {
        return (int) (Math.pow(p1[0] - p2[0], 2) + Math.pow(p1[1] - p2[1], 2));
    }

}
