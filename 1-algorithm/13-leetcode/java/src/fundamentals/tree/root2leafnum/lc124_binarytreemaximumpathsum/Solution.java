package fundamentals.tree.root2leafnum.lc124_binarytreemaximumpathsum;

import fundamentals.tree.TreeNode;

/**
 *
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

    private int maxSum = 0;

    public int maxPathSum(TreeNode root) {
        doMaxPathSum(root);
        return maxSum;
    }

    private int doMaxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxLeft = doMaxPathSum(root.left);
        int maxRight = doMaxPathSum(root.right);

        // Update max path sum
        maxSum = Math.max(maxSum, maxLeft + maxRight + root.val);

        // Return max path in left or right subtree, so that be combined in upper level
        return Math.max(maxLeft, maxRight) + root.val;
    }

}
