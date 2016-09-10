package advanced.scan.sequence.lc354_russiandollenvelopes;

import java.util.Arrays;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example: Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class Solution {

    public static void main(String[] args) {
        //System.out.println(new Solution().maxEnvelopes(new int[][]{{2, 3}, {6, 4}, {5, 4}, {6, 5}}));
        System.out.println(new Solution().maxEnvelopes(new int[][]{{4,5}, {4,6}, {6,7}, {2,3}, {1,1}, {1,1}}));
    }

    // Ascend on width and descend on height if width are same.
    // [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting
    // otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (e1, e2) -> (e1[1] < e2[1]) ? -1         // 1 means e1 > e2, -1 means e1 < e2
                : (e1[1] > e2[1] ? 1 : Integer.compare(e2[0], e1[0]))); // key!
        int[][] dp = new int[envelopes.length][2];
        int max = 0;
        for (int[] envelope : envelopes) {
            //if (i > 0 && envelopes[i][1] == envelopes[i - 1][1]) continue;
            int i = Arrays.binarySearch(dp, 0, max, envelope,
                    (e1, e2) -> Integer.compare(e1[0], e2[0]));
            if (i < 0) i = -(i + 1);
            dp[i] = envelope;
            if (i == max) max++;
        }
        return max;
    }

}
