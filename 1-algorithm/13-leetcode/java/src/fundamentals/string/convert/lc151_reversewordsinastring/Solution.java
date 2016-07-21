package fundamentals.string.convert.lc151_reversewordsinastring;

/**
 * Given an input string, reverse the string word by word.
 * For example, given s = "the sky is blue", return "blue is sky the".
 * Clarification:
 *  1.What constitutes a word? A sequence of non-space characters constitutes a word.
 *  2.Could the input string contain leading or trailing spaces? Yes. However, your reversed string should not contain leading or trailing spaces.
 *  3.How about multiple spaces between two words? Reduce them to a single space in the reversed string.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().reverseWords("   hello  world abc"));
    }

    public String reverseWords(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            // 1.Skip tailing space
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            // 2.i is a letter now, bypass other letters of this word using j
            int j = i;
            while (j >= 0 && s.charAt(j) != ' ') {
                j--;
            }
            // 3.Now we got a word [j+1,i]
            if (i >= 0) {
                result.append(s.substring(j + 1, i + 1)).append(" ");
            }
            i = j;
        }
        if (result.length() == 0) {
            return "";
        }
        return result.deleteCharAt(result.length() - 1).toString();
    }

    // Recursive version is direct, but TLE... :(
    public String reverseWords2(String s) {
        if (s.isEmpty()) { // i=j=len
            return "";
        }

        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        int j = i;
        while (j < s.length() && s.charAt(j) != ' ') {
            j++;
        }

        // This is not pure recursion, since last word should not lead by space.
        // That means it's different from other sub-problem as follows.
        String reversed = reverseWords2(s.substring(j));
        if (reversed.isEmpty()) {
            return s.substring(i, j);
        }
        return reversed + " " + s.substring(i, j);
    }

}
