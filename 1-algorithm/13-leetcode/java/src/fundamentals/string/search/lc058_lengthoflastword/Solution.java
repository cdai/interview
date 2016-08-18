package fundamentals.string.search.lc058_lengthoflastword;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string. If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example, Given s = "Hello World", return 5.
 */
public class Solution {

    // Use only i, a little cleaner perhaps
    public int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        for ( ; i >= 0 && s.charAt(i) == ' '; i--);

        int len = 0;
        for ( ; i >= 0 && s.charAt(i) != ' '; i--) {
            len++;
        }
        return len;
    }

    // My 2nd: obvious solution in O(N)
    public int lengthOfLastWord2(String s) {
        // 1.Skip tailing space
        int i = s.length() - 1;
        for ( ; i >= 0 && s.charAt(i) == ' '; i--);

        // 2.Find last word (if s="" or "  ", then both i and j = -1)
        int j = i;
        for ( ; j >= 0 && s.charAt(j) != ' '; j--);
        return i - j;
    }

    // My 1st: what was I thinking...
    public int lengthOfLastWord1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int w = -1;
        int ws = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                ws++;
            } else if (ws > 0 || w == -1) { // error2: new word if c!=' ' OR w == -1
                w = i;
                ws = 0;
            }
            // c != ' ' && ws == 0 means: in word
        }
        return (w == -1) ? 0 : (s.length()-w-ws); // error1: assume last word is the last, no following ws
    }

}
