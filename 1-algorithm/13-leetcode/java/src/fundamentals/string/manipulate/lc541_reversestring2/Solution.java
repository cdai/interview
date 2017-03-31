package fundamentals.string.manipulate.lc541_reversestring2;

/**
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 */
public class Solution {

    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i += 2 * k) {
            rev(ch, i, i + k);
        }
        return String.valueOf(ch);
    }

    private void rev(char[] ch, int i, int j) {
        j = Math.min(ch.length, j) - 1;
        for (; i < j; i++, j--) {
            char tmp = ch[i];
            ch[i] = ch[j];
            ch[j] = tmp;
        }
    }

}
