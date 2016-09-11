package fundamentals.tree.dfs.inorder.lc094_binarytreeinordertraversal;

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

    // Iterative traversal: O(N) time, O(h) space
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while (!s.isEmpty() || p != null) {
            if (p != null) {
                s.push(p);
                p = p.left;
            } else {
                ret.add(s.peek().val);
                p = s.pop().right;
            }
        }
        return ret;
    }

    // Morris traversal: O(N) time, O(1) space
    public List<Integer> inorderTraversal_morris(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                result.add(cur.val);        // Exit-1: no left child, then go right
                cur = cur.right;
            } else {
                // Find rightmost in left subtree
                TreeNode rightmost = cur.left;
                while (rightmost.right != null && rightmost.right != cur) {
                    rightmost = rightmost.right;
                }

                // 1) rightmost = null: then link i to root as footprint before go left
                // 2) rightmost = cur: means we're back to root by footprint, so restore rightmost.right then go right
                if (rightmost.right == null) {
                    rightmost.right = cur;
                    cur = cur.left;
                } else {
                    result.add(cur.val);    // Exit-2: left subtree complete, then go right
                    rightmost.right = null;
                    cur = cur.right;
                }
            }
        }
        return result;
    }

    // My 1st
    public List<Integer> inorderTraversal1(TreeNode root) {
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
