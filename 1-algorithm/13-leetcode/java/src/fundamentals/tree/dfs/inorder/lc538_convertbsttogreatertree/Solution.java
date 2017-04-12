package fundamentals.tree.dfs.inorder.lc538_convertbsttogreatertree;

import fundamentals.tree.TreeNode;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to
 * the original key plus sum of all keys greater than the original key in BST.
 */
public class Solution {

    public TreeNode convertBST(TreeNode root) {
        dfs(root, 0);
        return root;
    }

    // Simple to think from reversed inorder traversal view
    // sum   : all nodes which we have traversed thus far
    // return: root.val + sum of all nodes greater than root
    private int dfs(TreeNode root, int sum) {
        if (root == null) return sum;
        int rsum = dfs(root.right, sum);
        root.val += rsum;
        return dfs(root.left, root.val);
    }

    /**    A
     *   /   \
     *  X    ...
     * /\
     * L R
     *
     * sum = sum of A.val and right subtree
     * R += sum
     * X += sum + rsum
     * L += sum + X.val + rsum
     * Return sum of all nodes of current tree
     */
    private int dfs_my(TreeNode root, int sum) {
        if (root == null) return 0;
        int rsum = dfs(root.right, sum);
        int oval = root.val;
        root.val += sum + rsum;
        int lsum = dfs(root.left, root.val);
        return oval + lsum + rsum;
    }

}
