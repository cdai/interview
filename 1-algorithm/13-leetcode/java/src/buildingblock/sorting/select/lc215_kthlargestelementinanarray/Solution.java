package buildingblock.sorting.select.lc215_kthlargestelementinanarray;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Find the kth largest element in an unsorted array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * For example, given [3,2,1,5,6,4] and k = 2, return 5.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().findKthLargest(new int[]{1, 5, 2, 3, 10, 7, 11}, 2));
    }

    // 4AC. Note that we only shrink the range between l and r but never change k.
    // My 3AC. Simplify variable names.
    public int findKthLargest(int[] A, int k) {
        k = A.length - k; // convert to index of k largest
        int l = 0, r = A.length - 1;
        while (l <= r) {
            int i = l; // partition [l,r] by A[l]: [l,i]<A[l], [i+1,j)>=A[l]
            for (int j = l + 1; j <= r; j++)
                if (A[j] < A[l]) swap(A, j, ++i);
            swap(A, l, i);

            if (k < i) r = i - 1;
            else if (k > i) l = i + 1;
            else return A[i];
        }
        return -1;
    }

    // Quick select solution from leetcode discuss.
    // O(N) average, O(N^2) worst. O(1) space.
    // Shuffle boost performance from 24% to 74%!!!
    public int findKthLargest_shuffle(int[] nums, int k) {
        k = nums.length - k;                // error1: Kth largest
        shuffle(nums);

        // (N-K)th smallest MustBe(low,high)
        int low = 0, high = nums.length - 1;
        while (low < high) {
            // Partition invariant: T=A[lo], [lo,mid]<T, [mid,i)>=T, [i,hi] unknown
            int mid = low;
            for (int i = low + 1; i <= high; i++)
                if (nums[i] < nums[low])
                    swap(nums, i, ++mid);
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

    private void shuffle(int[] nums) {
        Random rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            swap(nums, i, rand.nextInt(nums.length - i));
        }
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    // O(NlogN) based on quick sort. O(1) space, faster than heap.
    public int findKthLargest_sort(int[] nums, int k) {
        if (k <= 0 || nums.length < k) return -1;
        Arrays.sort(nums);
        return nums[nums.length - k];   // Nice!
    }

    // My 2nd: O(NlogK) time, O(K) space
    public int findKthLargest_heap(int[] nums, int k) {
        if (nums.length < k) return 0;
        Queue<Integer> q = new PriorityQueue<>();
        for (int num : nums) {
            q.offer(num);
            if (q.size() > k) {
                q.poll();
            }
        }
        return q.poll();
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
