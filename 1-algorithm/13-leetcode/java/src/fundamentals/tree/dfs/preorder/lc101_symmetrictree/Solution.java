package fundamentals.tree.dfs.preorder.lc101_symmetrictree;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric. But the following [1,2,2,null,3,null,3] is not.
 */
public class Solution {

    // My 2nd: recursive solution
    public boolean isSymmetric_recurisve(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return root1 == null && root2 == null;
        }
        return root1.val == root2.val
                && isSymmetric(root1.left, root2.right)
                && isSymmetric(root1.right, root2.left);
    }

    // My 2nd: iterative solution, use stack to simulate DFS
    public boolean isSymmetric(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            stack.push(root.right);
            stack.push(root.left);
        }

        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();

            if (node1 == null || node2 == null) {
                if (node1 != null || node2 != null) {
                    return false;
                }
            } else {
                if (node1.val != node2.val) {
                    return false;
                }
                stack.push(node2.left);
                stack.push(node1.right);
                stack.push(node2.right);
                stack.push(node1.left);
            }
        }
        return true;
    }

    // My 1st
    public boolean isSymmetric1(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetric1(root.left, root.right);
    }

    private boolean isSymmetric1(TreeNode tree1, TreeNode tree2) {
        if (tree1 == null || tree2 == null) {
            return (tree1 == null && tree2 == null);
        }
        return tree1.val == tree2.val &&
                isSymmetric1(tree1.left, tree2.right) &&
                isSymmetric1(tree1.right, tree2.left);
    }
}
