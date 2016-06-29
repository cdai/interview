package fundamentals.string.search.lc125_validpalindrome;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 * Note: Have you consider that the string might be empty? This is a good question to ask during an interview. For the purpose of this problem, we define empty string as valid palindrome.
 */
public class Solution {

    public boolean isPalindrome(String s) {
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
