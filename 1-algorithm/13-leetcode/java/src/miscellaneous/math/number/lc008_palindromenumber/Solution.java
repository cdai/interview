package miscellaneous.math.number.lc008_palindromenumber;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isPalindrome(12321));
    }

    // Amazing solution from cbmbbz
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) { // Note 10..00 will cause half=0, but 0 is palindrome
            return false;
        }
        // Reverse half of digits, no need to worry about overflow
        int half = 0;
        while (half < x) {
            half = half * 10 + x % 10;
            x /= 10;
        } /* half >= x */
        return half == x || half / 10 == x;
    }

    // My 2nd: Wrong! It's very hard to get each digit for decimal not binary
    public boolean isPalindrome_wrong(int x) {
        if (x < 0) {
            return false;
        }

        int high = 1;
        while (high * 10 <= x) {
            high *= 10;
        }
        /* high * 10 > x */

        for (int low = 10; low < high; low *= 10, high /= 10) {
            //if (((x >> (low - 1)) & 1) != ((x >> (high - 1)) & 1)) { // error: it's binary
            if ((x / high) != (x % low)) {
                return false;
            }
        }
        return true;
    }

    // My 1AC
    public boolean isPalindrome1(int x) {
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
