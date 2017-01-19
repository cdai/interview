package fundamentals.string.manipulate.lc151_reversewordsinastring;

import java.util.Arrays;
import java.util.Collections;

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
        System.out.println(new Solution().reverseWords("a"));
    }

    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+"); // must trim and split by s+, ow. " " exists after split!
        if (words.length == 0) return "";
        StringBuilder ret = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            ret.append(words[i]).append(" ");
        }
        return ret.append(words[0]).toString();
    }

    // Process in reversed order to reduce insert(0,str) cost
    // And use nested loop to get rid of flag variable
    public String reverseWords3(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') continue;
            int j = i;
            while (i >= 0 && s.charAt(i) != ' ') i--;
            result.append(s.substring(i + 1, j + 1)).append(" ");
        }
        return result.toString().trim();
    }

    // 3-line built-in lib version
    public String reverseWords_builtin(String s) {
        String[] words = s.trim().split("\\s+");    // must trim! otherwise, " a" -> ["", "a"]
        Collections.reverse(Arrays.asList(words));  // Arrays.asList just a wrapper
        return String.join(" ", words);
    }

    // My 2nd: not use built-in lib.
    public String reverseWords_naive(String s) {
        StringBuilder result = new StringBuilder();
        s += " ";
        boolean inWord = false;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (!inWord) {
                    j = i;
                    inWord = true;
                }
            } else {
                if (inWord) {
                    result.insert(0, s.substring(j, i) + " ");
                    inWord = false;
                }
            }
        }
        return result.length() == 0 ? "" : result.deleteCharAt(result.length() - 1).toString();
    }

    // My 1st
    public String reverseWords1(String s) {
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
