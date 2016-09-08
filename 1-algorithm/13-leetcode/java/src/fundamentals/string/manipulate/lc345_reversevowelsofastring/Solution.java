package fundamentals.string.manipulate.lc345_reversevowelsofastring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1: Given s = "hello", return "holle".
 * Example 2: Given s = "leetcode", return "leotcede".
 * Note: The vowels does not include the letter "y".
 */
public class Solution {

    // My 2nd: O(N) time O(1) space
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>(
                Arrays.asList(new Character[] { 'a','e','i','o','u','A','E','I','O','U' }));
        char[] chars = s.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; ) {
            if (!vowels.contains(chars[i])) {
                i++;
            } else if (!vowels.contains(chars[j])) {
                j--;
            } else {
                char tmp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = tmp;
            }
        }
        return new String(chars);
    }

    // Linear search is slow, but why Set is slower...
    private boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    // My 1st: could be better
    public String reverseVowels1(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        char[] chars = s.toCharArray();
        for (int i = 0, j = s.length() - 1; i < j; ) {
            if (vowels.contains(chars[i]) && vowels.contains(chars[j])) {
                swap(chars, i, j);
                i++;
                j--;
            } else if (vowels.contains(chars[i])) {
                j--;
            } else if (vowels.contains(chars[j])) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

}
