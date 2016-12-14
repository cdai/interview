package hackerrank.string.superreducedstring;

import java.util.Scanner;

/**
 * Reduce pair of adjacent letters with same value as much as possible.
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) { // Dirty (realworld) work: deal with input and output
//            String reduced = reduce(new StringBuilder(in.next())).toString();
            String reduced = reduce(in.next());
            System.out.println(reduced.isEmpty() ? "Empty String" : reduced);
        }
    }

    // O(N) time by using StringBuilder as Stack
    private static String reduce(String str) {
        StringBuilder s = new StringBuilder();
        for (char c : str.toCharArray())
            if (s.length() > 0 && s.charAt(s.length() - 1) == c)
                s.deleteCharAt(s.length() - 1);
            else
                s.append(c);
        return s.toString();
    }

    // O(N^2) time
    private static StringBuilder reduce2(StringBuilder str) {
        for (int i = 0; i < str.length() - 1; i++) /* safe to access i and i+1 */
            if (str.charAt(i) == str.charAt(i + 1))
                return reduce2(str.delete(i, i + 2));
        return str;
    }

}
