package hackerrank.sorting.counting;

import java.util.Scanner;

/**
 * Here is the real counting sort
 */
public class Solution2 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int total = Integer.parseInt(in.nextLine());
            int[] nums = new int[total];
            for (int i = 0; i < total; i++)
                nums[i] = in.nextInt();

            int[] sorted = countingSort(nums, 100);

            for (int num : sorted)
                System.out.print(num + " ");
            System.out.println();
        }
    }

    private static int[] countingSort(int[] nums, int max) {
        int[] cnt = new int[max];
        for (int num : nums)
            cnt[num]++;

        // Clear every counter. O(N) time totally.
        int[] ret = new int[nums.length];
        for (int i = 0, num = 0; num < max; num++)
            while (cnt[num]-- > 0)
                ret[i++] = num;
        return ret;
    }

    // O(N + K) time
    private static int[] countingSort2(int[] nums, int max) {
        // Count the number
        int[] cnt = new int[max];
        for (int num : nums)
            cnt[num]++;

        // Accumulate count to the position of number (#num that less than it)
        cnt[0]--; // Start from 0 not 1...
        for (int i = 1; i < max; i++)
            cnt[i] += cnt[i - 1];

        // Output result
        int[] ret = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--)
            ret[cnt[nums[i]]--] = nums[i];
        return ret;
    }

}
