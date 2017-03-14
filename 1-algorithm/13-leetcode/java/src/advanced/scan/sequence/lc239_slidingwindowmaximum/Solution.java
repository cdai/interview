package advanced.scan.sequence.lc239_slidingwindowmaximum;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(Arrays.toString(sol.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    // O(N) amortized. Only keep max and in-window number in the deque.
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k <= 0) return new int[0];
        Deque<Integer> q = new LinkedList<>();
        int[] max = new int[nums.length - k + 1];
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (!q.isEmpty() && i - q.peek() >= k) q.poll(); // remove elt out of win from front
            while (!q.isEmpty() && nums[q.peekLast()] < nums[i]) q.pollLast(); // remove smaller elt from back
            q.offer(i);
            if (i >= k - 1) max[j++] = nums[q.peek()];
        }
        return max;
    }

    private void print(int i, int[] nums, Deque<Integer> q) {
        System.out.printf("i=%d q=[ ", i);
        for (int j : q) {
            System.out.printf("%d ", nums[j]);
        }
        System.out.printf("]\n");
    }

}
