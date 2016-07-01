package advanced.combinatorial.combination.lc022_generateparentheses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public List<String> generateParenthesis(int n) {
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
