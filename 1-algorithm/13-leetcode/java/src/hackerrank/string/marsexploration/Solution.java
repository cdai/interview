package hackerrank.string.marsexploration;

import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String S = in.next();

        System.out.println(numOfLetterAltered(S, "SOS"));
    }

    // No need to use another index J
    private static int numOfLetterAltered(String s, String sos) {
        int cnt = 0;
        for (int i = 0; i < s.length(); i++)
            if (s.charAt(i) != sos.charAt(i % sos.length())) cnt++;
        return cnt;
    }

    private static int numOfLetterAltered2(String s, String sos) {
        int cnt = 0;
        for (int i = 0, j = 0; i < s.length(); i++, j = (j + 1) % sos.length())
            if (s.charAt(i) != sos.charAt(j)) cnt++;
        return cnt;
    }

}
