package fundamentals.string.convert.lc171_excelsheetcolumnnumber;

/**
 * Related to question Excel Sheet Column Title.
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 *  A -> 1
 *  B -> 2
 *  C -> 3
 *  ...
 *  Z -> 26
 *  AA -> 27
 *  AB -> 28
 */
public class Solution {

    public int titleToNumber(String s) {
        int num = 0;
        for (char c : s.toCharArray()) {
            num = num * 26 + (c - 'A') + 1;
        }
        return num;
    }

    // My 2AC: think about lowercase, whitespace, empty string or others...
    public int titleToNumber2(String s) {
        int num = 0;
        for (char c : s.toCharArray())
            num = num * 26 + (Character.toUpperCase(c) - 'A') + 1;
        return num;
    }

    // My 1AC
    public int titleToNumber1(String s) {
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + (s.charAt(i) - 'A' + 1);
        }
        return num;
    }

}
