package fundamentals.string.manipulate.lc344_reversestring;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example: Given s = "hello", return "olleh".
 */
public class Solution {

    // My 2nd: use char array
    public String reverseString(String s) {
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
