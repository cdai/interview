package fundamentals.string.search.lc058_lengthoflastword;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word in the string. If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example, Given s = "Hello World", return 5.
 */
public class Solution {

    public int lengthOfLastWord(String s) {
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
