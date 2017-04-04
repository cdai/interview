package miscellaneous.bitmanipulation.lc393_utf8validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an array of integers representing the data, return whether it is a valid utf-8 encoding.
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertTrue(validUtf8(new int[]{197, 130, 1}));
        Assertions.assertFalse(validUtf8(new int[]{240,162,138,147,145}));
    }

    public boolean validUtf8(int[] data) {
        int cnt = 0; // # of subsequent encoding if current is valid
        for (int d : data) {
            if (cnt == 0) {
                if ((d >> 5) == 0b110) cnt = 1;
                else if ((d >> 4) == 0b1110) cnt = 2;
                else if ((d >> 3) == 0b11110) cnt = 3;
                else if ((d >> 7) != 0) return false;
            } else {
                if ((d >> 6) != 0b10) return false;
                cnt--;
            }
        }
        return cnt == 0;
    }

    // Wrong for multiple UTF encodings in input array
    public boolean validUtf8_my(int[] data) {
        if (data.length == 0) return false;

        // 1.Check length of unicode (not necessary equal length of data array)
        int n = 0;
        for (int i = 7; i >= 4; i--) {
            if (((data[0] >> i) & 1) == 1) n++;
        }
        if (n == 0) return true;
        if (((data[0] >> (7 - n)) & 1) == 1) return false;

        // 2.Validate following bytes
        for (int i = 1; i < n; i++) {
            if ((data[i] >> 6) != 2) return false;
        }
        return true; // n==0
    }

}
