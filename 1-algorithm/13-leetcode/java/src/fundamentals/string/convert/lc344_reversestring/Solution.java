package fundamentals.string.convert.lc344_reversestring;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * Example: Given s = "hello", return "olleh".
 */
public class Solution {

    public String reverseString(String s) {
        StringBuilder result = new StringBuilder(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }

}
