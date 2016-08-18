package fundamentals.string.convert.lc006_zigzagconversion;

import java.util.Arrays;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
 * (you may want to display this pattern in a fixed font for better legibility)
 *  P   A   H   N
 *  A P L S I I G
 *  Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR".
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class Solution {

    // My 2nd: how can I come up with this...
    // My 1st
    public String convert(String s, int numRows) {
        if (s.isEmpty() || numRows < 2) { // error1: div-by-zero
            return s;
        }

        String[] result = new String[numRows];
        Arrays.fill(result, "");

        // Move numRows-1 times, then turn around
        int row = 0;
        for (int i = 0; i < s.length(); i++) {
            result[row] += s.charAt(i);
            row = (i / (numRows - 1) % 2 == 0) ? row + 1 : row - 1;
        }
        return String.join("", result); // Java 8
    }

}
