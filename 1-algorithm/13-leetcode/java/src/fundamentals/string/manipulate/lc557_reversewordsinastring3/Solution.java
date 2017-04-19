package fundamentals.string.manipulate.lc557_reversewordsinastring3;

/**
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 */
public class Solution {

    public String reverseWords(String s) {
        int n = s.length();
        char[] ch = s.toCharArray();
        for (int i = 0, j = 0; j <= n; j++) {
            if (j == n || s.charAt(j) == ' ') {
                rev(ch, i, j - 1);
                i = j + 1;
            }
        }
        return String.valueOf(ch);
    }

    private void rev(char[] ch, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = ch[i];
            ch[i] = ch[j];
            ch[j] = tmp;
        }
    }

}
