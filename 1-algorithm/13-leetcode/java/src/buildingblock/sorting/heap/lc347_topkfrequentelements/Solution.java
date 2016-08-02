package buildingblock.sorting.heap.lc347_topkfrequentelements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example, given [1,1,1,2,2,3] and k = 2, return [1,2].
 * Note: You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class Solution {

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums.length == 0 || k > nums.length) {
            return null;
        }

        // 1.Build num frequency table: O(N)
        Map<Integer,Integer> numFreq = new HashMap<>();
        for (int n : nums) {
            Integer freq = numFreq.get(n);
            if (freq == null) {
                freq = 0;
            }
            numFreq.put(n, freq + 1);
        }

        // 2.Sort by heap: O(NlogK)
        Queue<Map.Entry<Integer,Integer>> heap = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer,Integer> e : numFreq.entrySet()) {
            heap.offer(e);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        // 3.Convert to final result
        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll().getKey());
        }
        Collections.reverse(result);
        return result;
    }

}
