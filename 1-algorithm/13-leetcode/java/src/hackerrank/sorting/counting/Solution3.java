package hackerrank.sorting.counting;

import java.util.Scanner;

/**
 * Count the #num less or equal than current num
 */
public class Solution3 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int total = Integer.parseInt(in.nextLine());
            int[] nums = new int[total];
            for (int i = 0; i < total; i++, in.nextLine()) // skip remaining string
                nums[i] = in.nextInt();

            int[] sorted = count(nums, 100);

            for (int num : sorted)
                System.out.print(num + " ");
            System.out.println();
        }
    }

    private static int[] count(int[] nums, int max) {
        // Count the number
        int[] cnt = new int[max];
        for (int num : nums)
            cnt[num]++;

        // Accumulate count to the position of number
        // = the number of elements that are less than or equal to it
        for (int i = 1; i < max; i++)
            cnt[i] += cnt[i - 1];
        return cnt;
    }

}
