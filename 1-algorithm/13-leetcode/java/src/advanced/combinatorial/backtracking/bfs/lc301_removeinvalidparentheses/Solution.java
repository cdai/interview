package advanced.combinatorial.backtracking.bfs.lc301_removeinvalidparentheses;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 * Examples:
 *  "()())()" -> ["()()()", "(())()"]
 *  "(a)())()" -> ["(a)()()", "(a())()"]
 *  ")(" -> [""]
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().removeInvalidParentheses(")("));
    }

    public List<String> removeInvalidParentheses(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        List<String> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        boolean isValid = false;
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                result.add(s);
                isValid = true;
            }
            if (isValid) continue;
            for (int i = 0; i < s.length(); i++)
                if ("()".indexOf(s.charAt(i)) >= 0)
                    if (visited.add(s.substring(0, i) + s.substring(i + 1)))
                        queue.offer(s.substring(0, i) + s.substring(i + 1));
        }
        return result;
    }

    public List<String> removeInvalidParentheses_oneloop(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        List<String> result = new ArrayList<>();
        boolean isValid = false;
        while (!queue.isEmpty() && !isValid) {
            Set<String> next = new HashSet<>();
            int size = queue.size();
            while (size-- > 0) {
                s = queue.poll();
                if (isValid(s)) { // Very nice! "Steal" into result if any one is valid
                    result.add(s);
                    isValid = true;
                }
                if (isValid)
                    continue;
                for (int i = 0; i < s.length(); i++)
                    if ("()".indexOf(s.charAt(i)) >= 0)
                        next.add(s.substring(0, i) + s.substring(i + 1));
            }
            queue.addAll(next);
        }
        return result;
    }

    // My 2AC
    public List<String> removeInvalidParentheses_my(String s) {
        Queue<String> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            // 1.Check if found solution
            List<String> result = new ArrayList<>();
            for (String str : queue)
                if (isValid(str))
                    result.add(str);
            if (!result.isEmpty())
                return result;

            // 2.Continue remove
            Set<String> next = new HashSet<>();
            int size = queue.size();
            while (size-- > 0) {
                s = queue.poll();
                for (int i = 0; i < s.length(); i++)
                    if ("()".indexOf(s.charAt(i)) >= 0)
                        next.add(s.substring(0, i) + s.substring(i + 1));
            }
            queue.addAll(next);
        }
        return new ArrayList<>();
    }

    private boolean isValid(String s) {
        int stack = 0, i = 0;
        for (; i < s.length() && stack >= 0; i++) {
            stack = s.charAt(i) == '(' ? stack + 1 : (s.charAt(i) == ')' ? stack - 1 : stack);
        }
        return i == s.length() && stack == 0;
    }

}
