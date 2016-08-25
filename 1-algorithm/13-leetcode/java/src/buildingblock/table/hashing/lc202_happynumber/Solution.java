package buildingblock.table.hashing.lc202_happynumber;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum of the squares of its digits,
 * and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1.
 * Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 *  1^2 + 9^2 = 82
 *  8^2 + 2^2 = 68
 *  6^2 + 8^2 = 100
 *  1^2 + 0^2 + 0^2 = 1
 */
public class Solution {

    // An interesting "find cycle in linked list" flavour solution
    public boolean isHappy(int n) {
        int slow = n, fast = n;
        // eg. 10, slow=1, fast=1, wrong!
        /*while (slow != 1) {
            slow = next(slow);
            fast = next(next(fast));
            if (slow == fast) {
                return false;
            }
        }*/
        do {
            slow = next(slow);
            fast = next(next(fast));
        } while (slow != fast);
        return slow == 1;
    }

    private int next(int n) {
        int sum = 0;
        while (n > 0) {
            int d = (n % 10);
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    // My 2nd
    public boolean isHappy2(int n) {
        if (n <= 0) {
            return false;
        }

        Set<Integer> nums = new HashSet<>();
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                int d = (n % 10);
                sum += d * d;
                n /= 10;
            }
            n = sum;

            // Infinite dead loop
            if (!nums.add(n)) {     // Simplied!
                return false;
            }
        }

        // n = 1
        return true;
    }

    // My 1st
    public boolean isHappy1(int n) {
        if (n <= 0) {
            return false;
        }

        Set<Integer> nums = new HashSet<>();
        while (n != 1) {
            if (nums.contains(n)) {
                return false;
            }
            nums.add(n);

            int next = 0;
            while (n > 0) {
                int tmp = n % 10;
                next += (tmp * tmp);
                n /= 10;
            }
            n = next;
        }
        return true;
    }

}
