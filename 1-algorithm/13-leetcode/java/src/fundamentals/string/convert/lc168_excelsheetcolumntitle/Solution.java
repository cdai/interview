package fundamentals.string.convert.lc168_excelsheetcolumntitle;

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

    // Very nice recursive solution!
    // It's suprisingly fast using recusion in this kind of small problem
    public String convertToTitle(int n) {
        if (n-- <= 0) {
            return "";
        }
        return convertToTitle(n / 26) + (char) ('A' + n % 26);
    }

    // My 2AC: negative, zero...
    public String convertToTitle2(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        while (n-- > 0) {
            result.insert(0, (char) ('A' + (n % 26)));
            n /= 26;
        }
        return result.toString();
    }

    // My 1AC
    public String convertToTitle1(int n) {
        StringBuilder title = new StringBuilder();
        while (n > 0) {
            n--;                                        // error1: since it's using 1~26, need sub 1 in each round
            title.insert(0, (char) ('A' + (n % 26)));   // eg.27->[AA] (27-1)%26=0->A, 26/26-1=0->A
            n /= 26;
        }
        return title.toString();
    }

}
