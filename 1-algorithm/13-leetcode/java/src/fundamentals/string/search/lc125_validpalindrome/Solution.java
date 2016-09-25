package fundamentals.string.search.lc125_validpalindrome;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note: Have you consider that the string might be empty? This is a good question to ask during an interview. For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {

    // My 3AC. O(N) time.
    public boolean isPalindrome(String s) {
        char[] c = s.toCharArray();
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++; // error: isWhitespace is not applicable  eg.":,1"
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i++]) != Character.toLowerCase(c[j--]))
                return false;
        }
        return true;
    }

    // My 2nd: use helpful Character method
    public boolean isPalindrome21(String s) {
        char[] c = s.toCharArray();
        // Invariant: [0,i) and (j,N-1] are matched excluding whitespace
        for (int i = 0, j = c.length - 1; i < j; ) {
            if (!Character.isLetterOrDigit(c[i])) i++; // error: isWhitespace is not applicable  eg.":,1"
            else if (!Character.isLetterOrDigit(c[j])) j--;
            else if (Character.toLowerCase(c[i]) != Character.toLowerCase(c[j])) return false;
            else {
                i++;
                j--;
            }
        }
        return true;
    }

    // Regex solution: remove all non-alpha eg." ,.:", but too slow
    public boolean isPalindrome22(String s) {
        s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    // My 1st
    public boolean isPalindrome1(String s) {
        int i = 0;
        int j = s.length() - 1;
        do {
            while (i < j && !isAlphanumeric(s.charAt(i))) {
                i++;
            }

            while (i < j && !isAlphanumeric(s.charAt(j))) {
                j--;
            }

            if (i >= j) {
                return true;
            }

            if (!s.substring(i, i+1).equalsIgnoreCase(s.substring(j, j+1))) { //error1: ignore case
                return false;
            }

            i++;    // error2: forget causing dead loop
            j--;

        } while (true);
    }

    private static boolean isAlphanumeric(char c) {
        return ('a' <= c && c <= 'z') ||
                ('A' <= c && c <= 'Z') ||
                ('0' <= c && c <= '9');
    }

}
