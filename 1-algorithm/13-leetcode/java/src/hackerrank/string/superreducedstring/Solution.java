package hackerrank.string.superreducedstring;

import java.util.Scanner;

/**
 * Reduce pair of adjacent letters with same value as much as possible.
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) { // Dirty (realworld) work: deal with input and output
            String reduced = reduce(new StringBuilder(in.next())).toString();
            System.out.println(reduced.isEmpty() ? "Empty String" : reduced);
        }
    }

    private static StringBuilder reduce(StringBuilder str) {
        for (int i = 0; i < str.length() - 1; i++) /* safe to access i and i+1 */
            if (str.charAt(i) == str.charAt(i + 1))
                return reduce(str.delete(i, i + 2));
        return str;
    }

}
