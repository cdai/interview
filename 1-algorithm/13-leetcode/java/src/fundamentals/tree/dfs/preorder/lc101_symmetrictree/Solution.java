package fundamentals.tree.dfs.preorder.lc101_symmetrictree;

import fundamentals.tree.TreeNode;

import java.util.Stack;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric. But the following [1,2,2,null,3,null,3] is not.
 */
public class Solution {

    // 3AC
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return root1 == null && root2 == null;
        return root1.val == root2.val &&
                isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left);
    }

    public boolean isSymmetric_iterative(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> s = new Stack<>();
        s.push(root.left);
        s.push(root.right);
        while (!s.isEmpty()) {
            TreeNode n1 = s.pop(), n2 = s.pop();
            if (n1 == null && n2 == null) continue;
            if ((n1 == null || n2 == null) || n1.val != n2.val) return false;
            s.push(n1.left);
            s.push(n2.right);
            s.push(n1.right);
            s.push(n2.left);
        }
        return true;
    }

    // My 2nd: recursive solution
    public boolean isSymmetric_recurisve(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return root1 == null && root2 == null;
        return root1.val == root2.val
                && isSymmetric(root1.left, root2.right)
                && isSymmetric(root1.right, root2.left);
    }

    // My 2nd: iterative solution, use stack to simulate DFS
    public boolean isSymmetric2(TreeNode root) {
        Stack<TreeNode> s = new Stack<>();
        if (root != null) {
            s.push(root.right);
            s.push(root.left);
        }
        while (!s.isEmpty()) {
            TreeNode n1 = s.pop();
            TreeNode n2 = s.pop();
            if (n1 == null || n2 == null) {
                if (n1 != null || n2 != null)
                    return false;
            } else {
                if (n1.val != n2.val)
                    return false;
                s.push(n2.left);
                s.push(n1.right);
                s.push(n2.right);
                s.push(n1.left);
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
