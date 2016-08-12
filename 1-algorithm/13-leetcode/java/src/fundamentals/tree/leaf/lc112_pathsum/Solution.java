package fundamentals.tree.leaf.lc112_pathsum;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 */
public class Solution {

    // My 2nd
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }

        sum -= root.val;

        if (root.left == null && root.right == null) {
            return sum == 0;
        }
        /*if (root.right == null) {
            return hasPathSum(root.left, sum);
        }
        if (root.left == null) {
            return hasPathSum(root.right, sum);
        }*/
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    // Error for edge case
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {     // error: [],sum=0 -> should be false
            return sum == 0;
        }

        sum -= root.val;

        if (root.right == null) {
            return hasPathSum(root.left, sum);
        }
        if (root.left == null) {
            return hasPathSum(root.right, sum);
        }
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    // My 1st
    public boolean hasPathSum1(TreeNode root, int sum) {
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
            isFound = hasPathSum1(root.left, sum - root.val);
        }
        if (!isFound && root.right != null) {
            isFound = hasPathSum1(root.right, sum - root.val);
        }
        return isFound;
    }
}
