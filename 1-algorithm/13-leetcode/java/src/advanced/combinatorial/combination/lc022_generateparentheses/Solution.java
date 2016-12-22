package advanced.combinatorial.combination.lc022_generateparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 *  [
 *      "((()))",
 *      "(()())",
 *      "(())()",
 *      "()(())",
 *      "()()()"
 *  ]
 */
public class Solution {

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<>();
        generate(ret, new char[n * 2], 0, 0, n, 0);
        return ret;
    }

    private void generate(List<String> ret, char[] str, int left, int right, int max, int i) {
        if (left == max && right == left) {
            ret.add(String.valueOf(str));
            return;
        }

        if (left < max) {
            str[i] = '(';
            generate(ret, str, left + 1, right, max, i + 1);
        }
        if (right < left) {
            str[i] = ')';
            generate(ret, str, left, right + 1, max, i + 1);
        }
    }

    // My 2AC: O(?)
    public List<String> generateParenthesis2(int n) {
        Queue<String> queue = new LinkedList<>();
        Set<String> result = new HashSet<>();
        queue.offer("");    // error1: start from () will error for n=1, since Set is empty
        while (n-- > 0) {
            result.clear(); // error2: don't forget clear
            for (int size = queue.size(); size > 0; size--) {
                String par = queue.poll();
                for (int i = 0; i <= par.length(); i++) {
                    String newpar = par.substring(0, i) + "()" + par.substring(i);
                    if (result.add(newpar))
                        queue.offer(newpar);
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<String> generateParenthesis1(int n) {
        if (n == 0) {
            return Arrays.asList("");
        }
        if (n == 1) {
            return Arrays.asList("()");
        }

        /*Set<String> curr = new HashSet<>();
        for (int i = 1; i < n/2; i++) {
            List<String> p1 = generateParenthesis(i);
            List<String> p2 = generateParenthesis(n-i);

            curr.add(p1 + p2);
            curr.add(p2 + p1);
        }*/

        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (String p1 : generateParenthesis(i)) {
                for (String p2 : generateParenthesis(n-i-1)) {
                    result.add("(" + p1 + ")" + p2);
                }
            }
        }
        return result;
    }

}
