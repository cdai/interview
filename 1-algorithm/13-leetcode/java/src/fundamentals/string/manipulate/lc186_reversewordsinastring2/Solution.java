package fundamentals.string.manipulate.lc186_reversewordsinastring2;

/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 */
public class Solution {

    public static void main(String[] args) {
        char[] s = "the sky is blue".toCharArray();
        new Solution().reverseWords(s);
        System.out.println(String.valueOf(s));
    }

    public void reverseWords(char[] s) {
        int n = s.length;
        swap(s, 0, n - 1);
        for (int i = 0, j = 0; j <= n; j++) {
            if (j == n || s[j] == ' ') {
                swap(s, i, j - 1);
                i = j + 1;
            }
        }
    }

    private void swap(char[] s, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }

}
