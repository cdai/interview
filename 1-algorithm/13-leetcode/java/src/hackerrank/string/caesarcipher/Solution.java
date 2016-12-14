package hackerrank.string.caesarcipher;

import java.util.Scanner;

/**
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        try (Scanner in = new Scanner(System.in)) {
            int len = Integer.parseInt(in.nextLine());
            String str = in.nextLine();
            int key = Integer.parseInt(in.nextLine());
            System.out.println(encrypt(str, key));
        }
    }

    // O(N) time
    private static String encrypt(String str, int key) {
        StringBuilder secret = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                secret.append((char) (((c - base) + key) % 26 + base));
            } else {
                secret.append(c);
            }
        }
        return secret.toString();
    }

}
