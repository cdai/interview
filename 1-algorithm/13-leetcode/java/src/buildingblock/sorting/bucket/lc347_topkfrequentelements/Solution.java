package buildingblock.sorting.bucket.lc347_topkfrequentelements;

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

    // Essence of the problem is how to sort by freq less than O(NlogN) time
    // 1) Maintain a K-size-heap since we only only largest K element
    // 2) Sort by bucket since freq is in [1,nums.length]
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            if (bucket[e.getValue()] == null) {
                bucket[e.getValue()] = new ArrayList<>();
            }
            bucket[e.getValue()].add(e.getKey());
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = nums.length; i > 0 && ret.size() < k; i--) {
            if (bucket[i] != null) {
                ret.addAll(bucket[i]);
            }
        }
        return ret.subList(0, k);
    }

    // O(NlogK) time Heap solution.
    public List<Integer> topKFrequent3(int[] nums, int k) {
        Map<Integer,Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        Queue<Map.Entry<Integer,Integer>> q = new PriorityQueue<>(Map.Entry.comparingByValue());
        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            q.offer(e);
            if (q.size() > k) {
                q.poll();
            }
        }

        List<Integer> ret = new ArrayList<>();
        while (!q.isEmpty()) {
            ret.add(q.poll().getKey());
        }
        Collections.reverse(ret);
        return ret;
    }

    // Inspired by leetcode solution. O(N)
    public List<Integer> topKFrequent2(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) {
            return new ArrayList<>();
        }

        // 1.Stat num frequency
        Map<Integer,Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2.Put into bucket according to frequency
        List<Integer>[] bucket = new List[nums.length + 1];
        for (Map.Entry<Integer,Integer> e : freq.entrySet()) {
            if (bucket[e.getValue()] == null) {
                bucket[e.getValue()] = new ArrayList<>();
            }
            bucket[e.getValue()].add(e.getKey());
        }

        // 3.Get top frequency
        List<Integer> result = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && result.size() < k; i--) { // Nice with subList(0,k)!
            if (bucket[i] != null) {
                result.addAll(bucket[i]);
            }
        }
        return result.subList(0, k);
    }

    // My 1st: use Heap in O(NlogK).
    public List<Integer> topKFrequent1(int[] nums, int k) {
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
