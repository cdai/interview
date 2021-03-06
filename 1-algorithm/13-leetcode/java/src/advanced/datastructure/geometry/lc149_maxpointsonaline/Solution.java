package advanced.datastructure.geometry.lc149_maxpointsonaline;

import advanced.datastructure.geometry.Point;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 */
public class Solution {

    // O(N^2) time, O(N) space: Calculate max points on a line from a point one by one
    // local= #max points of line crossing p[i] (exclude p[i])
    // dup= #same points as p[i]
    // local max = local + dup + 1
    // global max = max(global max, local max)
    public int maxPoints(Point[] points) {
        int max = 0, n = points.length;
        for (int i = 0; i < n; i++) {
            Map<String, Integer> map = new HashMap<>();
            int dup = 0, local = 0;
            for (int j = i + 1; j < n; j++) {
                int x = points[j].x - points[i].x;
                int y = points[j].y - points[i].y;
                int d = gcd(x, y);
                if (d == 0) {
                    dup++;
                } else {
                    String slope = (x / d) + "_" + (y / d);
                    map.put(slope, map.getOrDefault(slope, 0) + 1);
                    local = Math.max(local, map.get(slope));
                }
            }
            max = Math.max(max, local + dup + 1);
        }
        return max;
    }

    private int gcd2(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // O(N^2) time, O(N) space
    // local= #max points of line crossing p[i] (exclude p[i])
    // dup= #same points as p[i]
    // local max = local + dup + 1
    // global max = max(global max, local max)
    public int maxPoints1(Point[] points) {
        if (points.length <= 2) return points.length;
        int max = 0;
        for (int i = 0; i < points.length - 1; i++) {
            Map<List<Integer>,Integer> lineCnt = new HashMap<>();
            int dup = 0, local = 0;
            for (int j = i + 1; j < points.length; j++) {
                int x = points[i].x - points[j].x, y = points[i].y - points[j].y;
                if (x == 0 && y == 0) {
                    dup++;
                    continue;
                }
                int gcd = gcd(x, y); // gcd can never be zero since we check ahead
                List<Integer> slope = Arrays.asList(x / gcd, y / gcd);
                lineCnt.put(slope, lineCnt.getOrDefault(slope, 0) + 1);
                local = Math.max(local, lineCnt.get(slope));
            }
            max = Math.max(max, local + dup + 1); // error!
        }
        return max;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}
