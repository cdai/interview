package fundamentals.tree.dfs.postorder.lc124_binarytreemaximumpathsum;

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

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    // Invariant:
    // 1) return max path that is able to reuse.
    // 2) max updated by tree rooted at root after return.
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left), right = dfs(root.right);
        max = Math.max(max, root.val); // Too messy!
        max = Math.max(max, root.val + left);
        max = Math.max(max, root.val + right);
        max = Math.max(max, root.val + left + right);
        return Math.max(root.val, Math.max(left + root.val, right + root.val));
    }

    public int maxPathSum2(TreeNode root) {
        doMaxPathSum(root);
        return max;
    }

    // must have: ret <= left + root.val + right
    public int doMaxPathSum(TreeNode root) {
        if (root == null) return 0; // Safe! Since we compare left+root, right+root and root
        int left = Math.max(0, doMaxPathSum(root.left));
        int right = Math.max(0, doMaxPathSum(root.right));
        max = Math.max(max, left + root.val + right);   // Two-side (could NOT be reused)
        return Math.max(left, right) + root.val;        // One-side (reused by upper level)
    }

    // must have: ret <= left + root.val + right
    public int doMaxPathSum_mine(TreeNode root) {
        if (root == null) return 0;         // Safe! Since we compare left+root, right+root and root
        int left = doMaxPathSum(root.left);
        int right = doMaxPathSum(root.right);
        int ret = Math.max(Math.max(left, right), 0) + root.val;    // One-side (reused by upper level)
        max = Math.max(max, Math.max(ret, left + root.val + right));// Two-side (could NOT be reused)
        return ret;
    }

    // My 1AC
    private int maxSum = Integer.MIN_VALUE; // error1: init to MIN_VAL, not 0. eg.[-3] -> -3 not 0

    public int maxPathSum1(TreeNode root) {
        doMaxPathSum1(root);
        return maxSum;
    }

    private int doMaxPathSum1(TreeNode root) {
        if (root == null) {
            return 0;   // error2: it's ok, since we compare root+left,root+right, never left or right alone
        }

        int maxLeft = doMaxPathSum1(root.left);
        int maxRight = doMaxPathSum1(root.right);

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
