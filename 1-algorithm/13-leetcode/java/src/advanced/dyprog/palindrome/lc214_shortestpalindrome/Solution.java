package advanced.dyprog.palindrome.lc214_shortestpalindrome;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example: Given "aacecaaa", return "aaacecaaa". Given "abcd", return "dcbabcd".
 */
public class Solution {

    // Timeout for "aaaaaa..."
    public String shortestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }

        // 1.Find longest palindrome starting at char 0
        int longest = 0;
        for (int i = 1; i <= s.length(); i++) {
            if (isPalindrome(s.substring(0, i))) {
                longest = i;
            }
        }

        // 2.Concat to form a palindrome
        String concat = new StringBuilder(s.substring(longest)).reverse().toString();
        return concat + s;
    }

    private boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

}
