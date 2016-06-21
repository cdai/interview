package fundamentals.tree.lc094_binarytreeinordertraversal;

import fundamentals.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * For example: Given binary tree [1,null,2,3], return [1,3,2].
 * Note: Recursive solution is trivial, could you do it iteratively?
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        if (root == null) {
            return vals;
        }

        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visit = new HashSet<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // Retain subtree root and use set to determine if visited before
            if (visit.contains(node)) {
                vals.add(node.val);
            } else {
                visit.add(node);

                if (node.right != null) {
                    stack.push(node.right);
                }
                stack.push(node);
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }
        return vals;
    }
}
