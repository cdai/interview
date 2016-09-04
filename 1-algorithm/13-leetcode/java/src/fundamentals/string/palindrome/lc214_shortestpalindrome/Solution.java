package fundamentals.string.palindrome.lc214_shortestpalindrome;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example: Given "aacecaaa", return "aaacecaaa". Given "abcd", return "dcbabcd".
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().shortestPalindrome("aacecaaa"));
    }

    // What the hell...
    public String shortestPalindrome3(String s) {
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
    }

    // A little better using extend approach. Still O(N^2).
    public String shortestPalindrome(String s) {
        int i = s.length() - 1;
        for (; i > 0 && !isPalindrome(s, i); i--);
        return new StringBuilder(s.substring(i + 1)).reverse().append(s).toString();
    }

    private boolean isPalindrome(String s, int end) {
        int mid1 = end / 2, mid2 = (end % 2 == 0) ? mid1 : mid1 + 1;
        for (int i = mid1, j = mid2; i >= 0 && j <= end; i--, j++)
            if (s.charAt(i) != s.charAt(j))
                return false;
        return true;
    }

    // O(N^2)
    public String shortestPalindrome_bruteforce(String s) {
        // Find center with longest palindrome
        int i = s.length() - 1;
        for (; i > 0 && !isPalindrome(s, i); i--);
        return new StringBuilder(s.substring(i + 1)).reverse().append(s).toString();
    }

    private boolean isPalindrome2(String s, int i) {
        for (int j = 0; j < i; j++, i--)
            if (s.charAt(j) != s.charAt(i))
                return false;
        return true;
    }

    // Timeout for "aaaaaa..."
    public String shortestPalindrome1(String s) {
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
