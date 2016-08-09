package advanced.scan.index.lc373_findkpairswithsmallestsums;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * Example 1: Given nums1 = [1,7,11], nums2 = [2,4,6], k = 3. Return: [1,2],[1,4],[1,6].
 *  The first 3 pairs are returned from the sequence:[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2: Given nums1 = [1,1,2], nums2 = [1,2,3], k = 2. Return: [1,1],[1,1].
 *  The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * Example 3: Given nums1 = [1,2], nums2 = [3], k = 3. Return: [1,3],[2,3].
 *  All possible pairs are returned from the sequence: [1,3],[2,3]
 */
public class Solution {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        // Each num1 matched to where in nums2[]
        int[] pos = new int[len1];
        k = Math.min(k, len1 * len2);   // Nice! Avoid condition in while

        List<int[]> result = new ArrayList<>();
        while (k-- > 0) {
            int minIdx = 0;
            for (int i = 0, min = Integer.MAX_VALUE; i < len1; i++) {
                if (pos[i] < len2 && min > nums1[i] + nums2[pos[i]]) {
                    min = nums1[i] + nums2[pos[i]];
                    minIdx = i;
                }
            }
            result.add(new int[]{ nums1[minIdx], nums2[pos[minIdx]] });
            pos[minIdx]++;
        }
        return result;
    }

    // This is very slow, because we generate all pairs which is a problem if k is huge...
    public List<int[]> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        // 1.Create a max heap to keep smallest K elements
        Queue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] + b[1] - a[0] - a[1]);
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                heap.offer(new int[]{ nums1[i], nums2[j] });
                if (heap.size() > k) {
                    heap.poll();
                }
            }
        }

        // 2.Convert heap to list in reverse order
        List<int[]> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(0, heap.poll());
        }
        return result;
    }

    // Tend to pick pairs already picked
    public List<int[]> kSmallestPairs3(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        int i1 = 0, i2 = 0, j1 = 0, j2 = 0;
        while (k-- > 0 && i1 < nums1.length && i2 < nums2.length) {
            if (i1 == j1 && i2 == j2) {     // error1:
                result.add(new int[]{nums1[j1++], nums2[i2++]});
            } else {
                int pair1 = nums1[i1] + nums2[i2];
                int pair2 = nums1[j1] + nums2[j2];
                if (pair1 < pair2) {
                    result.add(new int[]{nums1[i1], nums2[i2++]});
                } else {
                    result.add(new int[]{nums1[j1++], nums2[j2]});
                }
            }
            if (i2 == nums2.length) {
                i1++;
                i2 = j2;                    // error2:
            }
            if (j1 == nums1.length) {
                j2++;
                j1 = i1;
            }
        }
        return result;
    }

}
