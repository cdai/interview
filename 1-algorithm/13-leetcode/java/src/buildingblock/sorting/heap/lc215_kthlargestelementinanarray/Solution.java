package buildingblock.sorting.heap.lc215_kthlargestelementinanarray;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findKthLargest(new int[]{1, 5, 2, 3, 10, 7, 11}, 2));
    }

    // Quick select solution from leetcode discuss.
    // O(N) average, O(N^2) worst. O(1) space.
    public int findKthLargest(int[] nums, int k) {
        k = nums.length - k;                // error1: Kth largest

        // (N-K)th smallest MustBe(low,high)
        int low = 0, high = nums.length - 1;
        while (low < high) {
            // Partition invariant: T=A[lo], [lo,mid]<T, [mid,i)>=T, [i,hi] unknown
            int mid = low;
            for (int i = low + 1; i <= high; i++) {
                if (nums[i] < nums[low]) {
                    swap(nums, i, ++mid);
                }
            }
            swap(nums, low, mid);

            if (k < mid) {                  // error2: compare with mid not mid-low+1
                high = mid - 1;
            } else if (k > mid) {
                low = mid + 1;
            } else {
                return nums[mid];
            }
        }
        return nums[low];
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // O(NlogN) based on quick sort. O(1) space, faster than heap.
    public int findKthLargest_sort(int[] nums, int k) {
        if (k <= 0 || nums.length < k) {
            return -1;
        }
        Arrays.sort(nums);
        return nums[nums.length - k];   // Nice!
    }

    // My 2nd: O(NlogK) time, O(K) space
    public int findKthLargest_heap(int[] nums, int k) {
        Queue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            heap.offer(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        return (heap.size() < k || heap.isEmpty()) ? -1 : heap.poll();
    }

    // My 1st
    public int findKthLargest1(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return -1;
        }

        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int n : nums) {
            minHeap.offer(n);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

}
