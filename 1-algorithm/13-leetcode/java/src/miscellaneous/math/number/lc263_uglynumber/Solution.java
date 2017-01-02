package miscellaneous.math.number.lc263_uglynumber;

/**
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * Note that 1 is typically treated as an ugly number.
 */
public class Solution {

    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }

    // My 2nd: extract helper method. O(N) time?
    public boolean isUgly2(int num) {
        if (num <= 0) { // 0 will cause TLE...
            return false;
        }
        num = divide(num, 2);
        num = divide(num, 3);
        num = divide(num, 5);
        return num == 1;
    }

    private int divide(int num, int div) {
        while (num % div == 0) {
            num /= div;
        }
        return num;
    }

    // My 1st
    public boolean isUgly1(int num) {
        if (num <= 0) {
            return false;
        }
        num = divUntilUnable(num, 2);
        num = divUntilUnable(num, 3);
        num = divUntilUnable(num, 5);
        return num == 1;
    }

    private int divUntilUnable(int num, int d) {
        while (num % d == 0) {
            num /= d;
        }
        return num;
    }

}
