package lintcode.bst;

import fundamentals.tree.TreeNode;

/**
 */
public class Solution {

    // 375-Clone Binary Tree
    public TreeNode cloneTree(TreeNode root) {
        if (root == null) return null;
        TreeNode copy = new TreeNode(root.val);
        copy.left = cloneTree(root.left);
        copy.right = cloneTree(root.right);
        return copy;
    }

}
