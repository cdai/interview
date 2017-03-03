package fundamentals.string.manipulate.lc344_reversestring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example: Given s = "hello", return "olleh".
 */
public class Solution {

    @Test
    void test() {
        Assertions.assertEquals("olleh", reverseString("hello"));
    }

    // O(NlogN) time: rev(left+right) = rev(right) + rev(left)
    public String reverseString(String s) {
        if (s.length() <= 1) return s; // note len=1 cause dead loop: rev(substr(0)) + rev(substr(0,0))
        int m = s.length() / 2;
        return reverseString(s.substring(m)) + reverseString(s.substring(0, m)); // string concatenation takes linear time
    }

    public String reverseString_jdk(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    // My 2nd: use char array
    public String reverseString2(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }

    // My 1st
    public String reverseString1(String s) {
        StringBuilder result = new StringBuilder(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

}
