package hackerrank.string.camelcase;

import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        int cnt = 1;
        for (char c : s.toCharArray())
            if (Character.isUpperCase(c))
                cnt++;

        System.out.println(cnt);
    }

}
