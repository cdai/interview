package hackerrank.string.pangrams;

import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            System.out.println(isPangram(in.nextLine()) ? "pangram" : "not pangram"); // next() only return a word, use nextLine() for safe
        }
    }

    private static boolean isPangram(String str) {
        boolean[] appears = new boolean[26];
        for (char c : str.toCharArray())
            if (c != ' ')
                appears[Character.toLowerCase(c) - 'a'] = true;

        for (boolean appear : appears)
            if (!appear) return false;
        return true;
    }

}
