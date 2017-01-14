package fundamentals.tree.dfs.leaf.lc404_sumofleftleaves;

import fundamentals.tree.TreeNode;

/**
 * Find the sum of all left leaves in a given binary tree.
 */
public class Solution {

    // "Look ahead"
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null)
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    public int sumOfLeftLeaves1(TreeNode root) {
        return dfs(root, null);
    }

    private int dfs(TreeNode root, TreeNode pre) {
        if (root == null) return 0;
        if (root.left == null && root.right == null)
            return (pre != null && pre.left == root) ? root.val : 0;
        return dfs(root.left, root) + dfs(root.right, root);
    }

}
