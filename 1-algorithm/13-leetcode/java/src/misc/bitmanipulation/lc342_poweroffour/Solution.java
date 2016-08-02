package misc.bitmanipulation.lc342_poweroffour;

/**
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * Example: Given num = 16, return true. Given num = 5, return false.
 * Follow up: Could you solve it without loops/recursion?
 */
public class Solution {

    public boolean isPowerOfFour(int num) {
        // Use 0x5555555555555555l to check if 1 is on odd bit:
        // 010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101
        return (num > 0) && ((num & (num - 1)) == 0)
                && ((num & 0x5555555555555555l) == num);
    }

    public boolean isPowerOfFour2(int num) {
        // Determine if it's power of two at first
        if (num <= 0 || (num & (num - 1)) != 0) { // error: !=0 not ==1
            return false;
        }

        // Check if there're even zeroes: 4(100), 16(10000)...
        int i = 0;
        while (num > 1) {
            num >>= 1;
            i++;
        }
        return i % 2 == 0;
    }

}
