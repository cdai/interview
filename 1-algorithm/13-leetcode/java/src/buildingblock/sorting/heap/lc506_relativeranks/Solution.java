package buildingblock.sorting.heap.lc506_relativeranks;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores,
 * who will be awarded medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 */
public class Solution {

    public String[] findRelativeRanks(int[] nums) {
        // Sort pair of (index,num) by heap
        Queue<int[]> q = new PriorityQueue<>((p1, p2) -> Integer.compare(p2[1], p1[1]));
        for (int i = 0; i < nums.length; i++) {
            q.offer(new int[]{ i, nums[i] });
        }

        // Get descending sequence from heap
        String[] rank = new String[nums.length];
        String[] medal = { "", "Gold Medal", "Silver Medal", "Bronze Medal" };
        for (int i = 1; !q.isEmpty(); i++) {
            rank[q.poll()[0]] = (i <= 3) ? medal[i] : String.valueOf(i);
        }
        return rank;
    }

}
