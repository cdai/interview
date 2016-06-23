package fundamentals.tree.root2leafnum.lc112_pathsum;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 */
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        // Base case
        if (root.left == null && root.right == null) {
            return (sum - root.val == 0);
        }

        // Prune: it turns out to be unnecessary, which is wrong for negative number...
        //if (sum < 0) {
        //    return false;
        //}

        boolean isFound = false;
        if (root.left != null) {
            isFound = hasPathSum(root.left, sum - root.val);
        }
        if (!isFound && root.right != null) {
            isFound = hasPathSum(root.right, sum - root.val);
        }
        return isFound;
    }
}
