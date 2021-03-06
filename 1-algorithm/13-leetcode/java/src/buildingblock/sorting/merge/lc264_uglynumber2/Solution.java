package buildingblock.sorting.merge.lc264_uglynumber2;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program to find the n-th ugly number. Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * Note that 1 is typically treated as an ugly number.
 * Hint: The naive approach is to call isUgly for every number until you reach the nth one.
 * Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
 * An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
 * The key is how to maintain the order of the ugly numbers.
 * Try a similar approach of merging from three sorted lists: L1, L2, and L3.
 * Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().nthUglyNumber(41));
        System.out.println(new Solution().nthUglyNumber3(41));
    }

    // We only know, at the beginning, the first one, which is 1. Then k[1] = min( k[0]x2, k[0]x3, k[0]x5).
    // The answer is k[0]x2. So we move 2's pointer to 1. Then we test: k[2] = min( k[1]x2, k[0]x3, k[0]x5).
    // And so on. Be careful about the cases such as 6, in which we need to forward both pointers of 2 and 3.
    public int nthUglyNumber(int n) {
        int[] nums = new int[n + 1];
        nums[1] = 1;
        int i2 = 0, i3 = 0, i5 = 0;
        int u2 = nums[1], u3 = nums[1], u5 = nums[1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Math.min(u2, Math.min(u3, u5));
            if (nums[i] == u2) u2 = nums[++i2] * 2;
            if (nums[i] == u3) u3 = nums[++i3] * 3;
            if (nums[i] == u5) u5 = nums[++i5] * 5;
        }
        return nums[n];
    }

    public int nthUglyNumber4(int n) {
        if (n <= 0) return 0;
        int[] ugly = new int[n + 1];
        ugly[1] = 1;
        for (int i = 2, i2 = 1, i3 = 1, i5 = 1; i <= n; i++) {
            ugly[i] = Math.min(ugly[i2] * 2, Math.min(ugly[i3] * 3, ugly[i5] * 5));
            if (ugly[i] == ugly[i2] * 2) i2++; // Move all to avoid duplication
            if (ugly[i] == ugly[i3] * 3) i3++;
            if (ugly[i] == ugly[i5] * 5) i5++;
        }
        return ugly[n];
    }

    // Inspired by leetcode discuss. Reduce unnecessary calculation.
    public int nthUglyNumber3(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;

        //Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
        int i2 = 0, i3 = 0, i5 = 0;
        int u2 = 2, u3 = 3, u5 = 5;
        for (int i = 1; i < n; i++) {
            int min = Math.min(u2, Math.min(u3, u5));
            ugly[i] = min;
            System.out.println((i + 1) + " - " + min);

            if (min == u2) {
                u2 = 2 * ugly[++i2];
            }
            if (min == u3) {
                u3 = 3 * ugly[++i3];
            }
            if (min == u5) {
                u5 = 5 * ugly[++i5];
            }
        }
        return ugly[n - 1];
    }

    // My 2nd: use array to boost, but could be more concise
    public int nthUglyNumber2(int n) {
        int[] nums = new int[n];
        nums[0] = 1;

        //Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
        int i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < n; i++) {
            int u2 = nums[i2] * 2;
            int u3 = nums[i3] * 3;
            int u5 = nums[i5] * 5;
            int min = Math.min(u2, Math.min(u3, u5));
            nums[i] = min;

            if (min == u2) { // error: must be independent "if", since u2=u3 or u3=u5
                i2++;
            }
            if (min == u3) {
                i3++;
            }
            if (min == u5) {
                i5++;
            }
        }
        return nums[n - 1];
    }

    // My 1st: ArrayList is too heavy
    public int nthUglyNumber1(int n) {
        List<Integer> uglyNums = new ArrayList<>();
        uglyNums.add(1);

        int i = 0, j = 0, k = 0;
        while (uglyNums.size() < n) {
            int n2 = uglyNums.get(i) * 2;
            int n3 = uglyNums.get(j) * 3;
            int n5 = uglyNums.get(k) * 5;
            int min = Math.min(n2, Math.min(n3, n5));
            uglyNums.add(min);

            if (n2 == min) {    // if not if-else to handle same ugly num from different source
                i++;
            }
            if (n3 == min) {
                j++;
            }
            if (n5 == min) {
                k++;
            }
        }
        return uglyNums.get(n - 1);
    }

}
