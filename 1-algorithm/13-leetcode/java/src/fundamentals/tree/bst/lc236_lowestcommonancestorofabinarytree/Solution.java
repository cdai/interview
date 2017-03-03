package fundamentals.tree.bst.lc236_lowestcommonancestorofabinarytree;

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

    // O(N) time.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    // My 3AC. O(N) time. not O(h)
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return (left == null) ? right : left;
    }

    // Find one then return it. Find two then return root. Otherwise return null
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;   // Key: nice!!!

        TreeNode leftFound = lowestCommonAncestor2(root.left, p, q);
        TreeNode rightFound = lowestCommonAncestor2(root.right, p, q);
        if (leftFound != null && rightFound != null) // Input is TreeNode not val, no duplicate!!
            return root;
        return (leftFound == null) ? rightFound : leftFound;
    }

}
