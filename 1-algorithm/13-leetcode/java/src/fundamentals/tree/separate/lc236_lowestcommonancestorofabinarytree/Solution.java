package fundamentals.tree.separate.lc236_lowestcommonancestorofabinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {   // Key: nice!!!
            return root;
        }

        TreeNode leftFound = lowestCommonAncestor(root.left, p, q);
        TreeNode rightFound = lowestCommonAncestor(root.right, p, q);

        if (leftFound != null && rightFound != null) { // Input is TreeNode not val, no duplicate!!
            return root;
        }
        return (leftFound == null) ? rightFound : leftFound;
    }

}
