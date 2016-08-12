package fundamentals.tree.dfs.postorder.lc110_balancedbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class Solution {

    // My 2nd
    public boolean isBalanced(TreeNode root) {
        return doCheckBalance(root) != -1;
    }

    // return -1 to terminate, otherwise it means height
    private int doCheckBalance(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Only check if necessary (short circuit)
        int h1 = doCheckBalance(root.left);
        if (h1 == -1) {
            return -1;
        }
        int h2 = doCheckBalance(root.right);
        if (h2 == -1) {
            return -1;
        }
        return Math.abs(h1 - h2) <= 1 ? Math.max(h1, h2) + 1 : -1;
    }

    // My 1st
    public boolean isBalanced1(TreeNode root) {
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
