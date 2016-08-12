package fundamentals.tree.depth.lc111_minimumdepthofbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(7);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        System.out.println(new Solution().minDepth(root));
    }

    // My 2nd
    public int minDepth(TreeNode root) {
        if (root == null) { // Just in case root is null, no help for later recursion
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;       // error: this is a leaf, return 1 not 0
        }
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    // My 1st
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return doMinDepth(root, 1);
    }

    private int doMinDepth(TreeNode root, int depth) {
        int lDepth = Integer.MAX_VALUE;
        int rDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            lDepth = doMinDepth(root.left, depth+1);
        }
        if (root.right != null) {
            rDepth = doMinDepth(root.right, depth+1);
        }

        if (root.left == null && root.right == null) { // error1: set l/rDepth to MAX and return depth if leaf
            return depth;
        }
        return Math.min(lDepth, rDepth);
    }
}
