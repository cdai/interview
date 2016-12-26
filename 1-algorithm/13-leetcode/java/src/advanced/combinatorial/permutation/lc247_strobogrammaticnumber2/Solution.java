package advanced.combinatorial.permutation.lc247_strobogrammaticnumber2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find all strobogrammatic numbers that are of length = n.
 * For example, Given n = 2, return ["11","69","88","96"].
 * Hint: Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findStrobogrammatic(1));
        System.out.println(sol.findStrobogrammatic(2));
        System.out.println(sol.findStrobogrammatic(3));
        System.out.println(sol.findStrobogrammatic(4));
    }

    public List<String> findStrobogrammatic(int n) {
        return new ArrayList<>(generate(n, n));
    }

    private List<String> generate(int k, int n) {
        if (k == 0) return Arrays.asList("");
        if (k == 1) return Arrays.asList("0", "1", "8");

        List<String> ret = new ArrayList<>();
        for (String s : generate(k - 2, n)) {
            if (k < n) ret.add("0" + s + "0");
            ret.add("1" + s + "1");
            ret.add("6" + s + "9");
            ret.add("8" + s + "8");
            ret.add("9" + s + "9");
        }
        return ret;
    }

    // Wrong! 10 is not strobogrammatic, but it could be used for 1001.
    public List<String> findStrobogrammatic_wrong(int n) {
        if (n == 1) return Arrays.asList("1", "6", "8", "9");

        List<String> ret = new ArrayList<>();
        if (n % 2 == 0) {
            for (String half : findStrobogrammatic(n / 2))
                ret.add(half + strobo(half));
        } else {
            for (String half : findStrobogrammatic(n / 2))
                for (char one : new char[] { '0', '1', '8' })//findStrobogrammatic(1))
                    ret.add(half + one + strobo(half));
        }
        return ret;
    }

    private String strobo(String num) {
        StringBuilder ret = new StringBuilder();
        for (int i = num.length() - 1; i >= 0; i--) {
            switch (num.charAt(i)) {
                case '0': ret.append('0'); break;
                case '1': ret.append('1'); break;
                case '6': ret.append('9'); break;
                case '8': ret.append('8'); break;
                case '9': ret.append('6'); break;
            }
        }
        return ret.toString();
    }

}
