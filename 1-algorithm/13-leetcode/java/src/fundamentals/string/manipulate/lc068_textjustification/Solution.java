package fundamentals.string.manipulate.lc068_textjustification;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and
 * is fully (left and right) justified. You should pack your words in a greedy approach; that is,
 * pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 * For example, words: ["This", "is", "an", "example", "of", "text", "justification."]. L: 16.
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 */
public class Solution {

    // "it teaches you in the real world. Programmers are always been ask to deal with dirty works."

    public static void main(String[] args) {
        System.out.println(new Solution().fullJustify(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        }, 16));
    }

    // My 2AC inspired by leetcode solution
    // Edge case: only 1 word, last line, maxWidth=0
    //   0   1  2    3
    // "This is an example..."
    //  i=0, j=3, width=8, space=(16-8)/(3-0-1)=4, extra=0
    // ------------------------------------------------------
    //   3      4   5        6
    // "example of text justification."
    //  i=3, j=6, width=13, space=(16-13)/(3-0-1)=1, extra=1
    // ------------------------------------------------------
    // "justification."
    //  i=6, j=7, space=1, extra=0
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0, j; i < words.length; ) {
            int width = 0;                                  // width of words without space
            for (j = i; j < words.length && width + words[j].length() + (j - i) <= maxWidth; j++)
                width += words[j].length();                 /* j is the next word */

            int space = 1, extra = 0;                       // for last line, space=1
            if (j - i != 1 && j != words.length) {          // not 1 word (div-by-zero) and not last line
                space = (maxWidth - width) / (j - i - 1);
                extra = (maxWidth - width) % (j - i - 1);   // minus 1 to exclude skip last word
            }

            StringBuilder line = new StringBuilder(words[i]);
            for (i = i + 1; i < j; i++) {                   // move i to j finally
                for (int s = space; s > 0; s--) line.append(" ");
                if (extra-- > 0) line.append(" ");
                line.append(words[i]);
            }
            for (int s = maxWidth - line.length(); s > 0; s--) line.append(" ");
            result.add(line.toString());
        }
        return result;
    }

    // Edge case: only 1 word, last line
    public List<String> fullJustify_widthwithspace(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        for (int i = 0, w; i < words.length; i = w) {       // Nice! -1 is very clever!
            int width = -1;
            for (w = i; w < words.length && width + words[w].length() + 1 <= maxWidth; w++)
                width += words[w].length() + 1;             /* w is next word causing termination */

            int space = 1, extra = 0;                       // for last line, space=1
            if (w - i != 1 && w != words.length) {          // not 1 word (div-by-zero) and not last line (space=1)
                space = (maxWidth - width) / (w - i - 1) + 1;
                extra = (maxWidth - width) % (w - i - 1);   // minus 1 to exclude skip last word
            }

            StringBuilder line = new StringBuilder(words[i]);
            for (int j = i + 1; j < w; j++) {
                for (int s = space; s > 0; s--) line.append(" ");
                if (extra-- > 0) line.append(" ");
                line.append(words[j]);
            }
            for (int j = maxWidth - line.length(); j > 0; j--) line.append(" ");
            result.add(line.toString());
        }
        return result;
    }

    // My ugly 1st attempt
    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>(), line = new ArrayList<>();
        for (int i = 0, width = 0, wlen = 0; i < words.length; i++) {
            if (width == 0) {
                line.add(words[i]);
                width = wlen = words[i].length();
            } else {
                if (width + words[i].length() + 1 <= maxWidth) {
                    line.add(words[i]);
                    wlen += words[i].length();
                    width += words[i].length() + 1;
                } else {
                    for (int j = 0, k = 0; k < maxWidth - wlen; j = (j + 1) % (line.size() - 1), k++)
                        line.set(j, line.get(j) + " ");
                    result.add(String.join("", line));
                    line.clear();
                    width = 0;
                    i--;
                }
            }
        }
        if (!line.isEmpty()) {
            String lastline = String.join(" ", line);
            for (int i = lastline.length(); i < maxWidth; i++)
                lastline += " ";
            result.add(lastline);
        }
        return result;
    }

}
