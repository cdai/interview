package fundamentals.tree.dfs.preorder.lc100_sametree;

import fundamentals.tree.TreeNode;

/**
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) return p == q;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // My 2nd: preorder traversal, can implement in stack
    // My 1st
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return (p == null && q == null);
        }
        return p.val == q.val &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right);
    }
}
