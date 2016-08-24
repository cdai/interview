package fundamentals.stack.nested.lc385_miniparser;

import java.util.Stack;

/**
 * Given a nested list of integers represented as a string, implement a parser to deserialize it.
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * Note: You may assume that the string is well-formed:
 *  String is non-empty.
 *  String does not contain white spaces.
 *  String contains only digits 0-9, [, - ,, ].
 * Example 1: Given s = "324", You should return a NestedInteger object which contains a single integer 324.
 * Example 2: Given s = "[123,[456,[789]]]", Return a NestedInteger object containing a nested list with 2 elements:
 *  1. An integer containing value 123.
 *  2. A nested list containing two elements:
 *    i.  An integer containing value 456.
 *    ii. A nested list with one element:
 *     a. An integer containing value 789.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().deserialize("[5,[-31,12,[20]],-13]"));
        //System.out.println(new Solution().deserialize("[-1]"));
    }

    // Elegant recursive solution
    public NestedInteger deserialize(String s) {
        if (!s.contains("[")) return new NestedInteger(Integer.valueOf(s));

        NestedInteger result = new NestedInteger();
        int prev = 1;
        for (int i = 1, cnt = 0; i < s.length() - 1; i++) { // Exclude outmost '[]'
            if (s.charAt(i) == '[') {
                cnt++;
            } else if (s.charAt(i) == ']') {
                cnt--;
            } else if (s.charAt(i) == ',' && cnt == 0) {
                result.add(deserialize(s.substring(prev, i)));
                prev = i + 1;
            }
        }
        if (prev < s.length() - 1) {
            result.add(deserialize(s.substring(prev, s.length() - 1)));
        }
        return result;
    }

    // Wrong next ] location...
    public NestedInteger deserialize_wrongrecursive(String s) {
        if (!s.contains("[")) return new NestedInteger(Integer.valueOf(s));

        NestedInteger result = new NestedInteger();
        for (int i = 1, end = 0; i < s.length() - 1; i = end + 1) { // Exclude outmost '[]' and Skip ',' if exist
            if (s.charAt(i) == '[') {
                end = s.lastIndexOf("]", s.length() - 2) + 1;       // Skip last ']'
            } else {
                end = s.indexOf(",", i);
                if (end < 0) {
                    end = s.length() - 1;                           // This is the last number
                }
            }
            result.getList().add(deserialize(s.substring(i, end)));
        }
        return result;
    }

    // My intuitive approach: parsing one by one without recursion or lookahead
    public NestedInteger deserialize_iterative(String s) {
        if (!s.contains("[")) {                 // Only number
            return new NestedInteger(Integer.valueOf(s));
        }

        Stack<NestedInteger> stack = new Stack<>();
        stack.push(new NestedInteger());        // Create outmost one, so iterate string [1,len-1]

        NestedInteger cur = null;
        for (int i = 0, sign = 1; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            if (c == '[') {                     // '[': New list linked with stack top
                NestedInteger list = new NestedInteger();
                if (!stack.isEmpty()) {
                    stack.peek().add(list);
                }
                stack.push(list);
            } else if (c == '-') {
                sign = -1;
            } else if ('0' <= c && c <= '9') {  // '0~9': Update cur number
                if (cur == null) {
                    stack.peek().add(cur = new NestedInteger(0));
                }
                cur.setInteger(cur.getInteger() * 10 + sign * (c - '0'));
            } else {                            // ',' or ']': number and list ends
                if (c == ']') {
                    stack.pop();
                }
                cur = null;
                sign = 1;
            }
        }
        return stack.pop();
    }

}
