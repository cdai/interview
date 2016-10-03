package advanced.datastructure.geometry.lc218_theskylineproblem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().getSkyline(new int[][]{{0, 2, 3}, {2, 5, 3}}));
    }

    private static final int L = 0;
    private static final int R = 1;
    private static final int H = 2;

    private static final int X = 0;
    private static final int Y = 1;

    // My 3AC. O(NlogN) time. Use TreeMap to replace heap.
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> lines = new ArrayList<>();
        for (int[] bldg : buildings) {
            lines.add(new int[]{ bldg[L], -bldg[H] });
            lines.add(new int[]{ bldg[R], bldg[H] });
        }
        lines.sort((a, b) -> (a[X] != b[X])
                ? Integer.compare(a[X], b[X]) : Integer.compare(a[Y], b[Y]));

        List<int[]> ret = new ArrayList<>();
        TreeMap<Integer,Integer> h = new TreeMap<>(Collections.reverseOrder());
        h.put(0, 1); // force output of last endpoint finally
        int prev = 0;
        for (int[] l : lines) {
            if (l[Y] < 0) h.put(-l[Y], h.getOrDefault(-l[Y], 0) + 1);
            else {
                if (h.get(l[Y]) == 1) h.remove(l[Y]);
                else h.put(l[Y], h.get(l[Y]) - 1);
            }
            if (prev != h.firstKey()) {
                ret.add(new int[]{ l[X], h.firstKey() });
                prev = h.firstKey();
            }
        }
        return ret;
    }

    // O(N^2) time
    // Excellent explanation: https://briangordon.github.io/2014/08/the-skyline-problem.html
    // "Scan across the critical points from left to right:
    //   1) When we encounter the left edge of a rectangle, we add that rectangle to the heap with its height as the key.
    //   2) When we encounter the right edge of a rectangle, we remove that rectangle from the heap.
    // Any time we encounter a critical point, after updating the heap,
    //   we set the height of that critical point to the value peeked from the top of the heap."
    public List<int[]> getSkyline2(int[][] buildings) {
        Set<int[]> heights = new TreeSet<>((a, b) ->
                a[L] != b[L] ? Integer.compare(a[L], b[L]) : Integer.compare(a[1], b[1]));
        for (int[] bldg : buildings) {
            heights.add(new int[]{ bldg[L], -bldg[H] }); // Trick: use sign to distinguish left or right
            heights.add(new int[]{ bldg[R], bldg[H] });  // make left negative to guarantee left appear first
        }                                                // eg.[[0,2,3],[2,5,3]] -> [0,-3],[2,-3],[2,3],[5,3]

        Queue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        q.offer(0);
        List<int[]> skyline = new ArrayList<>();
        int prev = 0;
        for (int[] bldg : heights) {
            if (bldg[1] < 0) q.offer(-bldg[1]);
            else q.remove(bldg[1]); // slow!

            if (prev != q.peek()) {
                skyline.add(new int[]{ bldg[L], q.peek() });
                prev = q.peek();
            }
        }
        return skyline;
    }

}
