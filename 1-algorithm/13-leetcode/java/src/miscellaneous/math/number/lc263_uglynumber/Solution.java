package miscellaneous.math.number.lc263_uglynumber;

/**
 * Write a program to check whether a given number is an ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 * Note that 1 is typically treated as an ugly number.
 */
public class Solution {

    public boolean isUgly(int num) {
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
