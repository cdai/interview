package fundamentals.tree.depth.lc111_minimumdepthofbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path
 * from the root node down to the nearest leaf node.
 */
public class Solution {
    public int minDepth(TreeNode root) {
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
