package fundamentals.tree.root2leafnum.lc124_binarytreemaximumpathsum;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find the maximum path sum. For this problem, a path is defined as any sequence of nodes
 * from some starting node to any node in the tree along the parent-child connections.
 * The path does not need to go through the root.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(new Solution().maxPathSum(root));
    }

    private int maxSum = Integer.MIN_VALUE; // error1: init to MIN_VAL, not 0. eg.[-3] -> -3 not 0

    public int maxPathSum(TreeNode root) {
        doMaxPathSum(root);
        return maxSum;
    }

    private int doMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;   // error2: it's ok, since we compare root+left,root+right, never left or right alone
        }

        int maxLeft = doMaxPathSum(root.left);
        int maxRight = doMaxPathSum(root.right);

        // Max path sum that is useable by parent: root, root+left, root+right
        int maxSumForParent = Math.max(root.val, root.val + maxLeft);
        maxSumForParent = Math.max(maxSumForParent, root.val + maxRight);
        maxSum = Math.max(maxSum, maxSumForParent);

        // Mat path sum that is NOT useable by parent
        maxSum = Math.max(maxSum, maxLeft + maxRight + root.val);

        // Only return path sum reusable by parent
        return maxSumForParent;
    }

    // Correct, but only works for positive nums
    private int doMaxPathSum2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxLeft = doMaxPathSum2(root.left);
        int maxRight = doMaxPathSum2(root.right);

        // Update max path sum
        maxSum = Math.max(maxSum, maxLeft + maxRight + root.val);

        // Return max path in left or right subtree, so that be combined in upper level
        return Math.max(maxLeft, maxRight) + root.val;
    }

}
