package fundamentals.stack.validation.lc331_verifypreorderserializationofabinarytree;

import java.util.Stack;

/**
 * One way to serialize a binary tree is to use pre-order traversal.
 * When we encounter a non-null node, we record the node's value.
 * If it is a null node, we record using a sentinel value such as #.
 *
 *       _9_
 *      /   \
 *     3     2
 *    / \   / \
 *   4   1  #  6
 *  / \ / \   / \
 *  # # # #   # #
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 * where # represents a null node. Given a string of comma separated values,
 * verify whether it is a correct preorder traversal serialization of a binary tree.
 * Find an algorithm without reconstructing the tree.
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 * Example 1: "9,3,4,#,#,1,#,#,2,#,6,#,#". Return true.
 * Example 2: "1,#". Return false.
 * Example 3: "9,#,#,1". Return false.
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().isValidSerialization("9,3,4,#,#,1,#,#,2,#,6,#,#"));
    }

    // Neat and smart solution from dietpepsi!
    // Check if outdegree = indegree along the preorder path
    // which means every subtree must follow that rule
    public boolean isValidSerialization(String preorder) {
        int diff = 1;   // a "virtual" indegree for root
        for (String node : preorder.split(",")) {
            if (--diff < 0) {
                return false;
            }
            diff += "#".equals(node) ? 0 : 2;
        }
        return diff == 0;
    }

    // My 2AC: use a variable stack (or called depth)
    public boolean isValidSerialization2(String preorder) {
        String[] nodes = preorder.split(",");
        int stack = 0, i = 0;
        for (; i < nodes.length; i++) {
            if ("#".equals(nodes[i])) {
                if (stack == 0) {
                    break;
                }
                stack--;
            } else {
                stack++;
            }
        }
        return stack == 0 && i == nodes.length - 1 && "#".equals(nodes[i]);
    }

    // My 1AC: a real stack is overkill here.
    public boolean isValidSerialization1(String preorder) {
        Stack<String> stack = new Stack<>();
        String[] serial = preorder.split(",");

        int i = 0;
        while (i < serial.length && (!stack.isEmpty() || !serial[i].equals("#"))) {
            if (!serial[i].equals("#")) {
                stack.push(serial[i]);
                i++;
            } else { // stack must not be empty
                stack.pop();
                i++;
            }
        }
        // Stack is empty && i points to last && last is "#" => true!
        return (i == serial.length - 1);
    }

}
