package hackerrank.algorithm.sorting.counting;

import java.util.Scanner;

/**
 * Just counting
 */
public class Solution1 {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int total = Integer.parseInt(in.nextLine());
            int[] nums = new int[total];
            for (int i = 0; i < total; i++)
                nums[i] = in.nextInt();

            int[] cnt = count(nums, 100);

            for (int c : cnt) // Arrays.toString() print with bracket and comma
                System.out.print(c + " ");
            System.out.println();
        }
    }

    private static int[] count(int[] nums, int max) { // max is exclusive
        int[] cnt = new int[max];
        for (int num : nums)
            cnt[num]++;
        return cnt;
    }

}
