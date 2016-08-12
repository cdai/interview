package fundamentals.tree.construct.lc114_flattenbinarytreetolinkedlist;

import fundamentals.tree.TreeNode;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * For example,
 * Given
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 */
public class Solution {

    // Freaking beatiful solution by reversed post-order traversal.
    // Inspired by leetcode discuss.
    public void flatten(TreeNode root) {
        doFlatten(root, null);
    }

    private TreeNode doFlatten(TreeNode root, TreeNode prev) {
        if (root == null) {
            return prev;
        }
        prev = doFlatten(root.right, prev);
        prev = doFlatten(root.left, prev);

        // prev is actually the last one in order
        root.right = prev;
        root.left = null;
        prev = root;
        return prev;
    }

    // My 2nd
    public void flatten2(TreeNode root) {
        doFlatten(root);
    }

    private TreeNode doFlatten(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftEnd = doFlatten(root.left);
        TreeNode rightEnd = doFlatten(root.right);

        if (leftEnd != null) {
            leftEnd.right = root.right;
            root.right = root.left;
            root.left = null;                           // error1: clean left pointer
        }
        return rightEnd != null ? rightEnd :
                ((leftEnd != null) ? leftEnd : root);   // error2: return itself for leaf
    }

    // My 1st
    public void flatten1(TreeNode root) {
        doFlatten(root);
    }

    private TreeNode doFlatten1(TreeNode node) {
        if (node == null) {
            return null;
        }

        TreeNode rightmostInLeft = doFlatten1(node.left);
        TreeNode rightmostInRight = doFlatten1(node.right);

        // This is a leaf!
        if (rightmostInLeft == null && rightmostInRight == null) {
            return node;
        }
        // Has left child, do flatten anyway
        if (rightmostInLeft != null) {
            TreeNode right = node.right;
            node.right = node.left;
            node.left = null;                   // error1: clear it!
            rightmostInLeft.right = right;
        }
        // No right child (node.right=null -> rightmostInRight=null)
        return (rightmostInRight == null) ? rightmostInLeft : rightmostInRight;
    }

}
