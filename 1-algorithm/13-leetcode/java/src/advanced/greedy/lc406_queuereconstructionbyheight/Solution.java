package advanced.greedy.lc406_queuereconstructionbyheight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k),
 * where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 */
public class Solution {

    // Pick out tallest group of people and sort them in a subarray (S).
    // Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
    // For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (p1, p2) -> (p1[0] != p2[0] ?
                Integer.compare(p2[0], p1[0]) : Integer.compare(p1[1], p2[1])));
        List<int[]> ret = new ArrayList<>();
        for (int[] p : people) {
            ret.add(p[1], p);
        }
        return ret.toArray(new int[ret.size()][]);
    }

    public int[][] reconstructQueue_my(int[][] people) {
        Arrays.sort(people, (p1, p2) -> (p1[0] != p2[0] ?
                Integer.compare(p2[0], p1[0]) : Integer.compare(p1[1], p2[1])));
        int n = people.length;
        int[][] q = new int[n][2];
        for (int i = 0; i < n; i++) {
            // Find insert position
            int j = 0;
            for (int k = people[i][1]; k > 0 && j < i; j++) {
                if (q[k][0] >= people[i][0]) k--;
            }
            // Move data after it
            for (int k = i; k > j; k--) {
                q[k] = q[k - 1];
            }
            q[j] = people[i];
        }
        return q;
    }

}
