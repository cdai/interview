package misc.math.number.lc008_palindromenumber;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class Solution {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }

        int d = 1;
        while (x / d >= 10) {
            d = d * 10;
        }

        while (x > 0) {
            int h = x / d;
            int l = x % 10;
            if (h != l) {
                return false;
            }
            x = (x % d) / 10;
            d = d / 100;     // error: x miss high and low digit, so d /= 100 rather than 10
        }
        return true;
    }

    public boolean isPalindrome2(int x) {
        if (x < 0) {
            return false;
        }
        if (x < 10) { // error2
            return true;
        }

        int y = 0, diff = -1; // error1: diff should init as -1 nor 0
        while (x > 0 && !(x == y || (0 <= diff && diff < 10))) {
            y = y * 10 + x % 10;
            x = x / 10;
            diff = x - y * 10;
        }
        return (x != 0);
    }

}
