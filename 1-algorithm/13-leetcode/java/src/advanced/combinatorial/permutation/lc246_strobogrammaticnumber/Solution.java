package advanced.combinatorial.permutation.lc246_strobogrammaticnumber;

import java.util.HashMap;
import java.util.Map;

/**
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.isStrobogrammatic("0"));
        System.out.println(sol.isStrobogrammatic("69"));
        System.out.println(sol.isStrobogrammatic("88"));
        System.out.println(sol.isStrobogrammatic("181"));
        System.out.println(sol.isStrobogrammatic("131"));
    }

    public boolean isStrobogrammatic(String num) {
        for (int i = 0, j = num.length() - 1; i <= j; i++, j--)
            if (!"00 11 696 88".contains(num.charAt(i) + "" + num.charAt(j)))
                return false;
        return true;
    }

    public boolean isStrobogrammatic_map(String num) {
        Map<Character, Character> map = new HashMap<>();
        for (char[] c : new char[][]{{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}}) {
            map.put(c[0], c[1]);
            map.put(c[1], c[0]);
        }

        for (int i = 0, j = num.length() - 1; i <= j; i++, j--)
            if (map.getOrDefault(num.charAt(i), ' ') != num.charAt(j))
                return false;
        return true;
    }

}
