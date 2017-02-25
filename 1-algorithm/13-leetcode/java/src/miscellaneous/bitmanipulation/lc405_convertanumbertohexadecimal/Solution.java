package miscellaneous.bitmanipulation.lc405_convertanumbertohexadecimal;

/**
 */
public class Solution {

    // MIN_VALUE:   10000000000000000000000000000000
    // >> 1:        11000000000000000000000000000000
    // >>>1:        01000000000000000000000000000000
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = "0123456789abcdef".toCharArray();
        StringBuilder hex = new StringBuilder();
        for (; num != 0; num >>>= 4) { // Handle 4 bits each iteration
            hex.insert(0, map[num & 0xf]);
        }
        return hex.toString();
    }

}
