package misc.math.convert.lc168_excelsheetcolumntitle;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 *  1 -> A
 *  2 -> B
 *  3 -> C
 *  ...
 *  26 -> Z
 *  27 -> AA
 *  28 -> AB
 */
public class Solution {

    public String convertToTitle(int n) {
        StringBuilder title = new StringBuilder();
        while (n > 0) {
            n--;                                        // error1: since it's using 1~26, need sub 1 in each round
            title.insert(0, (char) ('A' + (n % 26)));   // eg.27->[AA] (27-1)%26=0->A, 26/26-1=0->A
            n /= 26;
        }
        return title.toString();
    }

}
