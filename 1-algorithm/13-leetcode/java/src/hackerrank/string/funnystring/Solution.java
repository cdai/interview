package hackerrank.string.funnystring;

import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int num = Integer.parseInt(in.nextLine());
            while (num-- > 0) {
                System.out.println(isFunny(in.nextLine()) ? "Funny" : "Not Funny");
            }
        }
    }

    private static boolean isFunny(String str) {
        char[] c = str.toCharArray();
        for (int i = 1, len = str.length(); i < len; i++)
            if (Math.abs(c[i] - c[i - 1]) != Math.abs(c[len - i - 1] - c[len - i]))
                return false;
        return true;
    }

}
