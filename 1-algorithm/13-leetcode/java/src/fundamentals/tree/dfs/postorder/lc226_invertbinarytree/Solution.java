package fundamentals.tree.dfs.postorder.lc226_invertbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Invert a binary tree.
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * to
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class Solution {

    // My 2nd: O(N) time, O(h) space
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left, right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);
        return root;
    }

    // My 1st
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode invertLeft = invertTree(root.left);
        TreeNode invertRight = invertTree(root.right);

        root.left = invertRight;
        root.right = invertLeft;
        return root;
    }

}
