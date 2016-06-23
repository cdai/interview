package fundamentals.tree.depth.lc104_maximumdepthofbinarytree;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 */
public class Solution {
    public int maxDepth_old(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return doMaxDepth(root, 1);
    }

    private int doMaxDepth(TreeNode root, int depth) {
        int lDepth = depth;
        int rDepth = depth;
        if (root.left != null) {
            lDepth = doMaxDepth(root.left, depth+1);
        }
        if (root.right != null) {
            rDepth = doMaxDepth(root.right, depth+1);
        }
        return Math.max(lDepth, rDepth);
    }


    // More elegant, compact, efficient solution
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
