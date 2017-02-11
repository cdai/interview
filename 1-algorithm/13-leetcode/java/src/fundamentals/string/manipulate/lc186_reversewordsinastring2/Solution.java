package fundamentals.string.manipulate.lc186_reversewordsinastring2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 */
public class Solution {

    @Test
    void testNormal() {
        char[] ch1 = "hello".toCharArray();
        reverseWords(ch1);
        Assertions.assertEquals("hello", String.valueOf(ch1));

        char[] ch2 = "the sky is blue".toCharArray();
        reverseWords(ch2);
        Assertions.assertEquals("blue is sky the", String.valueOf(ch2));
    }

    public void reverseWords(char[] s) {
        int n = s.length;
        reverse(s, 0, n - 1);
        for (int i = 0, j = 0; j <= n; j++) {
            if (j == n || s[j] == ' ') {
                reverse(s, i, j - 1);
                i = j + 1;
            }
        }
    }

    private void reverse(char[] s, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }

}
