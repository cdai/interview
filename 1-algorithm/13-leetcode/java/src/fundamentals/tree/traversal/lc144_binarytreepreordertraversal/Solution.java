package fundamentals.tree.traversal.lc144_binarytreepreordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example: Given binary tree {1,#,2,3}, return [1,2,3].
 */
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        if (root == null) {
            return vals;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // Pre-orderly visit current node
            vals.add(node.val);

            if (node.right != null) { // error2: push right first so make left popped first
                stack.push(node.right);
            }

            if (node.left != null) { // error1: push null into stack by mistake causing NPE
                stack.push(node.left);
            }
        }
        return vals;
    }
}
