package fundamentals.tree.validate.lc110_balancedbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return (height(root) != -1);
    }

    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int hleft = height(root.left);
        int hright = height(root.right);
        if ((hleft != -1) && (hright != -1) &&
                (Math.abs(hleft - hright) <= 1)) {
            return Math.max(hleft, hright) + 1;
        }
        return -1;
    }
}
